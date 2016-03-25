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
