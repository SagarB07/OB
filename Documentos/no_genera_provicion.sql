-- Function: no_genera_rol_provision(character varying)

-- DROP FUNCTION no_genera_rol_provision(character varying);

CREATE OR REPLACE FUNCTION no_genera_provicion(v_User_ID character varying, v_Client_ID character varying, v_Org_ID character varying )
  RETURNS void AS
$BODY$ DECLARE 
v_ResultStr VARCHAR(2000):='';
    v_Message VARCHAR(2000):='';
    v_Result NUMERIC:=1;
    v_Record_ID VARCHAR(32);

    Cur_Parameter RECORD;
    Cur_Empleado RECORD;
    Cur_Rubro_Rol RECORD;

    v_co_genera_rol_id VARCHAR(32);
    
    v_count NUMERIC;
    v_count_rp NUMERIC;
    v_count_rpl NUMERIC;
    v_rol_pago_provision_id VARCHAR(32);
    v_rol_pago_prov_line_id VARCHAR(32);
    v_line NUMERIC;

    v_doctypeprov_id VARCHAR(32);
    v_doctypeline_id VARCHAR(32);
    v_documentnoprov VARCHAR;
    v_documentnoline VARCHAR;

    v_qry VARCHAR;
    v_calc_moth VARCHAR;
    v_fecha_inicio TIMESTAMP;
    v_fecha_final TIMESTAMP;
    v_currency_id VARCHAR(32);

