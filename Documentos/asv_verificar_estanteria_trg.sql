-- Function: asv_verificar_estanteria_trg()

-- DROP FUNCTION asv_verificar_estanteria_trg();

CREATE OR REPLACE FUNCTION asv_verificar_estanteria_trg()
  RETURNS trigger AS
$BODY$ DECLARE 
declare
	--Verifico si se ha seleccionado una estanteria en pedido detalle caso contrario se genera una excepcion
	v_docstatus character(32);
BEGIN
	select docstatus into v_docstatus from asv_pedido_inventario where asv_pedido_inventario_id=new.asv_pedido_inventario_id;
	IF v_docstatus = 'PR' and new.m_locator_id is null and new.enviado > 0 THEN
		RAISE EXCEPTION '%', '@ASV_MENSAJE_ESTANTE@';
	else
		RETURN NEW;
	END IF;
END;00000000000000000
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION asv_verificar_estanteria_trg()
  OWNER TO tad;
