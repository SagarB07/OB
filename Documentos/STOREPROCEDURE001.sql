--delete from idt_novedad

 --NO GENERA ROL
CREATE OR REPLACE FUNCTION idt_importarNovedad(p_pinstance_id character varying)
  RETURNS  void AS
$BODY$
DECLARE
v_Message VARCHAR(2000):='';
v_ResultStr VARCHAR(2000):='';
v_Result NUMERIC:=1;
v_Record_ID VARCHAR(32);
VAR_CEDULA character varying(32);
VAR_CABNOVEDAD character varying(32);
VAR_PARTNER_ID character varying(32);
VAREXIST NUMERIC;
VARVALIDADO BOOLEAN;
VAREXISTNOV NUMERIC;
ITERADOR  RECORD;
ITERADORVALIDA RECORD; 

BEGIN

 RAISE NOTICE '%','Updating PInstance - Processing ' || p_PInstance_ID;
    v_ResultStr:='PInstanceNotFound';
    PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'Y', NULL, NULL);
VARVALIDADO = FALSE;

	FOR ITERADOR IN 
	(
	select C_BPARTNER_ID, cedula,no_tipo_ingreso_egreso_id, c_period_id, idt_novedad_id, VALOR
	from idt_novedad where novprocesada = 'N'
	
	)
		
	LOOP
		SELECT COUNT(1) INTO  VAREXIST FROM C_BPARTNER WHERE TAXID = ITERADOR.CEDULA and isemployee = 'Y';
		
		IF ITERADOR.VALOR <= 0 THEN 
			UPDATE IDT_NOVEDAD SET i_errormsg = 'ERROR: EL VALOR DEBE SER MAYOR A 0: ' || ITERADOR.cedula
			WHERE idt_novedad_id = ITERADOR.idt_novedad_id;	
						
		ELSE
	
			IF (VAREXIST = 0) THEN 
							UPDATE IDT_NOVEDAD SET i_errormsg = 'ERROR: NO EXISTE LA CEDULA: ' || ITERADOR.cedula
							WHERE idt_novedad_id = ITERADOR.idt_novedad_id;
						
						ELSE
							
							select COUNT (1) INTO VAREXIST  from no_contrato_empleado where  C_BPARTNER_ID =  (SELECT C_BPARTNER_ID FROM C_BPARTNER WHERE TAXID = ITERADOR.CEDULA  )
							and isactive = 'Y' 
							and fecha_inicio <= (select startdate from c_period where c_period_id = (
							select c_period_id from idt_novedad where idt_novedad_id = ITERADOR.idt_novedad_id))  
							and fecha_fin >= (select enddate from c_period where c_period_id = (
							select c_period_id from idt_novedad where idt_novedad_id = ITERADOR.idt_novedad_id)) ;
							
								IF (VAREXIST = 0) THEN
								
								UPDATE IDT_NOVEDAD SET i_errormsg = 'ERROR: NO EXISTE CONTRATO PARA ACTIVO O LAS FECHAS DEL PERIODO SON INCORRECTAS: ' || ITERADOR.CEDULA  || ' NOVEDAD ID: ' || ITERADOR.idt_novedad_id
								WHERE idt_novedad_id = ITERADOR.idt_novedad_id;						
								ELSE
								
									UPDATE IDT_NOVEDAD SET i_errormsg = NULL, C_BPARTNER_ID = (SELECT C_BPARTNER_ID FROM C_BPARTNER WHERE TAXID = ITERADOR.CEDULA)
									WHERE idt_novedad_id = ITERADOR.idt_novedad_id;
									
									SELECT COUNT(1) INTO VAREXISTNOV FROM NO_NOVEDAD 			
									WHERE NO_TIPO_INGRESO_EGRESO_ID = ITERADOR.no_tipo_ingreso_egreso_id AND C_PERIOD_ID = ITERADOR.c_period_id;
									
									IF (VAREXISTNOV = 0) THEN
										UPDATE IDT_NOVEDAD SET i_errormsg = 'ERROR: AL BUSCAR RUBRO Y SU NOVEDAD, POR LA CEDULA: ' || ITERADOR.cedula
										WHERE idt_novedad_id = ITERADOR.idt_novedad_id;
									ELSE 
										UPDATE IDT_NOVEDAD SET i_errormsg = NULL
										WHERE idt_novedad_id = ITERADOR.idt_novedad_id;
									END IF;
								
								END IF ;			
						END IF;			
						
				END IF;		
	END LOOP;
	
	--UPDATE IDT_NOVEDAD SET i_errormsg = 'xxxx';
	select COUNT (1) INTO VAREXIST from idt_novedad where i_errormsg like '%ERROR%';
	
	IF (VAREXIST = 0) THEN 
		FOR ITERADORVALIDA IN (
			SELECT DISTINCT ON (C_BPARTNER_ID) C_BPARTNER_ID FROM idt_novedad WHERE novprocesada = 'N'
		)
	
		LOOP
			
			
			SELECT C_BPARTNER_ID INTO VAR_PARTNER_ID FROM IDT_NOVEDAD WHERE C_BPARTNER_ID = ITERADORVALIDA.C_BPARTNER_ID LIMIT 1;
			
			SELECT NO_NOVEDAD_ID INTO VAR_CABNOVEDAD FROM NO_NOVEDAD 			
			WHERE NO_TIPO_INGRESO_EGRESO_ID = (
			SELECT NO_TIPO_INGRESO_EGRESO_ID FROM IDT_NOVEDAD WHERE C_BPARTNER_ID = ITERADORVALIDA.C_BPARTNER_ID LIMIT 1) 
			AND C_PERIOD_ID = (
			SELECT C_PERIOD_ID FROM IDT_NOVEDAD WHERE C_BPARTNER_ID = ITERADORVALIDA.C_BPARTNER_ID LIMIT 1) ;	
			
			select COUNT (1) INTO VAREXIST from NO_NOVEDAD_LINEA WHERE NO_NOVEDAD_ID = VAR_CABNOVEDAD AND C_BPARTNER_ID = VAR_PARTNER_ID;
			
			IF (VAREXIST = 0) THEN  
			
				UPDATE IDT_NOVEDAD SET i_errormsg = 'ERROR: NO EXISTE LA PERSONA EN EN LA TABLA DE REGISTRO DE RUBROS.: ' || VAR_CABNOVEDAD
				WHERE idt_novedad_id = (SELECT idt_novedad_id FROM idt_novedad WHERE C_BPARTNER_ID	= ITERADORVALIDA.C_BPARTNER_ID LIMIT 1 );	
			
			ELSE 
			
				UPDATE NO_NOVEDAD_LINEA SET 
				VALOR = (SELECT SUM (VALOR) FROM IDT_NOVEDAD WHERE C_BPARTNER_ID = ITERADORVALIDA.C_BPARTNER_ID AND novprocesada = 'N')
				WHERE NO_NOVEDAD_ID = VAR_CABNOVEDAD AND C_BPARTNER_ID = VAR_PARTNER_ID;		
				DELETE FROM IDT_NOVEDAD  WHERE C_BPARTNER_ID	= ITERADORVALIDA.C_BPARTNER_ID;

			END IF ;
	
		END LOOP;
	
	VARVALIDADO = TRUE;
	ELSE
	
	VARVALIDADO = FALSE;

	END IF;
	--IDT_EjecucionError
	
	if VARVALIDADO = true then 
	v_Message:='@IDT_EjecucionCorrecta@';
	 RAISE NOTICE '%','Updating PInstance - Finished - ' || v_Message;
     PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, '100', 'Y', 1, v_Message);
	  else 
	  v_Message:='@IDT_EjecucionError@';
	  RAISE NOTICE '%','Updating PInstance - Finished - ' || v_Message;
     PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, '100', 'Y', 0, v_Message);
	 end if;
	  
      
  
  RETURN;
   EXCEPTION
    WHEN OTHERS THEN
        v_ResultStr:= '@ERROR=' || SQLERRM;
        
		RAISE NOTICE '%','Updating PInstance - Finished - ' || v_Message;
      PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, '100', 'Y', 0, v_Message);
		

        PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'N', 0, v_ResultStr);
        RETURN;
END;

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

  

