-- Function: no_genera_rol(character varying)

-- DROP FUNCTION no_genera_rol(character varying);

CREATE OR REPLACE FUNCTION no_genera_rol(p_pinstance_id character varying)
  RETURNS void AS
$BODY$ DECLARE 
v_ResultStr VARCHAR(2000):='';
    v_Message VARCHAR(2000):='';
    v_Result NUMERIC:=1;
    v_Record_ID VARCHAR(32);

    Cur_Parameter RECORD;
    Cur_Empleado RECORD;
    Cur_Rubro_Rol RECORD;
    Cur_Calcula_Robro_L RECORD;

    v_co_genera_rol_id VARCHAR(32);
    v_Client_ID VARCHAR(32);
    v_Org_ID VARCHAR(32);
    v_Org_Emp_ID VARCHAR(32);
    v_User_ID VARCHAR(32);

    v_rol_pago_provision_id VARCHAR(32);
    v_rol_pago_prov_line_id VARCHAR(32);
    v_line NUMERIC;

    v_doctype_id VARCHAR(32);
    v_documentno VARCHAR;
    v_period_id VARCHAR;

    v_qry varchar;
    v_valor_rubro NUMERIC:=0;
    v_valor_aux NUMERIC:=0;
    v_salario NUMERIC:=0;
    
    v_anio_inicio NUMERIC:=0;
    v_mes_inicio NUMERIC:=0;
    v_dia_inicio NUMERIC:=0;
    
    v_anio_fin NUMERIC:=0;
    v_mes_fin NUMERIC:=0;
    v_dia_fin NUMERIC:=0;
    
    v_anio_actual NUMERIC:=0;
    v_mes_actual NUMERIC:=0;
    
    v_dias_mes NUMERIC:=0;
    v_dias_div NUMERIC:=0;
    v_dias_trabajo NUMERIC:=0;
    v_dias_trabajo_fin NUMERIC:=0;
    v_date_acct TIMESTAMP;
    
    v_suma_valor NUMERIC:= 0;
    v_inicio_periodo  TIMESTAMP;
    v_fin_periodo  TIMESTAMP;
    v_es_ing_egr VARCHAR;

BEGIN
    
	
    RAISE NOTICE '%','Updating PInstance - Processing ' || p_PInstance_ID;
    v_ResultStr:='PInstanceNotFound';
    PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'Y', NULL, NULL);

    BEGIN
	

        FOR Cur_Parameter IN
          (SELECT i.Record_ID,
                  i.AD_User_ID,
                  i.AD_Client_ID,
                  i.ad_org_id,
                  p.ParameterName,
                  p.P_String,
                  p.P_Number,
                  p.P_Date
             FROM AD_PInstance i
             LEFT JOIN AD_PInstance_Para p
               ON i.AD_PInstance_ID=p.AD_PInstance_ID
            WHERE i.AD_PInstance_ID=p_PInstance_ID
            ORDER BY p.SeqNo)
        LOOP
            v_co_genera_rol_id:=Cur_Parameter.Record_ID;
            v_User_ID:=Cur_Parameter.AD_User_ID;
            v_Client_ID:=Cur_Parameter.AD_Client_ID;
            v_Org_ID:=Cur_Parameter.AD_Org_ID;
	    v_period_ID:=Cur_Parameter.P_String; 
        END LOOP;

	    v_inicio_periodo  := (select date(cp.startdate) from c_period cp where cp.c_period_id = v_period_ID);
        v_fin_periodo     := (select date(cp.enddate) from c_period cp where cp.c_period_id = v_period_ID);

        

        FOR Cur_Empleado IN
        (select bp.c_bpartner_id, 
                ce.fecha_inicio, 
                ce.fecha_fin,
                ce.ad_org_id
           from c_bpartner bp,
                no_contrato_empleado ce
          where bp.c_bpartner_id = ce.c_bpartner_id
            and bp.isemployee = 'Y'
            and bp.isactive = 'Y'
            and bp.AD_Client_ID=v_Client_ID
            
            and ce.isactive='Y'
            and (date_trunc('month', ce.fecha_fin::DATE) >= date_trunc('month',TO_DATE(NOW())::DATE)
                or
                date_trunc('month', ce.fecha_fin::DATE) >= date_trunc('month',TO_DATE((TO_DATE(NOW())::DATE - interval '1 month'))::DATE)
                )
            and date(ce.fecha_inicio) <= date(v_fin_periodo)
            )
        LOOP

        
            v_Org_Emp_ID:=Cur_Empleado.ad_org_id;
            select get_uuid() into v_rol_pago_provision_id;
            select dt.c_doctype_id into v_doctype_id
              from c_doctype dt
             where dt.docbasetype = 'NO_NRP'
               and ad_client_id = v_Client_ID;  


	    v_date_acct := (select enddate from c_period where c_period_id = v_period_id);

	--delete from no_rol_provision_line_mes;
	--delete from no_rol_pago_provision_line;
	--delete from no_rol_pago_provision;

 	update idt_contrato set i_errormsg = 'hol¿dkwei'; 
