-- Function: no_procesarquincenanovedad(character varying)

-- DROP FUNCTION no_procesarquincenanovedad(character varying);

CREATE OR REPLACE FUNCTION no_procesarquincenanovedad(p_pinstance_id character varying)
  RETURNS void AS
$BODY$ DECLARE 
ITERADORCAB  RECORD;
ITERADORDET  RECORD;
ITERADOR  RECORD;

VAREXIST NUMERIC;
v_Message VARCHAR(2000):='';
v_ResultStr VARCHAR(2000):='';
v_Novedad VARCHAR(32):='';
v_Novedad_Linea VARCHAR(32):='';
v_Periodo VARCHAR(32):='';
v_Rubro VARCHAR(32):='';

BEGIN
--RAISE NO_DATA_FOUND;
 UPDATE IDT_CONTRATO SET i_errormsg = p_pinstance_id;
 
 FOR ITERADORCAB IN 
 (	
	
	SELECT DISTINCT ON (no_registra_quincena_id) no_registra_quincena_id  FROM no_registra_quinc_line WHERE docstatus ='CO' AND no_registra_quinc_line_ID = p_pinstance_id
 )
 LOOP
	--UPDATE IDT_CONTRATO SET i_errormsg = '1 ITERADOR';
	FOR ITERADORDET IN 
	 (	
		SELECT DISTINCT ON (no_tipo_ingreso_egreso_id) no_tipo_ingreso_egreso_id  FROM no_registra_quinc_line WHERE  no_registra_quinc_line_ID = p_pinstance_id AND docstatus ='CO' and no_registra_quincena_id = ITERADORCAB.no_registra_quincena_id
	 )
	 LOOP
	--	UPDATE IDT_CONTRATO SET i_errormsg = '2 ITERADOR';
		
		FOR ITERADOR IN 
		 (	
			SELECT no_registra_quincena_id, no_tipo_ingreso_egreso_id, c_bpartner_id, ad_client_id, ad_org_id, createdby, updatedby, valor
			FROM no_registra_quinc_line 
			WHERE docstatus ='CO' 
			and no_registra_quincena_id = ITERADORCAB.no_registra_quincena_id 
			AND no_registra_quinc_line_ID = p_pinstance_id
			AND no_tipo_ingreso_egreso_id = ITERADORDET.no_tipo_ingreso_egreso_id
		 )
		 LOOP

		--- UPDATE IDT_CONTRATO SET i_errormsg = '3 ITERADOR.';
		 v_Periodo = ( SELECT c_period_id  FROM no_registra_quincena WHERE no_registra_quincena_id = ITERADOR.no_registra_quincena_id );
		
  		 SELECT COUNT (*) INTO VAREXIST FROM NO_NOVEDAD 
		 WHERE no_tipo_ingreso_egreso_id = ITERADOR.no_tipo_ingreso_egreso_id 
		 AND c_period_id = v_Periodo;
		 		
		 --UPDATE IDT_CONTRATO SET i_errormsg = v_Periodo;
		 v_Rubro = ITERADOR.no_tipo_ingreso_egreso_id;
		 
		 IF VAREXIST = 0 THEN
			--	 UPDATE IDT_CONTRATO SET i_errormsg = '000000';
				 INSERT INTO NO_NOVEDAD 
					(no_novedad_id,
					ad_client_id, 
					ad_org_id, 
					isactive, 
					created, 
					createdby,
					updated, 
					updatedby,
					no_tipo_ingreso_egreso_id,
					c_period_id
					)
				VALUES 
					(get_uuid(),
					ITERADOR.ad_client_id, 
					ITERADOR.ad_org_id, 
					'Y', 
					TO_DATE(NOW()),
					ITERADOR.createdby,
					TO_DATE(NOW()),
					ITERADOR.updatedby,
					v_Rubro,
				    v_Periodo
					);

				SELECT COUNT (*) INTO VAREXIST FROM NO_NOVEDAD WHERE no_tipo_ingreso_egreso_id = v_Rubro  and c_period_id = v_Periodo;
				--UPDATE IDT_CONTRATO SET i_errormsg = '11' ;
				 
						 IF VAREXIST > 0 THEN 
						 v_Novedad =   (SELECT NO_NOVEDAD_ID FROM NO_NOVEDAD WHERE no_tipo_ingreso_egreso_id = v_Rubro  and c_period_id = v_Periodo);
						-- UPDATE IDT_CONTRATO SET i_errormsg = v_Novedad ;
						 INSERT INTO no_novedad_linea 
								(no_novedad_linea_id,
								  ad_client_id ,
								  ad_org_id ,
								  isactive ,
								  created ,
								  createdby ,
								  updated ,
								  updatedby ,
								  c_bpartner_id ,
								  valor ,
								  no_novedad_id   
								)
							VALUES 
								(get_uuid(),
								ITERADOR.ad_client_id, 
								ITERADOR.ad_org_id, 
								'Y', 
								TO_DATE(NOW()),
								ITERADOR.createdby,
								TO_DATE(NOW()),
								ITERADOR.updatedby,
								ITERADOR.c_bpartner_id,
								ITERADOR.valor,
								v_Novedad
								);	 
							
						
						 END IF;		 
		 
		 ELSE
		 
		--UPDATE IDT_CONTRATO SET i_errormsg = '1xxxxxxxxxx' ;

		v_Novedad =   (SELECT NO_NOVEDAD_ID FROM NO_NOVEDAD WHERE no_tipo_ingreso_egreso_id = v_Rubro  and c_period_id = v_Periodo);


		SELECT COUNT (*) INTO VAREXIST  FROM no_novedad_linea WHERE NO_NOVEDAD_ID = v_Novedad AND c_bpartner_id = ITERADOR.c_bpartner_id;
		
		--UPDATE IDT_CONTRATO SET i_errormsg =  VAREXIST ;
			 

		IF VAREXIST = 0 THEN 			
			--UPDATE IDT_CONTRATO SET i_errormsg = '1xxxxxxxxxx' ;
			    INSERT INTO no_novedad_linea 
					(no_novedad_linea_id,
					  ad_client_id ,
					  ad_org_id ,
					  isactive ,
					  created ,
					  createdby ,
					  updated ,
					  updatedby ,
					  c_bpartner_id ,
					  valor ,
					  no_novedad_id   
					)
				VALUES 
					(get_uuid(),
					ITERADOR.ad_client_id, 
					ITERADOR.ad_org_id, 
					'Y', 
					TO_DATE(NOW()),
					ITERADOR.createdby,
					TO_DATE(NOW()),
					ITERADOR.updatedby,
					ITERADOR.c_bpartner_id,
					ITERADOR.valor,
					v_Novedad
					);

