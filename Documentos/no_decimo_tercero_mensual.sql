-- Function: no_decimo_tercero_mensual(character varying, character varying, numeric, character varying)

-- DROP FUNCTION no_decimo_tercero_mensual(character varying, character varying, numeric, character varying);

CREATE OR REPLACE FUNCTION no_decimo_tercero_mensual(p_empleado_id character varying, p_tipo_ingreso_egreso_id character varying, p_valor numeric, p_period_id character varying)
  RETURNS numeric AS
$BODY$ DECLARE 
v_comision NUMERIC;
v_sueldo   NUMERIC;

BEGIN
	
	
    if exists(select * 
                from no_contrato_empleado 
               where isactive ='Y' and c_bpartner_id = p_empleado_id) then
		--return round( p_valor * 0.0833, 2);

		v_comision := (select coalesce(nvl.valor,0)
				  from no_empleado_ing_egr em, no_tipo_ingreso_egreso tr, no_novedad nov, no_novedad_linea nvl
				 where em.no_tipo_ingreso_egreso_id = tr.no_tipo_ingreso_egreso_id
				   and nov.no_tipo_ingreso_egreso_id = tr.no_tipo_ingreso_egreso_id
				   and nvl.no_novedad_id = nov.no_novedad_id
				   and nov.c_period_id = p_period_id
				   and nvl.c_bpartner_id = p_empleado_id
				   and tr.suma_ingreso = 'Y'
				   and tr.isingreso = 'Y'
				   and tr.no_tipo_ingreso_egreso_id = p_tipo_ingreso_egreso_id
				 limit 1);

		v_sueldo := (select ce.salario 
			       from no_contrato_empleado ce
                              where ce.isactive ='Y' 
                                and ce.c_bpartner_id = p_empleado_id);

		RETURN ((coalesce(v_comision,0) + coalesce(v_sueldo,0))/12);
	else
        return 0;
    end if;
END ; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION no_decimo_tercero_mensual(character varying, character varying, numeric, character varying)
  OWNER TO tad;
