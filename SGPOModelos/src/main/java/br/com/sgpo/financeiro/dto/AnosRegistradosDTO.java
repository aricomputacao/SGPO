/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.dto;

import java.io.Serializable;

/**
 *
 * @author ari
 */
public class AnosRegistradosDTO implements Serializable{
    
    private int ano;

    public AnosRegistradosDTO(int ano) {
        this.ano = ano;
    }

    public AnosRegistradosDTO() {
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    
}
