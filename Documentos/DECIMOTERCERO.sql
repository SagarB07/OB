select bp.taxid as cedula, bp.name as nombre ,co.salario,co.ad_org_id,

coalesce((select coalesce(pr.valor,0) from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id and pe.c_year_id = ye.c_year_id and pr.no_rol_pago_provision_line_id in (
select no_rol_pago_provision_line_id from no_rol_pago_provision_line
where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
and (ye.year=to_char(to_number(to_number('2015')-1)) and periodno='12')) as Dic,

coalesce((select coalesce(pr.valor,0) from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id and pe.c_year_id = ye.c_year_id and pr.no_rol_pago_provision_line_id in (
select no_rol_pago_provision_line_id from no_rol_pago_provision_line
where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
and (ye.year=to_char(to_number('2015')) and periodno='1')) as Ene,

coalesce((select coalesce(pr.valor,0) from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id and pe.c_year_id = ye.c_year_id and pr.no_rol_pago_provision_line_id in (
select no_rol_pago_provision_line_id from no_rol_pago_provision_line
where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
and (ye.year=to_char(to_number('2015')) and periodno='2')) as Feb,

coalesce((select coalesce(pr.valor,0) from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id and pe.c_year_id = ye.c_year_id and pr.no_rol_pago_provision_line_id in (
select no_rol_pago_provision_line_id from no_rol_pago_provision_line
where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
and (ye.year=to_char(to_number('2015')) and periodno='3')) as Mar,


coalesce((select coalesce(pr.valor,0) from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id and pe.c_year_id = ye.c_year_id and pr.no_rol_pago_provision_line_id in (
select no_rol_pago_provision_line_id from no_rol_pago_provision_line
where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
and (ye.year=to_char(to_number('2015')) and periodno='4')) as Abr,

coalesce((select coalesce(pr.valor,0) from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id and pe.c_year_id = ye.c_year_id and pr.no_rol_pago_provision_line_id in (
select no_rol_pago_provision_line_id from no_rol_pago_provision_line
where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
and (ye.year=to_char(to_number('2015')) and periodno='5')) as May,

coalesce((select coalesce(pr.valor,0) from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id and pe.c_year_id = ye.c_year_id and pr.no_rol_pago_provision_line_id in (
select no_rol_pago_provision_line_id from no_rol_pago_provision_line
where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
and (ye.year=to_char(to_number('2015')) and periodno='6')) as Jun,


coalesce((select coalesce(pr.valor,0) from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id and pe.c_year_id = ye.c_year_id and pr.no_rol_pago_provision_line_id in (
select no_rol_pago_provision_line_id from no_rol_pago_provision_line
where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
and (ye.year=to_char(to_number('2015')) and periodno='7')) as Jul,

coalesce((select coalesce(pr.valor,0) from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id and pe.c_year_id = ye.c_year_id and pr.no_rol_pago_provision_line_id in (
select no_rol_pago_provision_line_id from no_rol_pago_provision_line
where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
and (ye.year=to_char(to_number('2015')) and periodno='8')) as Ago,


coalesce((select coalesce(pr.valor,0) from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id and pe.c_year_id = ye.c_year_id and pr.no_rol_pago_provision_line_id in (
select no_rol_pago_provision_line_id from no_rol_pago_provision_line
where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
and (ye.year=to_char(to_number('2015')) and periodno='9')) as Sep,

coalesce((select coalesce(pr.valor,0) from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id and pe.c_year_id = ye.c_year_id and pr.no_rol_pago_provision_line_id in (
select no_rol_pago_provision_line_id from no_rol_pago_provision_line
where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
and (ye.year=to_char(to_number('2015')) and periodno='10')) as Oct,


coalesce((select coalesce(pr.valor,0) from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id and pe.c_year_id = ye.c_year_id and pr.no_rol_pago_provision_line_id in (
select no_rol_pago_provision_line_id from no_rol_pago_provision_line
where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
and (ye.year=to_char(to_number('2015')) and periodno='11')) as Nov,


(select sum (pr.valor)
from no_rol_provision_line_mes pr,
c_period pe, c_year ye
where pe.c_period_id = pr.c_period_id
and pe.c_year_id = ye.c_year_id
and pr.no_rol_pago_provision_line_id in (
 select no_rol_pago_provision_line_id from no_rol_pago_provision_line
 where no_rol_pago_provision_id in (select no_rol_pago_provision_id from no_rol_pago_provision where c_bpartner_id=co.c_bpartner_id and ispago='N')
 and no_tipo_ingreso_egreso_id in (select no_tipo_ingreso_egreso_id from no_tipo_ingreso_egreso where no_calcula_rubro_id in (select no_calcula_rubro_id from no_calcula_rubro where upper(nombre) like '%TERCE%')))
 and ((ye.year=to_char(to_number(to_number('2015')-1)) and periodno='12') or (ye.year=to_char(to_number('2015'))))
) as total,




fecha_inicio as finicio,
fecha_fin as ffin
from no_contrato_empleado co, c_bpartner bp
where co.c_bpartner_id=bp.c_bpartner_id
and to_date(co.fecha_fin) > (select startdate from c_period where c_year_id = ( select c_year_id from c_year where year=to_char(to_number('2015'))) and periodno='12')
order by bp.name