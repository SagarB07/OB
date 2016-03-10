 --NO GENERA ROL
CREATE OR REPLACE FUNCTION i_importarNovedad()
  RETURNS  Boolean AS
$BODY$
DECLARE

VAR_CEDULA character varying(32);
VAREXIST NUMERIC;
ITERADOR  RECORD;

BEGIN

	FOR ITERADOR IN 
	(
	select cedula,no_tipo_ingreso_egreso_id, c_period_id, idt_novedad_id
	from idt_novedad where processed = 'N'
	
	)
		
	LOOP
		SELECT COUNT(1) INTO  VAREXIST FROM C_BPARTNER WHERE TAXID = ITERADOR.CEDULA;
		
		IF (VAREXIST = 0) THEN 
			UPDATE IDT_NOVEDAD SET i_errormsg = 'Error no existe tercero con el numero de cedula' || ITERADOR.cedula
			WHERE idt_novedad_id = ITERADOR.idt_novedad_id;
		
		ELSE
			UPDATE IDT_NOVEDAD SET i_errormsg = NULL, C_BPARTNER_ID = (SELECT C_BPARTNER_ID FROM C_BPARTNER WHERE TAXID = ITERADOR.CEDULA)
			WHERE idt_novedad_id = ITERADOR.idt_novedad_id;
		
		END IF;
	END LOOP;
  
  RETURN TRUE;
END;

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

  

