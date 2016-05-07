/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ari
 */
public class ResultadoFinanceiroMensalDTO implements Serializable {

    private final Date mes;
    private final BigDecimal receita;
    private final BigDecimal despesa;

    public ResultadoFinanceiroMensalDTO(Date mes, BigDecimal receita, BigDecimal despesa) {
        this.mes = mes;
        this.receita = receita == null ? BigDecimal.ZERO : receita;
        this.despesa = despesa == null ? BigDecimal.ZERO : despesa;

    }

    
   
    public Date getMes() {
       
        return mes;
    }

    public BigDecimal getReceita() {
        return receita;
    }

    public BigDecimal getDespesa() {
        return despesa;
    }

    public BigDecimal getSaldo() {
        return receita.subtract(despesa);
    }

}
