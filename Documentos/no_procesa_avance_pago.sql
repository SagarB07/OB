-- Function: no_procesa_avance_pago(character varying)

-- DROP FUNCTION no_procesa_avance_pago(character varying);

CREATE OR REPLACE FUNCTION no_procesa_avance_pago(p_pinstance_id character varying)
  RETURNS void AS
$BODY$ DECLARE 
v_ResultStr VARCHAR(2000):='';
    v_Message VARCHAR(2000):='';
    v_Record_ID VARCHAR(32);
    v_Result NUMERIC:=1;

    Cur_Parameter RECORD;
    Cur_AvanceSchema RECORD;

    v_no_avance_ln_id VARCHAR(32);
    v_Client_ID VARCHAR(32);
    v_Org_ID VARCHAR(32);
    v_User_ID VARCHAR(32);

    v_valor_total NUMERIC;
    v_salario NUMERIC;
    v_fin_paymentmethod_id VARCHAR(32);
    v_account_id VARCHAR(32);
    v_es_ingreso char(1);
    v_cuenta_rubro_debe VARCHAR(32);
    v_cuenta_rubro_haber VARCHAR(32);
    v_cuenta_por_pagar VARCHAR(32);
    v_no_liquida_line_id VARCHAR(32);
    v_account_debe_haber VARCHAR(32);
    v_account_por_pagar VARCHAR(32);
    v_account_bancos VARCHAR(32);

    v_alias_por_pagar VARCHAR(40);
    v_description_por_pagar VARCHAR(255);
    v_combination_por_pagar VARCHAR(120);
    v_alias_payment VARCHAR(40);
    v_description_payment VARCHAR(255);
    v_combination_payment VARCHAR(120);

    v_alias VARCHAR(40);
    v_combination VARCHAR(120);
    v_description VARCHAR(255);

    v_fin_payment1_id VARCHAR(32);
    v_fin_payment_detail_id VARCHAR(32);
    v_fin_financial_account_id VARCHAR(32);
    v_fin_payment_scheduledetail_id VARCHAR(32);
    v_line NUMERIC;
    v_fin_finacc_transaction_id VARCHAR(32);
    v_documentno_rol VARCHAR(30);
    v_name_partner VARCHAR(60);
    v_doctype_payment_id VARCHAR(32);
    gl_category_payment_id VARCHAR(32);

    v_fact_acct_id VARCHAR(32);
    v_acctschema_id VARCHAR(32);
    v_period_id VARCHAR(32);
    v_table_id VARCHAR(32);
    v_table_payment_id VARCHAR(32);
    v_amtacctcr VARCHAR(32);
    v_currency_id VARCHAR(32);
    v_currency_orig_id VARCHAR(32);
    v_bpartner_id VARCHAR(32);
    v_locfrom_id VARCHAR(32);
    v_locto_id VARCHAR(32);
    v_fact_acct_group_cto_id VARCHAR(32);
    v_fact_acct_group_cto_pay_id VARCHAR(32);
    v_seqno NUMERIC;
    v_documentno VARCHAR(30);
    v_doctype_id VARCHAR(32);
    v_category_id VARCHAR(32);
    v_docbasetype VARCHAR(40);

