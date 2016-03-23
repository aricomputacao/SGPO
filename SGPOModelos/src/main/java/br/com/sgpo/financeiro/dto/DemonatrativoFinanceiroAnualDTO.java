/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.dto;

import br.com.sgpo.financeiro.enumeration.TipoDeOperacao;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;

/**
 *
 * @author ari
 * CREATE view demostrativo_anual_view as
SELECT  cop.cop_tipo,EXTRACT(YEAR from fo.fto_vencimento) as ano,EXTRACT(MONTH from fo.fto_vencimento) as mes, sum(fo.fto_valor) from financeiro.fatura_operacao fo 
			INNER JOIN financeiro.operacao op on op.ope_id = fo.ope_id
            INNER JOIN financeiro.categoria_operacao cop on cop.cop_id = op.cop_id
GROUP by cop.cop_tipo, EXTRACT(YEAR from fo.fto_vencimento),EXTRACT(MONTH from fo.fto_vencimento)            
 
ORDER BY cop.cop_tipo, EXTRACT(YEAR from fo.fto_vencimento),EXTRACT(MONTH from fo.fto_vencimento)
 */

public class DemonatrativoFinanceiroAnualDTO implements Serializable{
    
    private TipoDeOperacao tipo;
    private int ano;
    private BigDecimal valor;

    public DemonatrativoFinanceiroAnualDTO(TipoDeOperacao tipo,  int ano, BigDecimal valor) {
        this.tipo = tipo;
        this.ano = ano;
        this.valor = valor;
    }

    public DemonatrativoFinanceiroAnualDTO() {
    }

    public TipoDeOperacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeOperacao tipo) {
        this.tipo = tipo;
    }

   

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
     public String getNomeTipoOperacao(){
         return this.tipo.toString();
     }
    
    
}
