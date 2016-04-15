-- Function: no_valid_rol_prov_complete_trg()

-- DROP FUNCTION no_valid_rol_prov_complete_trg();

CREATE OR REPLACE FUNCTION no_genera_provicion_trg()

RETURNS trigger AS

$BODY$ DECLARE 

  v_User_ID varchar(32);
  v_Client_ID varchar(32);
  v_Org_ID varchar(32);
  
  v_docstatus varchar(60);
  v_mensaje varchar(60);
  v_num_provisiones NUMERIC;
BEGIN
    

    IF TG_OP = 'UPDATE' THEN        
       if(new.docstatus='CO' )then
     
			v_User_ID = (SELECT CREATEDBY FROM no_rol_pago_provision WHERE  no_rol_pago_provision_ID = NEW.no_rol_pago_provision_ID);
			v_Client_ID = (SELECT AD_CLIENT_ID FROM no_rol_pago_provision WHERE  no_rol_pago_provision_ID = NEW.no_rol_pago_provision_ID);
			v_Org_ID = (SELECT AD_ORG_ID FROM no_rol_pago_provision WHERE  no_rol_pago_provision_ID = NEW.no_rol_pago_provision_ID);
			
			v_qry  := 'SELECT no_genera_provicion ('''|| v_User_ID ||''','''|| v_Client_ID ||''','''|| v_Org_ID ||''');'
			
			update idt_contrato set i_errormsg = v_qry; 
      
		end if;    
        RETURN NEW;
    ELSE
        RETURN OLD;
    END IF;
	

	IF TG_OP = 'DELETE' THEN RETURN OLD; ELSE RETURN NEW; END IF; 

END 

; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION no_valid_rol_prov_complete_trg()
  OWNER TO tad;
