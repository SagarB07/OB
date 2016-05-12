---DECIMO CA
--substring('Thomas' from 2 for 3)
SELECT CAST('08' AS character varying) AS AGOSTO;
SELECT CAST('07' AS character varying) AS JULIO;

SELECT CAST ( NOW() AS character varying ) AS ACTUAL FROM C_PERIOD

SELECT  substring( CAST(NOW() AS character varying) FROM 1 FOR 4) AS FACTUAL ;

SELECT  substring( CAST(NOW() AS character varying) FROM 1 FOR 4) || '-08-01' AS FINICIAL ;

SELECT  CAST(CAST(substring( CAST(NOW() AS character varying) FROM 1 FOR 4) AS NUMERIC)-1 AS character varying ) || '-07-01'  AS FECHA_FINAL ;

SELECT  substring( CAST(NOW() AS character varying) FROM 1 FOR 4) || '-06-30' AS FECHA_INCIAL ;


SELECT STARTDATE FROM C_PERIOD WHERE STARTDATE BETWEEN '2015-08-01' AND '2016-06-30'

SELECT C_PERIOD_ID, NAME, PERIODNO  FROM C_PERIOD WHERE STARTDATE BETWEEN '2015-08-01' AND '2016-06-30'

SELECT CAST (STARTDATE AS character varying) , NOW() AS ACTUAL FROM C_PERIOD

select * from c_bpartner where c_bpartner_id = '15497893F5B34118A899957112C1537B';
select * from  no_rol_pago_provision 
select * from no_tipo_ingreso_egreso where no_tipo_ingreso_egreso_id = '73986DBC43E34F25B67766C6409E213F';
select no_tipo_ingreso_egreso_id 
from no_tipo_ingreso_egreso 
where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where nombre ilike '%Cuarto%')

select * from no_rol_provision_line_mes;

SELECT sum(provmes.valor) FROM no_rol_provision_line_mes provmes, no_rol_pago_provision_line probline , no_rol_pago_provision provcab 
WHERE provcab.no_rol_pago_provision_id = probline.no_rol_pago_provision_id 
and probline.no_rol_pago_provision_line_id = provmes.no_rol_pago_provision_line_id 
and provcab.c_bpartner_id = '15497893F5B34118A899957112C1537B' 
and probline.no_tipo_ingreso_egreso_id =  '73986DBC43E34F25B67766C6409E213F' 
and provmes.c_period_id in (select no_recupera_periodos (1));

---DECIMO CA
--substring('Thomas' from 2 for 3)
SELECT CAST('08' AS character varying) AS AGOSTO;
SELECT CAST('07' AS character varying) AS JULIO;

SELECT CAST ( NOW() AS character varying ) AS ACTUAL FROM C_PERIOD

SELECT  substring( CAST(NOW() AS character varying) FROM 1 FOR 4) AS FACTUAL ;

SELECT  substring( CAST(NOW() AS character varying) FROM 1 FOR 4) || '-08-01' AS FINICIAL ;

SELECT  CAST(CAST(substring( CAST(NOW() AS character varying) FROM 1 FOR 4) AS NUMERIC)-1 AS character varying ) || '-07-01'  AS FECHA_FINAL ;

SELECT  substring( CAST(NOW() AS character varying) FROM 1 FOR 4) || '-06-30' AS FECHA_INCIAL ;


SELECT STARTDATE FROM C_PERIOD WHERE STARTDATE BETWEEN '2015-08-01' AND '2016-06-30'

SELECT C_PERIOD_ID, NAME, PERIODNO  FROM C_PERIOD WHERE STARTDATE BETWEEN '2015-08-01' AND '2016-06-30'

SELECT CAST (STARTDATE AS character varying) , NOW() AS ACTUAL FROM C_PERIOD

select * from c_bpartner where c_bpartner_id = '15497893F5B34118A899957112C1537B';
select * from  no_rol_pago_provision 
select * from no_tipo_ingreso_egreso where no_tipo_ingreso_egreso_id = '73986DBC43E34F25B67766C6409E213F';
select no_tipo_ingreso_egreso_id 
from no_tipo_ingreso_egreso 
where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where nombre ilike '%Cuarto%')

select * from no_rol_provision_line_mes;

SELECT sum(provmes.valor) FROM no_rol_provision_line_mes provmes, no_rol_pago_provision_line probline , no_rol_pago_provision provcab 
WHERE provcab.no_rol_pago_provision_id = probline.no_rol_pago_provision_id 
and probline.no_rol_pago_provision_line_id = provmes.no_rol_pago_provision_line_id 
and provcab.c_bpartner_id = '15497893F5B34118A899957112C1537B' 
and probline.no_tipo_ingreso_egreso_id =  '73986DBC43E34F25B67766C6409E213F' 
and provmes.c_period_id in (select no_recupera_periodos (1));


