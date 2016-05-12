-- Function: no_fondo_reserva_prov(character varying, character varying)

-- DROP FUNCTION no_fondo_reserva_prov(character varying, character varying);

CREATE OR REPLACE FUNCTION no_fondo_reserva_prov(p_empleado_id character varying, p_tipo_ingreso_egreso_id character varying, p_valor numeric , p_period_id character varying)
  RETURNS numeric AS
$BODY$ 
DECLARE 
v_total NUMERIC:=0;
v_Message varchar;
BEGIN


    if ((select count(*) 
          from no_rol_pago_provision 
         where c_period_id = p_period_id
           and c_bpartner_id = p_empleado_id) = 0) then
        
        v_Message:='@NO_FONDOREVPRO_ERROR@';
        RAISE EXCEPTION '%',v_Message;
    end if;


    if exists(select * 
                from no_contrato_empleado 
               where isactive ='Y'
                 and pagofondoreserva = 'Y'
                 and c_bpartner_id = p_empleado_id) then
    
        select COALESCE(sum(ppl.valor),0)
          into v_total
          from no_rol_pago_provision pp,
               no_rol_pago_provision_line ppl,
               no_tipo_ingreso_egreso ie
         where ispago = 'Y'
           and ppl.no_tipo_ingreso_egreso_id = ie.no_tipo_ingreso_egreso_id
           and ie.suma_ingreso = 'Y'
           and pp.no_rol_pago_provision_id = ppl.no_rol_pago_provision_id
           and pp.c_period_id = p_period_id
           and pp.c_bpartner_id = p_empleado_id;
           return v_total/12;
    else
        return 0;
    end if;

    return 0;
END ; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION no_fondo_reserva_prov(character varying, character varying)
  OWNER TO tad;
