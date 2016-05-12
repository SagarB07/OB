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