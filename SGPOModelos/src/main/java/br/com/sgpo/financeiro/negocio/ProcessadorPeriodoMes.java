/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.negocio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ari
 */
public class ProcessadorPeriodoMes implements ProcessadorDePeriodoRecorrencia,Serializable{

    @Override
    public Date retornaData(Date ultimaData) {
        Calendar c = Calendar.getInstance();
        c.setTime(ultimaData);
        c.add(Calendar.MONTH, 1);
        
        return c.getTime();
    }
    
}
