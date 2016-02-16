/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitarios.relatorios;

/**
 *
 * @author ari
 */
public abstract class PastasRelatorio {

    //relatórios do módulo administrativo
    public static final String RESOURCE_ADMINISTRATIVO = "WEB-INF/relatorios/administrativo";
    public static final String REL_ADMINISTRATIVO_CLIENTE = "/WEB-INF/relatorios/administrativo/rel_clientes.jasper";
    public static final String REL_ADMINISTRATIVO_FORNECEDOR = "/WEB-INF/relatorios/administrativo/rel_fornecedor.jasper";
    public static final String REL_ADMINISTRATIVO_DADOS_FORNECEDOR = "/WEB-INF/relatorios/administrativo/rel_dados_fornecedor.jasper";
    public static final String REL_ADMINISTRATIVO_COLABORADOR = "/WEB-INF/relatorios/administrativo/rel_colaborador.jasper";

  //relatórios do módulo engenharia
    public static final String RESOURCE_ENGENHARIA = "WEB-INF/relatorios/engenharia";
    public static final String REL_PROJETO_COLABORADOR = "/WEB-INF/relatorios/engenharia/rel_projeto_colaborador.jasper";
    public static final String REL_ITENS_OBRA_QUINZENA = "/WEB-INF/relatorios/engenharia/rel_itens_obra_quinzena.jasper";
    public static final String REL_ITENS_OBRA_MES = "/WEB-INF/relatorios/engenharia/rel_itens_obra_mes.jasper";
    

}
