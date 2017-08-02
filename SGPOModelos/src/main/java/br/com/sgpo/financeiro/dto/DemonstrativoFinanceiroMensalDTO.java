/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.dto;

import br.com.sgpo.financeiro.enumeration.TipoDeOperacao;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author ari
 */
public class DemonstrativoFinanceiroMensalDTO implements Serializable {

    private TipoDeOperacao tipo;
    private int mes;
    private BigDecimal valor;

    public DemonstrativoFinanceiroMensalDTO(TipoDeOperacao tipo, int mes, BigDecimal valor) {
        this.tipo = tipo;
        this.mes = mes;
        this.valor = valor;
    }

    public DemonstrativoFinanceiroMensalDTO() {
    }

    
    
    public TipoDeOperacao getTipo() {
        return tipo;
    }

    public int getMes() {
        return mes;
    }

    public BigDecimal getValor() {
        return valor;
    }
    
    
}
