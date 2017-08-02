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
public enum TipoCliente {
    
    PESSOA_JURIDICA("Pessoa Jurídica"),
    PESSOA_FISICA("Pessoa Física"),
    INSTITUICAO_PUBLICA("Instituição Pública");
    
    private final String descricao;
    
    private TipoCliente(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
}
