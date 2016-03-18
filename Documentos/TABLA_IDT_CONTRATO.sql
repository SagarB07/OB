CREATE TABLE idt_contrato
(
  idt_contrato_id character varying(32) ,
  atnorh_cargo_id character varying(32),
  ad_client_id character varying(32) ,
  ad_org_id character varying(32) ,
  c_doctype_id character varying(32) ,
  c_bpartner_id character varying(32) ,
  c_currency_id character varying(32), 
  fin_financial_account_id character varying(32),
  fin_paymentmethod_id character varying(32),  
  createdby character varying(32) ,
  updatedby character varying(32) ,
  isactive character(1)  DEFAULT 'Y'::bpchar,
  created timestamp without time zone  DEFAULT now(),  
  updated timestamp without time zone  DEFAULT now(),  
  fecha_inicio timestamp without time zone ,
  fecha_fin timestamp without time zone,
  documentno character varying(40) ,  
  salario numeric ,  
  tipo_contrato character varying(60) ,
  pagofondoreserva character(1) ,  
  is_impuesto_asumido character(1),
  aplica_utilidad character(1) DEFAULT 'Y'::bpchar,
  i_errormsg character varying(2000),
  i_isimported character(1) DEFAULT 'N'::bpchar,
  procesado character(1) DEFAULT 'N'::bpchar,
  btnproceso character varying(60),
  ne_sissalnet character varying(60),  
  ne_motivo_salida character varying(60),
  ne_is_jornada_parcial character(1) DEFAULT 'N'::bpchar,
  ne_num_horas_parciales numeric,
  ne_vacacion_prop numeric(10,2),
  ne_vacacion_tom numeric(10,2),
  ne_vacacion_res numeric(10,2),
  ne_region character varying(60),
  ne_observaciones character varying(255),
  constraint idt_idtcontrato_pk primary key (idt_contrato_id)
  );
  create unique index idt_contrato_PK on idt_contrato (
	idt_contrato_id
	);

	
alter table idt_contrato
   add constraint IDT_FK_idtcontrato_c_bpartner foreign key (c_bpartner_id)
      references c_bpartner (c_bpartner_id)
      on delete restrict on update restrict;
	  
alter table idt_contrato
   add constraint IDT_FK_contrato_atnorh_cargo foreign key (atnorh_cargo_id)
      references atnorh_cargo (atnorh_cargo_id)
      on delete restrict on update restrict;
  
 alter table idt_contrato
   add constraint IDT_FK_idtcontrato_ad_client foreign key (ad_client_id)
      references ad_client (ad_client_id)
      on delete restrict on update restrict;
	  
alter table idt_contrato
   add constraint IDT_FK_idtcontrato_ad_org foreign key (ad_org_id)
      references ad_org (ad_org_id)
      on delete restrict on update restrict;	  

alter table idt_contrato
   add constraint IDT_FK_idtcontrato_c_doctype foreign key (c_doctype_id)
      references c_doctype (c_doctype_id)
      on delete restrict on update restrict;	  
	  
	  
alter table idt_contrato
   add constraint IDT_FK_idtcontrato_currency foreign key (c_currency_id)
      references c_currency (c_currency_id)
      on delete restrict on update restrict;	  
	  
	  
alter table idt_contrato
   add constraint IDT_FK_idtcontrato_c_currency foreign key (fin_financial_account_id)
      references fin_financial_account (fin_financial_account_id)
      on delete restrict on update restrict;		  
  
 alter table idt_contrato
   add constraint IDT_FK_idtcontrato_fin_paymentmethod foreign key (fin_paymentmethod_id)
      references fin_paymentmethod (fin_paymentmethod_id)
      on delete restrict on update restrict;		  
	  

	  
