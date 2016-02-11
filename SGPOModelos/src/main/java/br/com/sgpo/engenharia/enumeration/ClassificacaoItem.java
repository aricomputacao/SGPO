/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.enumeration;

import br.com.sgpo.utilitarios.ManipuladorDeArquivo;
import br.com.sgpo.utilitarios.ResourceUtil;

/**
 *
 * @author ari
 */
public enum ClassificacaoItem {
    
    MATERIAL(ResourceUtil.lerBundle("material", ResourceUtil.LABEL)),
    SERVICO_DE_TERCEIROS(ResourceUtil.lerBundle("servico_terceiro", ResourceUtil.LABEL)),
    SERVICO_PROPRIO(ResourceUtil.lerBundle("servico_prorpio", ResourceUtil.LABEL)),
    EQUIPAMENTO(ResourceUtil.lerBundle("equipamento", ResourceUtil.LABEL));
    
    private final String descricao;

    private ClassificacaoItem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    

}
