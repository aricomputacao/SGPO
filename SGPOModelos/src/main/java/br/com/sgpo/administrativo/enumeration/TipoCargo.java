/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.enumeration;

/**
 *
 * @author ari
 */
public enum TipoCargo {
    GERENTE("Gerente"),
    SUPERVISOR("Supervisor"),
    ESTAGIARIO("Estagiário"),
    ENGENHEIRO("Engenheiro"),
    OUTRO("Outro");
    
    private final String descricao;

    private TipoCargo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
}
