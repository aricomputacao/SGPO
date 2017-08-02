/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.enumeration;

import br.com.sgpo.financeiro.negocio.ProcessadorDePeriodoRecorrencia;
import br.com.sgpo.financeiro.negocio.ProcessadorPeriodoMes;
import br.com.sgpo.financeiro.negocio.ProcessadorPeriodoSemana;
import br.com.sgpo.financeiro.negocio.ProcessarPeriodoDia;
import java.util.Date;

/**
 *
 * @author ari
 */
public enum PeriodoRecorrencia {
    MES("Mês",new ProcessadorPeriodoMes()),
    SEMANA("Semana",new ProcessadorPeriodoSemana()),
    DIA("Dia",new ProcessarPeriodoDia());

    private final String descricao;
    
    private final ProcessadorDePeriodoRecorrencia pdpr;
    
    private PeriodoRecorrencia(String descricao,ProcessadorDePeriodoRecorrencia pdpr) {
        this.descricao = descricao;
        this.pdpr = pdpr;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public Date processaData(Date ultimaData){
        return this.pdpr.retornaData(ultimaData);
    }
    
}