BEGIN

    RAISE NOTICE '%','Updating PInstance - Processing ' || p_PInstance_ID;
    v_ResultStr:='PInstanceNotFound';
    PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'Y', NULL, NULL);

    BEGIN


        FOR Cur_Empleado IN
        (select bp.c_bpartner_id,
                ce.ad_org_id,
                ce.fecha_inicio
           from c_bpartner bp,
                no_contrato_empleado ce
          where bp.c_bpartner_id = ce.c_bpartner_id
            and bp.isemployee = 'Y'
            and bp.isactive = 'Y'
            and bp.AD_Client_ID=v_Client_ID)
           
        LOOP
		
            v_Org_ID := Cur_Empleado.ad_Org_ID;
            select c_currency_id into v_currency_id
              from ad_client
             where ad_client_id=v_Client_ID;

			 
            SELECT COUNT(*) INTO v_count
              FROM no_rol_pago_provision
             WHERE c_bpartner_id = Cur_Empleado.c_bpartner_id
               AND docstatus in('BR','CO') 
               AND isactive = 'Y'
               AND ispago = 'N';

            IF v_count = 0 THEN

               
                SELECT get_uuid() INTO v_rol_pago_provision_id;

                SELECT dt.c_doctype_id INTO v_doctypeprov_id
                  FROM c_doctype dt
                 WHERE dt.ad_table_id = 'F5057A2680BB43149D7C57B1224A7653';

               SELECT * INTO  v_documentnoprov FROM ad_sequence_doctype(v_doctypeprov_id, v_Client_ID, 'Y');

               INSERT INTO no_rol_pago_provision
                            (no_rol_pago_provision_id,   ad_client_id,       ad_org_id,          isactive,
                             created,                    createdby,          updated,            updatedby,
                             c_bpartner_id,              c_doctype_id,       documentno,         total_ingreso, 
                             total_egreso,               total_neto,         docstatus,          ispago,
                             processed,                  docaccionno,        posted,             processing)
                      VALUES(v_rol_pago_provision_id,    v_Client_ID,        v_Org_ID,           'Y',
                             current_timestamp,          v_User_ID,          current_timestamp,  v_User_ID,
                             Cur_Empleado.c_bpartner_id, v_doctypeprov_id,    v_documentnoprov,       0,
                             0,                          0,                  'CO',               'N',  
                             'Y',                        'CO',               'N',                'N');

                FOR Cur_Rubro_Rol IN
                   (
				   select cr.no_calcula_rubro_id,
                           eie.no_tipo_ingreso_egreso_id,
                           tie.mes_calculo,
                           tie.name,
                           tie.isprovmes
					from no_cb_empleado_acct eie,
											   no_tipo_ingreso_egreso tie,
											   no_calcula_rubro cr
					where 
                       tie.no_tipo_ingreso_egreso_id = eie.no_tipo_ingreso_egreso_id
                       and cr.no_calcula_rubro_id = tie.no_calcula_rubro_id
                       and tie.isactive ='Y'
                       and eie.isactive = 'Y'
                       and cr.isactive = 'Y'
                       and tie.isprovision = 'Y'
		       and eie.c_bpartner_id = Cur_Empleado.c_bpartner_id
                       order by tie.isingreso desc
					   )
                LOOP
                    select get_uuid() into v_rol_pago_prov_line_id;
                    select coalesce(max(line),0)+10 into v_line
                      from no_rol_pago_provision_line
                     where no_rol_pago_provision_id = v_rol_pago_provision_id;

                    IF Cur_Rubro_Rol.mes_calculo is null THEN
                        v_Message='@No_ConfiguracionTIE@';
                        RAISE EXCEPTION '%',v_Message||' '|| Cur_Rubro_Rol.name;
                    END IF;

                    v_calc_moth:= Cur_Rubro_Rol.mes_calculo || ' MONTH';
                    v_qry:= 'select (DATE_TRUNC(''YEAR'', date( now()::varchar)) + interval '''||v_calc_moth||''' )::TIMESTAMP';

                    execute v_qry into v_fecha_inicio;
                    v_fecha_final:= (DATE(v_fecha_inicio::varchar) + interval '11 MONTH')::TIMESTAMP;
                    select min(p.startdate), max(p.enddate)
                    into v_fecha_inicio, v_fecha_final
                    from c_year y, c_period p
                    where to_number(y.year) = DATE_part('YEAR', date(TO_DATE(NOW()))) 
                    and y.ad_client_id = v_Client_ID
                    and y.c_year_id = p.c_year_id;

                    SELECT dt.c_doctype_id INTO v_doctypeline_id
                    FROM c_doctype dt
                    WHERE dt.ad_table_id = 'DDBCCC1DA9BE4E01A31B8325706B08DB';
                    SELECT * INTO  v_documentnoline FROM ad_sequence_doctype(v_doctypeline_id, v_Client_ID, 'Y');

                     INSERT INTO no_rol_pago_provision_line
                                (no_rol_pago_provision_line_id,        ad_client_id,                ad_org_id,                                  isactive,
                                 created,                              createdby,                   updated,                                    updatedby,
                                 no_rol_pago_provision_id,             line,                        no_tipo_ingreso_egreso_id,                  valor,
                                 processed,                            fechainicio,                 fechafin,                                   c_currency_id,
                                 docstatus,                            c_doctype_id,                documentno,                                 docaccionno)
                          VALUES(v_rol_pago_prov_line_id,              v_Client_ID,                 v_Org_ID,                                   'Y',
                                 current_timestamp,                    v_User_ID,                   current_timestamp,                          v_User_ID,
                                 v_rol_pago_provision_id,              v_line,                      Cur_Rubro_Rol.no_tipo_ingreso_egreso_id,    0,
                                 
                                 'Y',                                  v_fecha_inicio,              v_fecha_final,                              v_currency_id, 
                                 
                                 'CO',                                 v_doctypeline_id,            v_documentnoline,                            'RE');
                END LOOP;

            ELSE

                v_rol_pago_provision_id := (select p.no_rol_pago_provision_id from no_rol_pago_provision p where ispago='N' and c_bpartner_id = Cur_Empleado.c_bpartner_id);
                
                SELECT * INTO  v_documentnoprov FROM ad_sequence_doctype(v_doctypeprov_id, v_Client_ID, 'Y');

                FOR Cur_Rubro_Rol IN
                   (select cr.no_calcula_rubro_id,
                           eie.no_tipo_ingreso_egreso_id,
                           tie.mes_calculo,
                           tie.name,
                           tie.isprovmes
                      from no_empleado_ing_egr eie,
                           no_tipo_ingreso_egreso tie,
                           no_calcula_rubro cr
                     where eie.c_bpartner_id = Cur_Empleado.c_bpartner_id
                       and tie.no_tipo_ingreso_egreso_id = eie.no_tipo_ingreso_egreso_id
                       and cr.no_calcula_rubro_id = tie.no_calcula_rubro_id
                       and tie.isactive ='Y'
                       and eie.isactive = 'Y'
                       and cr.isactive = 'Y'
                       and tie.isprovision = 'Y'
                       and eie.no_tipo_ingreso_egreso_id not in (select pl.no_tipo_ingreso_egreso_id from no_rol_pago_provision_line pl where pl.no_rol_pago_provision_id = v_rol_pago_provision_id)
                       order by tie.isingreso desc)
                LOOP
                    select get_uuid() into v_rol_pago_prov_line_id;
                    select coalesce(max(line),0)+10 into v_line
                      from no_rol_pago_provision_line
                     where no_rol_pago_provision_id = v_rol_pago_provision_id;

                    IF Cur_Rubro_Rol.mes_calculo is null THEN
                        v_Message='@No_ConfiguracionTIE@';
                        RAISE EXCEPTION '%',v_Message||' '|| Cur_Rubro_Rol.name;
                    END IF;

                    v_calc_moth:= Cur_Rubro_Rol.mes_calculo || ' MONTH';
                    
                    v_qry:= 'select (DATE_TRUNC(''YEAR'', date( now()::varchar)) + interval '''||v_calc_moth||''' )::TIMESTAMP';

                    execute v_qry into v_fecha_inicio;
                    
                   
                    v_fecha_final:= (DATE(v_fecha_inicio::varchar) + interval '11 MONTH')::TIMESTAMP;

                    select min(p.startdate), max(p.enddate)
                    into v_fecha_inicio, v_fecha_final
                    from c_year y, c_period p
                    where to_number(y.year) = DATE_part('YEAR', date(TO_DATE(NOW()))) 
                    and y.ad_client_id = v_Client_ID
                    and y.c_year_id = p.c_year_id;


                    SELECT dt.c_doctype_id INTO v_doctypeline_id
                    FROM c_doctype dt
                    WHERE dt.ad_table_id = 'DDBCCC1DA9BE4E01A31B8325706B08DB';
                    SELECT * INTO  v_documentnoline FROM ad_sequence_doctype(v_doctypeline_id, v_Client_ID, 'Y');
					

		    v_fecha_inicio = (  --select nce.fecha_inicio into v_fecha_inicio , nce.fecha_fin into v_fecha_final , nce.c_bpartner_id
					select fecha_inicio
					from no_contrato_empleado
					where isactive = 'Y' 
					and  ad_client_id=v_Client_ID 
					and c_bpartner_id = Cur_Empleado.c_bpartner_id
					);
		   v_fecha_final = (  --select nce.fecha_inicio into v_fecha_inicio , nce.fecha_fin into v_fecha_final , nce.c_bpartner_id
					select fecha_fin
					from no_contrato_empleado
					where isactive = 'Y' 
					and  ad_client_id=v_Client_ID 
					and c_bpartner_id = Cur_Empleado.c_bpartner_id
					);
					

                    IF (Cur_Rubro_Rol.no_tipo_ingreso_egreso_id NOT IN (select no_tipo_ingreso_egreso_id from no_rol_pago_provision_line where no_rol_pago_provision_id = v_rol_pago_provision_id)) then
                         INSERT INTO no_rol_pago_provision_line
                                    (no_rol_pago_provision_line_id,        ad_client_id,                ad_org_id,                                  isactive,
                                     created,                              createdby,                   updated,                                    updatedby,
                                     no_rol_pago_provision_id,             line,                        no_tipo_ingreso_egreso_id,                  valor,
                                     processed,                            fechainicio,                 fechafin,                                   c_currency_id,
                                     docstatus,                            c_doctype_id,                documentno,                                 docaccionno)
                              VALUES(v_rol_pago_prov_line_id,              v_Client_ID,                 v_Org_ID,                                   'Y',
                                     current_timestamp,                    v_User_ID,                   current_timestamp,                          v_User_ID,
                                     v_rol_pago_provision_id,              v_line,                      Cur_Rubro_Rol.no_tipo_ingreso_egreso_id,    0,
                                     'Y',                                  v_fecha_inicio,              v_fecha_final,                              v_currency_id, 
                                     'CO',                                 v_doctypeline_id,            v_documentnoline,                            'RE');
                     END IF;
                END LOOP;


            END IF;
        END LOOP;



        v_Message:='@NO_EjecucionCorrecta@';
        RAISE NOTICE '%','Updating PInstance - Finished - ' || v_Message ;
        PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, v_User_ID, 'Y', v_Result, v_Message);
        RETURN;


    END;
    EXCEPTION
    WHEN OTHERS THEN
        v_ResultStr:= '@ERROR=' || SQLERRM;
        RAISE NOTICE '%',v_ResultStr ;

        PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'N', 0, v_ResultStr);
        RETURN;
END ; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION no_genera_rol_provision(character varying)
  OWNER TO tad;
