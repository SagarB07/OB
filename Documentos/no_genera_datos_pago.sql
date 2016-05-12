-- Function: no_genera_datos_pago(character varying)

-- DROP FUNCTION no_genera_datos_pago(character varying);

CREATE OR REPLACE FUNCTION no_genera_datos_pago(p_pinstance_id character varying)
  RETURNS void AS
$BODY$ DECLARE 
v_ResultStr VARCHAR(2000):='';
    v_Message VARCHAR(2000):='';
    v_Record_ID VARCHAR(32);
    v_Result NUMERIC:=1;
    v_msg1 VARCHAR(2000):='Prueba';

	

    v_no_pago_cabecera_id VARCHAR(32);
	v_Client_ID VARCHAR(32);
    v_Org_ID VARCHAR(32);
    v_User_ID VARCHAR(32);
    v_period_id  VARCHAR(32);
	v_area_empresa_id VARCHAR(32);
	v_doctype_id VARCHAR(32);
    v_table_id VARCHAR(32);
	v_campo_valor VARCHAR(32):='';
    v_where varchar(500);
    v_completado varchar(500);
    qry VARCHAR(2000);
    qry1 VARCHAR(2000);
    v_bpartner_id varchar;
    v_ParameterName VARCHAR(32);

    V_PERIODO VARCHAR(32);
    V_TIPODOC VARCHAR(32);  
	V_TIPODOCQUIN VARCHAR(32);  
    
    ITERADOR RECORD;
    Cur_Parameter RECORD;

