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

    //sub rels do módulo administrativo
    public static final String RESOURCE_SUB_RELATORIOS = "WEB-INF/relatorios/sub_relatorios";

    //relatórios do módulo administrativo
    public static final String RESOURCE_ADMINISTRATIVO = "WEB-INF/relatorios/administrativo";
    public static final String REL_ADMINISTRATIVO_CLIENTE = "/WEB-INF/relatorios/administrativo/rel_clientes.jasper";
    public static final String REL_ADMINISTRATIVO_FORNECEDOR = "/WEB-INF/relatorios/administrativo/rel_fornecedor.jasper";
    public static final String REL_ADMINISTRATIVO_DADOS_FORNECEDOR = "/WEB-INF/relatorios/administrativo/rel_dados_fornecedor.jasper";
    public static final String REL_ADMINISTRATIVO_COLABORADOR = "/WEB-INF/relatorios/administrativo/rel_colaborador.jasper";
    public static final String REL_ADMINISTRATIVO_FALTA_COLABORADOR = "/WEB-INF/relatorios/administrativo/rel_falta_colaborador.jasper";

    //relatórios do módulo engenharia
    public static final String RESOURCE_ENGENHARIA = "WEB-INF/relatorios/engenharia";
    public static final String REL_PROJETO_COLABORADOR = "/WEB-INF/relatorios/engenharia/rel_projeto_colaborador.jasper";
    public static final String REL_ITENS_OBRA_QUINZENA = "/WEB-INF/relatorios/engenharia/rel_itens_obra_quinzena.jasper";
    public static final String REL_ITENS_OBRA_MES = "/WEB-INF/relatorios/engenharia/rel_itens_obra_mes.jasper";
    public static final String REL_ITENS_FORNECEDOR = "/WEB-INF/relatorios/engenharia/rel_itens_obra_fornecedor.jasper";
    public static final String REL_ITENS_ETAPA_TIPO = "/WEB-INF/relatorios/engenharia/rel_itens_obra_etapa_tipo.jasper";
    public static final String REL_EQUIPAMENTOS_OBRA = "/WEB-INF/relatorios/engenharia/rel_equipamento_obra.jasper";
    public static final String REL_COLABORADOR_OBRA = "/WEB-INF/relatorios/engenharia/rel_colaborador_obra.jasper";

    //relatórios do módulo engenharia
    public static final String RESOURCE_FINANCEIRO = "WEB-INF/relatorios/financeiro";
    public static final String REL_DEMONSTRATIVO_SINTETICO_DESPESA_MENSA = "/WEB-INF/relatorios/financeiro/demonstrativo_despesa_mensal.jasper";

}
