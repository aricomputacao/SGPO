/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.enumeration;

/**
 *
 * @author ari
 */
public enum PeriodoRecorrencia {
    DIA("Dia"),MES("Mês"),SEMANA("Semana");

    private final String descricao;
    
    private PeriodoRecorrencia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
}