BEGIN
    RAISE NOTICE '%','Updating PInstance - Processing ' || p_PInstance_ID;
    v_ResultStr:='PInstanceNotFound';
    PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'Y', NULL, NULL);

	
	select c_doctype_ID INTO  V_TIPODOCQUIN   from c_doctype where ad_table_id  = '1895538EF5114972AC1AE7CDE5568727' and docbasetype  = 'APP';
	
    BEGIN
        FOR Cur_Parameter IN
          (SELECT i.Record_ID,
                  i.AD_User_ID,
                  p.ParameterName,
                  p.P_String,
                  p.P_Number,
                  p.P_Date,
                  i.AD_Org_ID,
                  i.AD_Client_ID
             FROM AD_PInstance i
             LEFT JOIN AD_PInstance_Para p
               ON i.AD_PInstance_ID=p.AD_PInstance_ID
            WHERE i.AD_PInstance_ID=p_PInstance_ID
            ORDER BY p.SeqNo)
        LOOP
            v_no_pago_cabecera_id:=Cur_Parameter.Record_ID;
            v_User_ID:=Cur_Parameter.AD_User_ID;
            v_Org_ID:=Cur_Parameter.AD_Org_ID;
            v_Client_ID:=Cur_Parameter.AD_Client_ID;
            v_ParameterName:=Cur_Parameter.ParameterName;
        END LOOP;

		
		--UPDATE  IDT_CONTRATO SET I_ERRORMSG = v_no_pago_cabecera_id;

        select 
        c_doctype_id, 
        c_period_id 
        INTO V_TIPODOC,
            V_PERIODO
        from no_pago_cabecera  
        where no_pago_cabecera_id = v_no_pago_cabecera_id;
        IF exists (select* from no_rol_pago_provision rocab where rocab.c_doctype_id = V_TIPODOC AND ISPAGO = 'Y' ) THEN 
		
		UPDATE  IDT_CONTRATO SET I_ERRORMSG = V_PERIODO ;
			FOR ITERADOR IN 
			(
				SELECT C_BPARTNER_ID, 
				       (select t.tablename from ad_table t 
				       where t.ad_table_id = (SELECT AD_TABLE_ID FROM c_doctype WHERE c_doctype_ID = V_TIPODOC )) as TABLENAME,
				       TOTAL_NETO, 
				       DOCUMENTNO 
				FROM no_rol_pago_provision WHERE ISPAGO = 'Y'
				AND DOCSTATUS = 'CO'				
				AND C_PERIOD_ID = V_PERIODO 
			)

			LOOP

			v_table_id:= ITERADOR.TABLENAME || '_id';
			
			INSERT 
			INTO no_pago_line 
				(no_pago_line_id,
				    ad_client_id,		 
				    ad_org_id,		
				    isactive,		
				    created,
				    createdby,
				    updated,
				    updatedby,
				    no_pago_cabecera_id,
				    c_bpartner_id,
				    c_doctype_id,
				    documentno,
				    valor,
				    estado,
				    record_id)
			VALUES (
			get_uuid(),
			v_Client_ID,
			v_Org_ID,
			'Y',
			TO_DATE(NOW()),
			v_User_ID,
			TO_DATE(NOW()),
			v_User_ID,
			v_no_pago_cabecera_id,
			ITERADOR.C_BPARTNER_ID, 
			V_TIPODOC,
			ITERADOR.DOCUMENTNO,
			ITERADOR.TOTAL_NETO,
			'BR',
			v_table_id
			);
		
 			END LOOP;
        
        END IF;

	--UPDATE  IDT_CONTRATO SET I_ERRORMSG = V_TIPODOC ; 
	--CODIGO QUEMADO DE ROL DE PROVICIONES
        IF V_TIPODOC = 'E7EC0B4879C04A9EA2807C3209E7363B' THEN 
		--UPDATE  IDT_CONTRATO SET I_ERRORMSG = 'ROL DE PROVICIONES';

      
        
        END IF;

	-- CODIGO QUEMADO QUINCENAS
        IF V_TIPODOC = V_TIPODOCQUIN THEN 
		--UPDATE  IDT_CONTRATO SET I_ERRORMSG = 'QUINCENAS';
		--SELECT * FROM no_registra_quinc_line DET
		FOR ITERADOR IN 
		(
			SELECT DET.C_BPARTNER_ID,
			(select t.tablename from ad_table t 
				       where t.ad_table_id = (SELECT AD_TABLE_ID FROM c_doctype WHERE c_doctype_ID = V_TIPODOC )) as TABLENAME,			 
			DET.VALOR,
			DET.DOCUMENTNO  
			FROM no_registra_quinc_line DET, no_registra_quincena CAB  
			WHERE CAB.no_registra_quincena_ID = DET.no_registra_quincena_ID
			AND DOCSTATUS = 'CO'
			AND CAB.C_PERIOD_ID = V_PERIODO
		)
		LOOP


		v_table_id:= ITERADOR.TABLENAME || '_id';

			INSERT 
			INTO no_pago_line 
				(no_pago_line_id,
				    ad_client_id,		 
				    ad_org_id,		
				    isactive,		
				    created,
				    createdby,
				    updated,
				    updatedby,
				    no_pago_cabecera_id,
				    c_bpartner_id,
				    c_doctype_id,
				    documentno,
				    valor,
				    estado,
				    record_id)
			VALUES (
			get_uuid(),
			v_Client_ID,
			v_Org_ID,
			'Y',
			TO_DATE(NOW()),
			v_User_ID,
			TO_DATE(NOW()),
			v_User_ID,
			v_no_pago_cabecera_id,
			ITERADOR.C_BPARTNER_ID, 
			V_TIPODOC,
			ITERADOR.DOCUMENTNO,
			ITERADOR.VALOR,
			'BR',
			v_table_id
			);
		
		
		

		END LOOP;
		
		--SELECT * FROM no_registra_quincena WHER C_PERIOD_ID = V_PERIODO;

      
        
        END IF;
        
        v_Message:='@EjecucionCorrecta@';
        
        RAISE NOTICE '%','Updating PInstance - Finished - ' || v_Message;
        PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, v_User_ID, 'Y', v_Result, v_Message);
        RETURN;
		
	END;
    EXCEPTION
    WHEN OTHERS THEN
        v_ResultStr:= '@ERROR=' || SQLERRM;
        RAISE NOTICE '%',v_ResultStr ;
                PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'N', 0, v_ResultStr);
                 UPDATE  IDT_CONTRATO SET I_ERRORMSG = v_ResultStr;
        RETURN;
END ; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION no_genera_datos_pago(character varying)
  OWNER TO tad;
