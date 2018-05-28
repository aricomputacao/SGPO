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
public enum Cor {
    VERDE("greenBack"),
    AZUL("blueBack"),
    AMARELO("yellowBack"),
    VERMELHO("redBack"),
    LARANJA("orangeBack");
    private final String descricao;

    private Cor(String cor) {
        this.descricao = cor;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
}
