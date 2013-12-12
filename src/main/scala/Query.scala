package jp.co.guru.AnalyzeWeb

object Query {
 val query1 = """
 select distinct
                            contract_total.salequantity,
                            contract_total.saleprice,
                            contract_total.netprofit,
                            contract_total.commshop,
                            contract_total.commdelivery,
                            contract_total.commpickup,
                            contract_total.commpricetag,
                            contract_total.commbox,
                            contract_total.commpaperwork,
                            COALESCE(others.otherprice, 0) otherprice,
                            payoffs.id,
                            payoffs.closedate,
                            contracts.id contract_id,
                            contracts.contract_cd,
                            contracts.contractorname,
                            contracts.contractorkname,
                            organizations.name organization_name
					 from  (select sales.payoff_id,
                                  sales.contract_cd,
                                  COALESCE(sum(sales.salequantity), 0) salequantity,
                                  COALESCE(sum(sales.saleprice), 0) saleprice,
                                  COALESCE(sum(sales.netprofit), 0) netprofit,
                                  COALESCE(sum(commshop.commissionprice), 0) commshop,
                                  COALESCE(sum(commdelivery.commissionprice), 0) commdelivery,
                                  COALESCE(sum(commpickup.commissionprice), 0) commpickup,
                                  COALESCE(sum(commpricetag.commissionprice), 0) commpricetag,
                                  COALESCE(sum(commbox.commissionprice), 0) commbox,
                                  COALESCE(sum(commpaperwork.commissionprice), 0) commpaperwork,
                                  contracts.id contract_id
                             from sales
                             left join payoffs
                               on sales.payoff_id = payoffs.id
                             left join contracts
                               on sales.contract_cd = contracts.contract_cd
                              and (contracts.status is null or contracts.status = 0)
                             left join organizations
                               on contracts.organization_id = organizations.id
                             left join salecommissions as commshop
                               on sales.id = commshop.sale_id
                              and commshop.commissiondiv_id = 27
                              and (commshop.isexempt is null or commshop.isexempt = 0)
                             left join salecommissions as commdelivery
                               on sales.id = commdelivery.sale_id
                              and commdelivery.commissiondiv_id = 28
                              and (commdelivery.isexempt is null or commdelivery.isexempt = 0)
                             left join salecommissions as commpickup
                               on sales.id = commpickup.sale_id
                              and commpickup.commissiondiv_id = 29
                              and (commpickup.isexempt is null or commpickup.isexempt = 0)
                             left join salecommissions as commpricetag
                               on sales.id = commpricetag.sale_id
                              and commpricetag.commissiondiv_id = 30
                              and (commpricetag.isexempt is null or commpricetag.isexempt = 0)
                             left join salecommissions as commbox
                               on sales.id = commbox.sale_id
                              and commbox.commissiondiv_id = 31
                              and (commbox.isexempt is null or commbox.isexempt = 0)
                             left join salecommissions as commpaperwork
                               on sales.id = commpaperwork.sale_id
                              and commpaperwork.commissiondiv_id = 37
                              and (commpaperwork.isexempt is null or commpaperwork.isexempt = 0)
                            where sales.payoff_id is not null 
							  and organizations.id = 7 
							  and  payoffs.closedate >= '2013/10/31' 
							group by sales.payoff_id, sales.contract_cd, contracts.id
			    		 ) contract_total
                    left join payoffs
                      on contract_total.payoff_id = payoffs.id
                    left join contracts
                      on contract_total.contract_cd = contracts.contract_cd
                     and (contracts.status is null or contracts.status = 0)
                    left join organizations
                      on contracts.organization_id = organizations.id
                    left join (select sum(price) otherprice,
                                      payoff_id,
                                      contract_id
                                 from payoffothers
                                group by payoff_id, contract_id
                              ) others
                      on contract_total.payoff_id = others.payoff_id
                     and contracts.id = others.contract_id order by payoffs.closedate desc, contracts.contract_cd desc offset 0 limit 20   
 """
}