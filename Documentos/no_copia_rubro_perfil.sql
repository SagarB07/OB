-- Function: no_copia_rubro_perfil(character varying)

-- DROP FUNCTION no_copia_rubro_perfil(character varying);

CREATE OR REPLACE FUNCTION no_copia_rubro_perfil(p_pinstance_id character varying)
  RETURNS void AS
$BODY$ DECLARE 
v_ResultStr VARCHAR(2000):=''; 
v_Message VARCHAR(2000):=''; 
ITERADOR_RUBRO RECORD;
ITERADOR_PERFIL RECORD;

BEGIN
   
    RAISE NOTICE '%','Updating PInstance - Processing ' || p_PInstance_ID;
    v_ResultStr:='PInstanceNotFound';
    PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'Y', NULL, NULL);

    BEGIN 
    
        
        FOR ITERADOR_RUBRO IN
			SELECT  cab.no_tipo_ingreso_egreso_id, det.n_debe_acct, det.n_haber_acct
			FROM no_tipo_ingreso_egreso cab,no_tipo_ing_egr_acct det  
			WHERE det.no_tipo_ingreso_egreso_id = cab.no_tipo_ingreso_egreso_id          
        LOOP
			FOR ITERADOR_PERFIL IN
				SELECT ne_perfil_rubro_line_id , ne_debe_acct, ne_haber_acct 
				FROM ne_perfil_rubro_line WHERE NO_TIPO_INGRESO_EGRESO_ID = ITERADOR_RUBRO.NO_TIPO_INGRESO_EGRESO_ID			
			LOOP
			
			IF ITERADOR_PERFIL.NE_DEBE_ACCT<>ITERADOR_RUBRO.n_debe_acct AND ITERADOR_PERFIL.NE_HABER_ACCT<> ITERADOR_RUBRO.n_haber_acct  THEN
			UPDATE NE_PERFIL_RUBRO_LINE  SET NE_DEBE_ACCT = ITERADOR_RUBRO.n_debe_acct, NE_HABER_ACCT=ITERADOR_RUBRO.n_haber_acct  
			WHERE NE_PERFIL_RUBRO_LINE_ID = ITERADOR_PERFIL.NE_PERFIL_RUBRO_LINE_ID ;
			END IF;
			
			END LOOP;	
		
        END LOOP;

        v_Message:='@EjecucionCorrecta@';
        RAISE NOTICE '%','Updating PInstance - Finished - ' || v_Message ;
        PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'N', 0, v_ResultStr);
        RETURN;

    END;
    EXCEPTION
    WHEN OTHERS THEN
        v_ResultStr:= '@ERROR=' || SQLERRM;
        RAISE NOTICE '%',v_ResultStr ;
        
        PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'N', 0, v_ResultStr);
        RETURN;
END ; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION no_copia_rubro_perfil(character varying)
  OWNER TO tad;
