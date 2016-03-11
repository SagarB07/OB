 --NO GENERA ROL
CREATE OR REPLACE FUNCTION i_importarNovedad()
  RETURNS  Boolean AS
$BODY$
DECLARE

VAR_CEDULA character varying(32);
VAR_CABNOVEDAD character varying(32);
VAR_PARTNER_ID character varying(32);

VAREXIST NUMERIC;
VARVALIDADO BOOLEAN;
VAREXISTNOV NUMERIC;
ITERADOR  RECORD;
ITERADORVALIDA RECORD; 

BEGIN
VARVALIDADO = FALSE;

	FOR ITERADOR IN 
	(
	select cedula,no_tipo_ingreso_egreso_id, c_period_id, idt_novedad_id
	from idt_novedad where processed = 'N'
	
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
			SELECT DISTINCT ON (C_BPARTNER_ID) C_BPARTNER_ID FROM idt_novedad WHERE processed = 'N'
		)
	
		LOOP
		
	--	SELECT SUM (VALOR) FROM IDT_NOVEDAD WHERE C_BPARTNER_ID	=ITERADORVALIDA.C_BPARTNER_ID AND processed = 'N';
	
		SELECT C_PERIOD_ID INTO VAR_PARTNER_ID FROM IDT_NOVEDAD WHERE C_BPARTNER_ID = ITERADORVALIDA.C_BPARTNER_ID LIMIT 1;
		
		SELECT NO_NOVEDAD_ID INTO VAR_CABNOVEDAD FROM NO_NOVEDAD 			
		WHERE NO_TIPO_INGRESO_EGRESO_ID = (
		SELECT NO_TIPO_INGRESO_EGRESO_ID FROM IDT_NOVEDAD WHERE C_BPARTNER_ID = ITERADORVALIDA.C_BPARTNER_ID LIMIT 1) 
		AND C_PERIOD_ID = (
		SELECT C_PERIOD_ID FROM IDT_NOVEDAD WHERE C_BPARTNER_ID = ITERADORVALIDA.C_BPARTNER_ID LIMIT 1) ;	
		
		UPDATE NO_NOVEDAD_LINEA SET 
		VALOR = (SELECT SUM (VALOR) FROM IDT_NOVEDAD WHERE C_BPARTNER_ID = ITERADORVALIDA.C_BPARTNER_ID AND processed = 'N')
		WHERE NO_NOVEDAD_ID = VAR_CABNOVEDAD AND C_BPARTNER_ID = VAR_PARTNER_ID;
		
		UPDATE IDT_NOVEDAD SET processed = 'Y' WHERE C_BPARTNER_ID	= ITERADORVALIDA.C_BPARTNER_ID;
		
		
		END LOOP;
	
	
	
	
	VARVALIDADO = TRUE;
	ELSE
	
	VARVALIDADO = FALSE;

	END IF;
	
	
	
	
  
  RETURN VARVALIDADO;
END;

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

  