--		        DELETE FROM no_novedad_linea  WHERE no_novedad_id = v_Novedad AND ITERADOR.valor = 0.99;

			ELSE 
			--UPDATE IDT_CONTRATO SET i_errormsg = 'YYYYYY' ;
			v_Novedad_Linea = (SELECT no_novedad_linea_id FROM no_novedad_linea WHERE NO_NOVEDAD_ID = v_Novedad AND c_bpartner_id = ITERADOR.c_bpartner_id);

			UPDATE NO_NOVEDAD_LINEA SET 
			VALOR = ITERADOR.valor
			WHERE no_novedad_linea_id = v_Novedad_Linea;

			END IF ;			 
		 
		 
		 
		 END IF;
		 
		 
		 END LOOP;
	 
	 END LOOP;
 END LOOP;
     
  
RETURN;
   EXCEPTION
    WHEN OTHERS THEN
        v_ResultStr:= '@ERROR=' || SQLERRM;        
	RAISE NOTICE '%','Updating PInstance - Finished - ' || v_Message;
	--UPDATE IDT_CONTRATO SET i_errormsg = v_ResultStr;
	--UPDATE IDT_CONTRATO SET i_errormsg = 'OPOPOP' ;
        PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, '100', 'Y', 0, v_Message);
        PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'N', 0, v_ResultStr);
    RETURN;
END ; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION no_procesarquincenanovedad(character varying)
  OWNER TO tad;
