-- Function: no_total_rol_prov_linea_trg()

-- DROP FUNCTION no_total_rol_prov_linea_trg();

CREATE OR REPLACE FUNCTION no_actualiza_fecha_contrato_trg()
  RETURNS trigger AS
$BODY$ DECLARE 

v_fecha_inicio  timestamp;
v_fecha_fin timestamp;
v_codigo_cabecera VARCHAR(32); 
BEGIN
    IF (TG_OP = 'UPDATE' OR TG_OP = 'INSERT') THEN

        select 
		fecha_inicio into v_fecha_inicio, 
		fecha_fin into v_fecha_fin 
		from  no_contrato_empleado where no_contrato_empleado_id in (
		SELECT distinct (ce.no_contrato_empleado_id) FROM no_contrato_empleado ce,
		no_rol_pago_provision_line prl,  
		no_rol_provision_line_mes prlm, 
		no_rol_pago_provision prc
		where prl.no_rol_pago_provision_line_id = prlm.no_rol_pago_provision_line_id
		and ce.c_bpartner_id =  prc.c_bpartner_id
		and  ce.isactive = 'Y'
		and prc.no_rol_pago_provision_id = prl.no_rol_pago_provision_id
		and prl.no_rol_pago_provision_line_id = NEW.no_rol_pago_provision_line_id) limit 1;
		
		
		SELECT distinct (prc.no_rol_pago_provision_id) into v_codigo_cabecera FROM no_contrato_empleado ce,
		no_rol_pago_provision_line prl,  
		no_rol_provision_line_mes prlm, 
		no_rol_pago_provision prc
		where prl.no_rol_pago_provision_line_id = prlm.no_rol_pago_provision_line_id
		and ce.c_bpartner_id =  prc.c_bpartner_id
		and  ce.isactive = 'Y'
		and prc.no_rol_pago_provision_id = prl.no_rol_pago_provision_id
		and prl.no_rol_pago_provision_line_id = NEW.no_rol_pago_provision_line_id;
		
		update no_rol_pago_provision_line 
		set fechainicio = v_fecha_inicio, fechafin = v_fecha_fin 
		where no_rol_pago_provision_id = v_codigo_cabecera
		
        RETURN NEW;
    END IF;


	IF TG_OP = 'DELETE' THEN RETURN OLD; ELSE RETURN NEW; END IF; 

END 

; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION no_actualiza_fecha_contrato_trg()
  OWNER TO tad;