--------------------



CREATE OR REPLACE FUNCTION no_recupera_periodos (p_opcion  integer) 
RETURNS TABLE( IDPERIODO character varying) AS 
$BODY$ 
DECLARE
v_FECHAINCIAL VARCHAR(20);
v_FECHAFINAL VARCHAR(20);
v_ResultStr VARCHAR(2000):='';
v_Message VARCHAR(2000):='';
    --ids INTEGER[];
    
BEGIN
	
 	IF p_opcion = 1 THEN 
 	SELECT  CAST(CAST(substring( CAST(NOW() AS character varying) FROM 1 FOR 4) AS NUMERIC)-1 AS character varying ) || '-08-01'  INTO v_FECHAINCIAL  ;
	SELECT  substring( CAST(NOW() AS character varying) FROM 1 FOR 4) || '-07-01' INTO v_FECHAFINAL ;
	
	
 	ELSE
	SELECT  CAST(CAST(substring( CAST(NOW() AS character varying) FROM 1 FOR 4) AS NUMERIC)-1 AS character varying ) || '-12-01'  INTO v_FECHAINCIAL  ;
	SELECT  substring( CAST(NOW() AS character varying) FROM 1 FOR 4) || '-11-01' INTO v_FECHAFINAL ;
 	END IF;
	
     --ids := ARRAY[1,2];
	--UPDATE IDT_CONTRATO SET I_ERRORMSG = v_FECHAINCIAL||'*****'|| v_FECHAFINAL ;


       RETURN QUERY
  		SELECT PR.C_PERIOD_ID AS PERIODO
  		 FROM C_PERIOD PR
   		 WHERE PR.STARTDATE BETWEEN CAST(v_FECHAINCIAL AS TIMESTAMP) AND CAST(v_FECHAFINAL AS TIMESTAMP);
   		 
	--UPDATE IDT_CONTRATO SET I_ERRORMSG = v_FECHAINCIAL||'*1****'|| v_FECHAFINAL ;
EXCEPTION
    WHEN OTHERS THEN
        v_ResultStr:= '@ERROR=' || SQLERRM;        
	RAISE NOTICE '%','Updating PInstance - Finished - ' || v_Message;
    	UPDATE IDT_CONTRATO SET i_errormsg = v_ResultStr;




   		 
END;
$BODY$
LANGUAGE plpgsql VOLATILE
COST 100;
ALTER FUNCTION no_recupera_periodos (p_opcion  integer) 
  OWNER TO tad;


--select * from my_bfunction (1);
select * from C_PERIOD where C_PERIOD_id in  (select no_recupera_periodos (2));
--SELECT I_ERRORMSG FROM IDT_CONTRATO;













--------------------------




select  * from no_contrato_empleado;
select  cemp.em_ne_region as region ,  from c_bpartner emp, no_contrato_empleado cemp where emp.isemployee =  'Y' and emp.c_bpartner_id = cemp.c_bpartner_id and cemp.isactive = 'Y';
SELECT * FROM no_contrato_empleado;
SELECT * FROM c_bpartner_location;
SELECT * FROM C_CITY;
SELECT * FROM C_LOCATION;
SELECT * FROM C_REGION;
SELECT * FROM ATNORH_CARGO;

SELECT em_ne_region , 
CASE  WHEN em_ne_region = '1' THEN 'SIERRA' WHEN em_ne_region = '2' THEN 'COSTA' ELSE 'NE' END   FROM no_contrato_empleado

SELECT cemp.em_ne_region AS REGIONID, (CASE  WHEN em_ne_region = '1' THEN 'SIERRA' WHEN em_ne_region = '2' THEN 'COSTA' ELSE 'NE' END) AS REGIONNAME ,REG.NAME AS PROVINCIA, LOC.CITY, 
CEMP.FECHA_INICIO AS ILABORES, CEMP.FECHA_FIN AS FLABORES, CAR.NAME AS CARGO, CEMP.SALARIO AS SUELDO
FROM c_bpartner emp 
left  JOIN no_contrato_empleado cemp  ON cemp.c_bpartner_id = emp.c_bpartner_id 
left  JOIN c_bpartner_location LEMP  ON LEMP.c_bpartner_id = emp.c_bpartner_id 
left  JOIN C_LOCATION LOC  ON  LOC.C_LOCATION_ID = LEMP.C_LOCATION_ID 
left  JOIN C_REGION REG  ON  REG.C_REGION_ID = LOC.C_REGION_ID 
left  JOIN ATNORH_CARGO CAR  ON  CAR.ATNORH_CARGO_ID = cemp.EM_ATNORH_CARGO_ID 
WHERE 
emp.isemployee =  'Y'
AND cemp.isactive = 'Y';