BEGIN

    RAISE NOTICE '%','Updating PInstance - Processing ' || p_PInstance_ID;
    v_ResultStr:='PInstanceNotFound';
    PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'Y', NULL, NULL);

    BEGIN

        FOR Cur_Parameter IN
          (SELECT i.Record_ID,
                  i.AD_User_ID,
                  p.ParameterName,
                  p.P_String,
                  p.P_Number,
                  p.P_Date,
                  i.AD_Org_ID,
                  i.AD_Client_ID
             FROM AD_PInstance i
             LEFT JOIN AD_PInstance_Para p
               ON i.AD_PInstance_ID=p.AD_PInstance_ID
            WHERE i.AD_PInstance_ID=p_PInstance_ID
            ORDER BY p.SeqNo)
        LOOP
          v_no_avance_ln_id:=Cur_Parameter.Record_ID;
          v_User_ID:=Cur_Parameter.AD_User_ID;
          v_Org_ID:=Cur_Parameter.AD_Org_ID;
          v_Client_ID:=Cur_Parameter.AD_Client_ID;
        END LOOP;

        select ad_table_id
          into v_table_payment_id
          from ad_table
         where upper(tablename) = upper('FIN_Payment');

        select ad_table_id
          into v_table_id
          from ad_table
         where upper(tablename) = upper('no_registra_quinc_line');

        FOR Cur_AvanceSchema IN
         (select rql.no_registra_quinc_line_id,
                 '0' as c_doctype_id,
                 '0' as documentno,
                 rql.c_bpartner_id,
                 rql.fin_financial_account_id,
                 rql.fin_paymentmethod_id,
                 rql.c_currency_id as moneda_origen,
                 rql.valor,
                 rq.c_period_id,
                 a.c_acctschema_id,
                 a.name,
                 a.c_currency_id,
                 faa.fin_out_intransit_acct,
                 faa.fin_withdrawal_acct,
                 ea.em_no_pago_acct
            from no_registra_quincena rq,
                 no_registra_quinc_line rql,
                 c_bp_employee_acct ea,
                 fin_financial_account_acct faa,
                 c_acctschema a
            where ea.c_bpartner_id = rql.c_bpartner_id
             and rq.no_registra_quincena_id = rql.no_registra_quincena_id
             and faa.fin_financial_account_id = rql.fin_financial_account_id
             and ea.c_acctschema_id = faa.c_acctschema_id
             and ea.c_acctschema_id = a.c_acctschema_id
             and rql.no_registra_quinc_line_id =v_no_avance_ln_id)
        LOOP
            v_doctype_id:=Cur_AvanceSchema.c_doctype_id;
            v_bpartner_id:=Cur_AvanceSchema.c_bpartner_id;
            v_documentno_rol:=Cur_AvanceSchema.documentno;
            v_period_id:=Cur_AvanceSchema.c_period_id;
            v_fin_financial_account_id:=Cur_AvanceSchema.fin_financial_account_id;
            v_fin_paymentmethod_id:=Cur_AvanceSchema.fin_paymentmethod_id;
            v_currency_orig_id:=Cur_AvanceSchema.moneda_origen;
            v_currency_id:=Cur_AvanceSchema.c_currency_id;
            v_acctschema_id:=Cur_AvanceSchema.c_acctschema_id;
            v_cuenta_por_pagar:=Cur_AvanceSchema.em_no_pago_acct;

            select salario
              into v_salario
              from no_contrato_empleado
             where c_bpartner_id = v_bpartner_id
               and isactive = 'Y'
               and fecha_fin::DATE >= TO_DATE(NOW())::DATE;

            select C_Currency_Convert(v_salario, v_currency_orig_id, v_currency_id, NULL, NULL, v_Client_ID, v_Org_ID) into v_salario;

            select C_Currency_Convert(Cur_AvanceSchema.valor, v_currency_orig_id, v_currency_id, NULL, NULL, v_Client_ID, v_Org_ID) into v_valor_total;

                        
            select get_uuid() into v_fact_acct_group_cto_id;

            select get_uuid() into v_fin_payment1_id;

            select get_uuid() into v_fact_acct_group_cto_pay_id;

            select c_doctype_id,
                   gl_category_id
              into v_doctype_payment_id,
                   gl_category_payment_id
              from c_doctype
             where docbasetype='APP'
               and ad_client_id=v_Client_ID;

            SELECT * INTO  v_documentno
              FROM Ad_Sequence_Doctype(v_doctype_payment_id, v_Client_ID, 'Y');

            select account_id,
                   alias,
                   description,
                   combination
              into v_account_por_pagar,
                   v_alias_por_pagar,
                   v_description_por_pagar,
                   v_combination_por_pagar
              from c_validcombination
             where c_validcombination_id=v_cuenta_por_pagar;

            insert into fin_payment(fin_payment_id,             ad_client_id,               ad_org_id,                      created,                                                                createdby,                      updated,
                                    updatedby,                  isactive,                   isreceipt,                      c_bpartner_id,                                                          paymentdate,                    c_currency_id,
                                    amount,                     writeoffamt,                fin_paymentmethod_id,           documentno,                                                             referenceno,                    status,
                                    processed,                  processing,                 posted,                         description,                                                            fin_financial_account_id,       c_doctype_id,
                                    c_project_id,               c_campaign_id,              c_activity_id,                  user1_id,                                                               user2_id,                       generated_credit,
                                    used_credit,                createdbyalgorithm,         finacc_txn_convert_rate,        finacc_txn_amount,
                                    em_aprm_process_payment,    em_aprm_reconcile_payment,  em_aprm_add_scheduledpayments,  em_aprm_executepayment)
                            values (v_fin_payment1_id,          v_Client_ID,                v_Org_ID,                       current_timestamp,                                                      v_User_ID,                      current_timestamp,
                                    v_User_ID,                  'Y',                        'N',                            v_bpartner_id,                                                          current_timestamp,              v_currency_id,
                                    v_valor_total,              0,                          v_fin_paymentmethod_id,         v_documentno,                                                           'RP',                           'RPAP',
                                    'N',                        'N',                        'N',                            'Avance: '|| v_name_partner||'-'||DATE_TRUNC('day', current_timestamp), v_fin_financial_account_id,     v_doctype_payment_id,
                                    null,                       null,                       null,                           null,                                                                   null,                           null,
                                    0,                          'N',                        1,                              v_valor_total,
                                    'R',                        'N',                        'N',                            'N');

            select get_uuid() into v_fin_payment_detail_id;

            INSERT INTO fin_payment_detail(fin_payment_detail_id,       ad_client_id,       ad_org_id,          created,            createdby,
                                           updated,                     updatedby,          fin_payment_id,     amount,             refund,
                                           isactive,                    writeoffamt,        c_glitem_id,        isprepayment)
                                    VALUES(v_fin_payment_detail_id,     v_Client_ID,        v_Org_ID,           current_timestamp,  v_User_ID,
                                           current_timestamp,           v_User_ID,          v_fin_payment1_id,  v_valor_total,      'N',
                                           'Y',                         0,                  null,               'N');

            select get_uuid() into v_fin_payment_scheduledetail_id;

            INSERT INTO fin_payment_scheduledetail(fin_payment_scheduledetail_id,     ad_client_id,                     ad_org_id,             created,
                                                   createdby,                         updated,                          updatedby,             fin_payment_detail_id,
                                                   fin_payment_schedule_order,        fin_payment_schedule_invoice,     amount,                isactive,
                                                   writeoffamt,                       iscanceled,                       c_bpartner_id,         c_activity_id,
                                                   m_product_id,                      c_campaign_id,                    c_project_id,          c_salesregion_id)
                                           VALUES (v_fin_payment_scheduledetail_id,   v_Client_ID,                      v_Org_ID,              current_timestamp,
                                                   v_User_ID,                         current_timestamp,                v_User_ID,             v_fin_payment_detail_id,
                                                   null,                              null,                             v_valor_total,         'Y',
                                                   0,                                 'N',                              v_bpartner_id,         null,
                                                   null,                              null,                             null,                  null);

                select coalesce(max(seqno),0)+10 into v_seqno
                  from fact_acct
                 where record_id = v_fin_payment1_id;

                select get_uuid() into v_fact_acct_id;

                insert into fact_acct(fact_acct_id,                    ad_client_id,               ad_org_id,          isactive,                                created,             createdby,                      updated,                                                   updatedby,
                                      c_acctschema_id,                 account_id,                 datetrx,            dateacct,                                c_period_id,         ad_table_id,                    record_id,                                                 line_id,
                                      gl_category_id,                  c_tax_id,                   m_locator_id,       postingtype,                             c_currency_id,       amtsourcedr,                    amtsourcecr,                                               amtacctdr,
                                      amtacctcr,                       c_uom_id,                   qty,                m_product_id,                            c_bpartner_id,       ad_orgtrx_id,                   c_locfrom_id,                                              c_locto_id,
                                      c_salesregion_id,                c_project_id,               c_campaign_id,      c_activity_id,                           user1_id,            user2_id,                       description,                                               a_asset_id,
                                      fact_acct_group_id,              seqno,                      factaccttype,       docbasetype,                             acctvalue,           acctdescription,                record_id2,                                                c_withholding_id,
                                      c_doctype_id)
                               values(v_fact_acct_id,                  v_Client_ID,                v_Org_ID,           'Y',                                     current_timestamp,   v_User_ID,                      current_timestamp,                                         v_User_ID,
                                      v_acctschema_id,                 v_account_por_pagar,        current_timestamp,  DATE_TRUNC('day', current_timestamp ),   v_period_id,         v_table_payment_id,             v_fin_payment1_id,                                         null,
                                      gl_category_payment_id,          null,                       null,               'A',                                     v_currency_id,       v_valor_total,                  0,                                                         v_valor_total,
                                      0,                               null,                       0,                  null,                                    v_bpartner_id,       null,                           null,                                                      null,
                                      null,                            null,                       null,               null,                                    null,                null,                           v_documentno||'#'||v_name_partner,                         null,
                                      v_fact_acct_group_cto_pay_id,    v_seqno,                   'N',                'APP',                                    v_alias_por_pagar,   v_description_por_pagar,        null,                                                      null,
                                      v_doctype_payment_id);

            if Cur_AvanceSchema.fin_out_intransit_acct is not null then
                select account_id,
                       alias,
                       description,
                       combination
                  into v_account_bancos,
                       v_alias_payment,
                       v_description_payment,
                       v_combination_payment
                  from c_validcombination
                 where c_validcombination_id = Cur_AvanceSchema.fin_out_intransit_acct;
             else if Cur_AvanceSchema.fin_withdrawal_acct is not null then
                    select account_id,
                           alias,
                           description,
                           combination
                      into v_account_bancos,
                           v_alias_payment,
                           v_description_payment,
                           v_combination_payment
                      from c_validcombination
                     where c_validcombination_id = Cur_AvanceSchema.fin_withdrawal_acct;
                end if;
            end if;


            select get_uuid() into v_fact_acct_id;

            select coalesce(max(seqno),0)+10 into v_seqno
              from fact_acct
             where record_id = v_fin_payment1_id;

            insert into fact_acct(fact_acct_id,                    ad_client_id,               ad_org_id,          isactive,                                created,             createdby,                  updated,                                    updatedby,
                                  c_acctschema_id,                 account_id,                 datetrx,            dateacct,                                c_period_id,         ad_table_id,                record_id,                                  line_id,
                                  gl_category_id,                  c_tax_id,                   m_locator_id,       postingtype,                             c_currency_id,       amtsourcedr,                amtsourcecr,                                amtacctdr,
                                  amtacctcr,                       c_uom_id,                   qty,                m_product_id,                            c_bpartner_id,       ad_orgtrx_id,               c_locfrom_id,                               c_locto_id,
                                  c_salesregion_id,                c_project_id,               c_campaign_id,      c_activity_id,                           user1_id,            user2_id,                   description,                                a_asset_id,
                                  fact_acct_group_id,              seqno,                      factaccttype,       docbasetype,                             acctvalue,           acctdescription,            record_id2,                                 c_withholding_id,
                                  c_doctype_id)
                           values(v_fact_acct_id,                  v_Client_ID,                v_Org_ID,           'Y',                                     current_timestamp,   v_User_ID,                  current_timestamp,                          v_User_ID,
                                  v_acctschema_id,                 v_account_bancos,           current_timestamp,  DATE_TRUNC('day', current_timestamp ),   v_period_id,         v_table_payment_id,         v_fin_payment1_id,                           null,
                                  gl_category_payment_id,          null,                       null,               'A',                                     v_currency_id,       0,                          v_valor_total,                              0,
                                  v_valor_total,                   null,                       0,                  null,                                    v_bpartner_id,       null,                       null,                                       null,
                                  null,                            null,                       null,               null,                                    null,                null,                       v_documentno|| ' # ' ||v_name_partner,      null,
                                  v_fact_acct_group_cto_pay_id,    v_seqno,                   'N',                 'APP',                                   v_alias_payment,     v_description_payment,      null,                                       null,
                                  v_doctype_payment_id);

        END LOOP;

            update fin_payment
               set status='PPM',
                   processed='Y',
                   posted='Y'
             where fin_payment_id = v_fin_payment1_id;

        update no_registra_quinc_line
           set processed = 'Y'
         where no_registra_quinc_line_id = v_no_avance_ln_id;


        v_Message:='@NO_EjecucionCorrecta@';
        RAISE NOTICE '%','Updating PInstance - Finished - ' || v_Message ;
        PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, v_User_ID, 'Y', v_Result, v_Message);
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
ALTER FUNCTION no_procesa_avance_pago(character varying)
  OWNER TO tad;