-- 	select * from c_period  where c_period_id = 'B07427C234E2464E83C6364B232D5A05';
-- 	select * from no_rol_pago_provision 
-- 	where ispago='Y' and docstatus<>'AN' 
-- 	and c_period_id= 'B07427C234E2464E83C6364B232D5A05'
-- 	and c_bpartner_id= 'CCF2CFF9B461447E9B6487C9DA57E6C3';
	    
            IF NOT EXISTS (select * from no_rol_pago_provision where ispago='Y' and docstatus<>'AN' and c_period_id=v_period_id and c_bpartner_id=Cur_Empleado.c_bpartner_id) THEN

	    
                SELECT * INTO  v_documentno FROM ad_sequence_doctype(v_doctype_id, '', 'Y');

                INSERT INTO no_rol_pago_provision
                            (no_rol_pago_provision_id,   ad_client_id,       ad_org_id,          isactive,
                             created,                    createdby,          updated,            updatedby,
                             c_bpartner_id,              c_period_id,        c_doctype_id,       documentno,
                             total_ingreso,              total_egreso,       total_neto,         docstatus,
                             ispago,                     processed,          docaccionno,        dateacct, 
                             processing,                 posted)
                      VALUES(v_rol_pago_provision_id,    v_Client_ID,        v_Org_Emp_ID,           'Y',
                             current_timestamp,          v_User_ID,          current_timestamp,  v_User_ID,
                             Cur_Empleado.c_bpartner_id, v_period_id,        v_doctype_id,       v_documentno,
                             0,                          0,                  0,                  'BR',
                             'Y',                        'N',                'CO',       		 v_date_acct,
                             'N',                        'N');

                FOR Cur_Rubro_Rol IN
                   (select cr.no_calcula_rubro_id,
		           iee.no_tipo_ingreso_egreso_id,
		           tie.isavance,
		           tie.suma_ingreso, 
		           iee.c_bpartner_id,
		           iee.c_acctschema_id,
		           iee.no_cuenta_ingreso_acct,
		           iee.no_cuenta_egreso_acct,
		           tie.isingreso
		  from no_cb_empleado_acct iee,
		       no_tipo_ingreso_egreso tie,
		       no_calcula_rubro cr  
		 where iee.c_bpartner_id = Cur_Empleado.c_bpartner_id
		   and tie.no_tipo_ingreso_egreso_id = iee.no_tipo_ingreso_egreso_id
		   and cr.no_calcula_rubro_id = tie.no_calcula_rubro_id
		   and tie.isactive ='Y'
		   and iee.isactive = 'Y'
		   and cr.isactive = 'Y'
		   and tie.isprovision = 'N'
		 order by suma_ingreso desc, tie.isingreso asc)
                LOOP

                select get_uuid() into v_rol_pago_prov_line_id;

                CASE WHEN Cur_Rubro_Rol.isingreso = 'Y' THEN
			v_es_ing_egr = 'IN';
		     WHEN  Cur_Rubro_Rol.isingreso = 'N' THEN
			v_es_ing_egr = 'EG';
		     ELSE
			v_es_ing_egr = NULL;
		END CASE;

                select coalesce(max(line),0)+10 into v_line
                  from no_rol_pago_provision_line
                 where no_rol_pago_provision_id = v_rol_pago_provision_id;

                v_valor_rubro:=0;

                IF Cur_Rubro_Rol.isavance = 'Y' THEN
                    select coalesce(sum(rql.valor),0) into v_valor_rubro
                      from no_registra_quincena rq, 
                           no_registra_quinc_line rql
                     where rq.no_registra_quincena_id = rql.no_registra_quincena_id
                       and rql.docstatus='CO'
                       and rq.c_period_id = v_period_id
                       and rql.c_bpartner_id = Cur_Empleado.c_bpartner_id
                       and rql.no_tipo_ingreso_egreso_id = Cur_Rubro_Rol.no_tipo_ingreso_egreso_id
                       and rql.processed = 'Y';

                       
                
                    update no_registra_quinc_line
                     set include_rol_pago = 'Y'
                     where c_bpartner_id = Cur_Empleado.c_bpartner_id
                     and processed = 'Y'
                     and no_tipo_ingreso_egreso_id = Cur_Rubro_Rol.no_tipo_ingreso_egreso_id;
                ELSE

                    FOR Cur_Calcula_Robro_L IN
                    (select cr.nombre,
                            crl.isprocedure,
                            crl.issalario,
                            crl.isporcentaje,
                            crl.valor,
                            crl.procedure
                       from no_calcula_rubro cr,
                            no_calcula_rubro_line crl
                      where cr.no_calcula_rubro_id = crl.no_calcula_rubro_id
                        and cr.no_calcula_rubro_id = Cur_Rubro_Rol.no_calcula_rubro_id
                      order by crl.line)
                    LOOP

                        IF Cur_Calcula_Robro_L.isprocedure = 'Y' THEN
                            IF Cur_Calcula_Robro_L.procedure is null OR Cur_Calcula_Robro_L.procedure = '' THEN
                                
                                v_Message:='No existe el procedimiento de calculo para este rubro: ' || Cur_Calcula_Robro_L.nombre;
                                
                                v_valor_rubro := 0;
                            ELSE
                                v_qry := 'SELECT ' || Cur_Calcula_Robro_L.procedure || '('''|| Cur_Empleado.c_bpartner_id ||''' ,'''||Cur_Rubro_Rol.no_tipo_ingreso_egreso_id || ''',' || v_suma_valor||','''||v_period_id || ''');';
                                EXECUTE v_qry into v_valor_aux;
                                v_valor_rubro := v_valor_rubro + COALESCE(v_valor_aux,0);
                                v_Message:='';
                            END IF; 
                        
                        ELSE
                            IF Cur_Calcula_Robro_L.issalario = 'Y' THEN
                                select salario into v_salario
                                  from no_contrato_empleado
                                 where c_bpartner_id is not null
                                   and isactive = 'Y'
                                   and (date_trunc('month', fecha_fin) >= date_trunc('month', statement_timestamp())
					or
					date_trunc('month', fecha_fin::DATE) >= date_trunc('month',TO_DATE((TO_DATE(NOW())::DATE - interval '1 month'))::DATE)
					)
                                   and c_bpartner_id=Cur_Empleado.c_bpartner_id;

                                
                                select extract(year from (Cur_Empleado.fecha_inicio)) into v_anio_inicio;
                                select extract(month from (Cur_Empleado.fecha_inicio)) into v_mes_inicio;
                                select extract(day from (Cur_Empleado.fecha_inicio)) into v_dia_inicio;

                                select extract(year from (Cur_Empleado.fecha_fin)) into v_anio_fin;
                                select extract(month from (Cur_Empleado.fecha_fin)) into v_mes_fin;
                                select extract(day from (Cur_Empleado.fecha_fin)) into v_dia_fin;

                                select extract(year from (TO_DATE(NOW()))) into v_anio_actual;
                                select extract(month from (TO_DATE(NOW()))) into v_mes_actual;

                                IF(v_anio_inicio = v_anio_actual AND (v_mes_inicio = v_mes_actual or v_mes_inicio = (v_mes_actual - 1))) THEN 
                                                                        v_dias_mes := 30;
                                    v_dias_trabajo:= v_dias_mes - (v_dia_inicio - 1);
                                    v_salario:= (v_salario / v_dias_mes) * v_dias_trabajo;

                                    
                                    IF(v_anio_fin = v_anio_actual AND (v_mes_fin = v_mes_actual or v_mes_fin = (v_mes_actual - 1))) THEN 
                                                                                v_dias_mes := 30;
                                        v_dias_trabajo_fin:= v_dia_fin - v_dia_inicio;
                                        v_salario := (v_salario/v_dias_trabajo)*v_dias_mes;
                                        v_salario:= (v_salario / v_dias_mes) * v_dias_trabajo_fin;
                                    END IF;
                                    
                                
                                ELSIF(v_anio_fin = v_anio_actual AND (v_mes_fin = v_mes_actual or v_mes_fin = (v_mes_actual - 1))) THEN 
                                                                        v_dias_mes := 30;
                                    v_dias_trabajo:= v_dia_fin;
                                    v_salario:= (v_salario / v_dias_mes) * v_dias_trabajo;
                                    
                                END IF;
                                
                                   
                                IF Cur_Calcula_Robro_L.isporcentaje = 'Y' THEN
                                    v_valor_rubro := v_valor_rubro + (COALESCE(v_salario,0) * COALESCE(Cur_Calcula_Robro_L.valor,0)) / 100;
                                ELSE
                                    v_valor_rubro := COALESCE(v_salario,0);
                                END IF;
                            ELSE
                                v_valor_rubro := v_valor_rubro + COALESCE(Cur_Calcula_Robro_L.valor,0);
                            END IF;
                        END IF;
                        
                    END LOOP;
                END IF;
				
				IF v_valor_rubro> 0 THEN 
                INSERT INTO no_rol_pago_provision_line
                            (no_rol_pago_provision_line_id,        ad_client_id,       			ad_org_id,                                  isactive,
                             created,                              createdby,          			updated,                                    updatedby,
                             no_rol_pago_provision_id,             line,               			no_tipo_ingreso_egreso_id,                  valor, 
                             em_ne_observacion,                    c_acctschema_id,    			no_cuenta_ingreso_acct,                     no_cuenta_egreso_acct,
                             em_ne_tipo_rubro)
                      VALUES(v_rol_pago_prov_line_id,              v_Client_ID,        			v_Org_Emp_ID,                                   'Y',
                             current_timestamp,                    v_User_ID,          			current_timestamp,                          v_User_ID,
                             v_rol_pago_provision_id,              v_line,             			Cur_Rubro_Rol.no_tipo_ingreso_egreso_id,    round(v_valor_rubro,2),
                             v_Message,                            Cur_Rubro_Rol.c_acctschema_id,       Cur_Rubro_Rol.no_cuenta_ingreso_acct,       Cur_Rubro_Rol.no_cuenta_egreso_acct,
                             v_es_ing_egr);
				END IF;

               IF v_Message = '' THEN
                    UPDATE no_rol_pago_provision 
                       SET em_ne_observacion = ''
                     WHERE no_rol_pago_provision_id = v_rol_pago_provision_id;
               ELSE
                    UPDATE no_rol_pago_provision 
                       SET em_ne_observacion = 'ERROR'
                     WHERE no_rol_pago_provision_id = v_rol_pago_provision_id;
               END IF;

            IF Cur_Rubro_Rol.suma_ingreso = 'Y' THEN
                v_suma_valor := round(v_suma_valor,2) + v_valor_rubro;
            END IF;

            END LOOP;
            END IF;
            v_suma_valor :=0;
        END LOOP;

        v_Message:='@NO_EjecucionCorrecta@';
        RAISE NOTICE '%','Updating PInstance - Finished - ' || v_Message;
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
ALTER FUNCTION no_genera_rol(character varying)
  OWNER TO tad;
