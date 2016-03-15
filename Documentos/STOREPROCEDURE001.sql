 --NO GENERA ROL
CREATE OR REPLACE FUNCTION idt_importarNovedad(p_pinstance_id character varying)
  RETURNS  void AS
$BODY$
DECLARE
v_ResultStr VARCHAR(2000):='';
    v_Message VARCHAR(2000):='';
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
	select cedula,no_tipo_ingreso_egreso_id, c_period_id, idt_novedad_id
	from idt_novedad where novprocesada = 'N'
	
	)
		
	LOOP
		SELECT COUNT(1) INTO  VAREXIST FROM C_BPARTNER WHERE TAXID = ITERADOR.CEDULA;
		
		IF (VAREXIST = 0) THEN 
			UPDATE IDT_NOVEDAD SET i_errormsg = 'ERROR: NO EXISTE LA CEDULA: ' || ITERADOR.cedula
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
			
			
		
		END IF;
	END LOOP;
	
	select COUNT (1) INTO VAREXIST from idt_novedad where i_errormsg like '%ERROR%';
	
	IF (VAREXIST = 0) THEN 
		FOR ITERADORVALIDA IN (
			SELECT DISTINCT ON (C_BPARTNER_ID) C_BPARTNER_ID FROM idt_novedad WHERE novprocesada = 'N'
		)
	
		LOOP
		
	--	SELECT SUM (VALOR) FROM IDT_NOVEDAD WHERE C_BPARTNER_ID	=ITERADORVALIDA.C_BPARTNER_ID AND novprocesada = 'N';
	
		SELECT C_BPARTNER_ID INTO VAR_PARTNER_ID FROM IDT_NOVEDAD WHERE C_BPARTNER_ID = ITERADORVALIDA.C_BPARTNER_ID LIMIT 1;
		
		SELECT NO_NOVEDAD_ID INTO VAR_CABNOVEDAD FROM NO_NOVEDAD 			
		WHERE NO_TIPO_INGRESO_EGRESO_ID = (
		SELECT NO_TIPO_INGRESO_EGRESO_ID FROM IDT_NOVEDAD WHERE C_BPARTNER_ID = ITERADORVALIDA.C_BPARTNER_ID LIMIT 1) 
		AND C_PERIOD_ID = (
		SELECT C_PERIOD_ID FROM IDT_NOVEDAD WHERE C_BPARTNER_ID = ITERADORVALIDA.C_BPARTNER_ID LIMIT 1) ;	
		
		UPDATE NO_NOVEDAD_LINEA SET 
		VALOR = (SELECT SUM (VALOR) FROM IDT_NOVEDAD WHERE C_BPARTNER_ID = ITERADORVALIDA.C_BPARTNER_ID AND novprocesada = 'N')
		WHERE NO_NOVEDAD_ID = VAR_CABNOVEDAD AND C_BPARTNER_ID = VAR_PARTNER_ID;
		
		DELETE FROM IDT_NOVEDAD  WHERE C_BPARTNER_ID	= ITERADORVALIDA.C_BPARTNER_ID;
		
		
		END LOOP;
	
	
	
	
	VARVALIDADO = TRUE;
	ELSE
	
	VARVALIDADO = FALSE;

	END IF;
	
	
	
	
  
  RETURN;
   EXCEPTION
    WHEN OTHERS THEN
        v_ResultStr:= '@ERROR=' || SQLERRM;
        RAISE NOTICE '%',v_ResultStr ;

        PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'N', 0, v_ResultStr);
        RETURN;
END;

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

  

