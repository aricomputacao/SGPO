/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.enumeration;

/**
 *
 * @author ari
 */
public enum TipoEquipamento {
    PROPRIO("Próprio"),
    LOCADO("Locado");
    
    private final String descricao;

    private TipoEquipamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
    
    
}
