    
/*==============================================================*/
/* Table: idt_novedad                                       */
/*==============================================================*/

create table idt_novedad (
   idt_novedad_id 		character varying(32) NOT NULL,
   ad_client_id         character varying(32),   
   ad_org_id 			character varying(32),
   cedula				character varying(32),
   isactive 			character(1) DEFAULT 'Y'::bpchar,
   created				timestamp without time zone,
   createdby			character varying(32),
   updated				timestamp without time zone,
   updatedby			character varying(32),
   no_tipo_ingreso_egreso_id		character varying(32),
   c_period_id			character varying(32),
   valor numeric(10,2),
   c_bpartner_id character varying(32),
  i_errormsg character varying(2000), -- --OBTG:NVARCHAR--
  i_isimported character(1) DEFAULT 'N'::bpchar,
  procnovedad character(1),
  novprocesada character(1) DEFAULT 'N'::bpchar,
   constraint PK_idt_novedad primary key (idt_novedad_id)
);

/*==============================================================*/
/* Index: idt_novedad                                     */
/*==============================================================*/
create unique index idt_novedad_PK on idt_novedad (
idt_novedad_id
);
alter table idt_novedad
   add constraint IDT_FK_novedad_c_bpartner foreign key (c_bpartner_id)
      references c_bpartner (c_bpartner_id)
      on delete restrict on update restrict;


alter table idt_novedad
   add constraint IDT_FK_novedad_ad_client foreign key (ad_client_id)
      references ad_client (ad_client_id)
      on delete restrict on update restrict;
	  
alter table idt_novedad
   add constraint IDT_FK_novedad_ad_org foreign key (ad_org_id)
      references ad_org (ad_org_id)
      on delete restrict on update restrict;	  

alter table idt_novedad
   add constraint IDT_FK_novedad_c_period foreign key (c_period_id)
      references c_period (c_period_id)
      on delete restrict on update restrict;	

alter table idt_novedad
   add constraint IDT_FK_novedad_t_ing_egr foreign key (no_tipo_ingreso_egreso_id)
      references no_tipo_ingreso_egreso (no_tipo_ingreso_egreso_id)
      on delete restrict on update restrict;		  


