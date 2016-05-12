-- Function: ne_vacacion_val_trg()

-- DROP FUNCTION ne_vacacion_val_trg();

CREATE OR REPLACE FUNCTION ne_vacacion_val_trg()
  RETURNS trigger AS
$BODY$ DECLARE 

	v_days NUMERIC;
	v_linea NUMERIC;
	v_fecha_inicio timestamp without time zone;
	v_fecha_fin timestamp without time zone;
	v_em_ne_fecha_inicio timestamp without time zone;
	v_em_ne_fecha_fin timestamp without time zone;
BEGIN

--UPDATE idt_contrato SET i_errormsg = '00***' ;

	select fecha_inicio, fecha_fin
			into v_fecha_inicio, v_fecha_fin
			from no_contrato_empleado
			where no_contrato_empleado_id = :new.no_contrato_empleado_id;
			
			

	IF TG_OP = 'INSERT' THEN
	
	
		if date(:new.em_ne_fecha_inicio) < date(v_fecha_inicio) then
				raise '%', '@NE_FECHA_VAC_VAL@';
			end if;

			if date(:new.em_ne_fecha_fin) > date(v_fecha_fin) then
				raise '%', '@NE_FECHA_VAC_VAL@';
			end if;
	
 	if date(new.em_ne_fecha_fin) <= date(new.em_ne_fecha_inicio) then
 	raise exception '%', '@NE_NO_VACACION_FECHA_VAL@';
 	else
 	v_days := DATE_PART('day', new.em_ne_fecha_fin::timestamp - new.em_ne_fecha_inicio::timestamp);
 	new.em_ne_total_dias=to_number(v_days + 1);
 	end if;
	return new; 

	
	 ELSIF TG_OP = 'UPDATE' THEN
	 
	 if date(:new.em_ne_fecha_inicio) < date(v_fecha_inicio) then
				raise '%', '@NE_FECHA_VAC_VAL@';
			end if;

			if date(:new.em_ne_fecha_fin) > date(v_fecha_fin) then
				raise '%', '@NE_FECHA_VAC_VAL@';
			end if;


		if date(new.em_ne_fecha_fin) <= date(new.em_ne_fecha_inicio) then
		raise exception '%', '@NE_NO_VACACION_FECHA_VAL@';
		
		else
		v_days := DATE_PART('day', new.em_ne_fecha_fin::timestamp - new.em_ne_fecha_inicio::timestamp);
		new.em_ne_total_dias=to_number(v_days + 1);

		RETURN new;
		end if;
	 
	  	  --new.em_ne_total_dias=to_number(30);
	
		
		
	
	END IF;
	
IF TG_OP = 'DELETE' THEN RETURN OLD; ELSE RETURN NEW; END IF; 

END 

; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ne_vacacion_val_trg()
  OWNER TO tad;
