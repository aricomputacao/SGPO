/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.negocio;

import java.util.Date;

/**
 *
 * @author ari
 */
public interface ProcessadorDePeriodoRecorrencia {
    public Date retornaData(Date ultimaData,int quantidadeAdicional);
}
