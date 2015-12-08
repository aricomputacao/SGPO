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

    //relatórios do módulo básicos
    public static final String RESOURCE_CADASTRO_BASICO = "WEB-INF/relatorios/cadastro_basico";
    public static final String REL_CADASTRO_BASICO_PAIS = "/WEB-INF/relatorios/cadastro_basico/paises.jasper";

    //relatórios do modulo centro
    public static final String RESOURCE_CENTRO = "WEB-INF/relatorios/centro";
    public static final String REL_CENTRO_FIQUE_LIGADO = "/WEB-INF/relatorios/centro/fique_ligado.jasper";

    //relatórios do módulo secretaria
    public static final String RESOURCE_SECRETARIA = "WEB-INF/relatorios/secretaria";
    public static final String REL_SECRETARIA_AGENDA = "/WEB-INF/relatorios/secretaria/agenda.jasper";
    public static final String REL_SECRETARIA_ANIVERSARIANTES_MES = "/WEB-INF/relatorios/secretaria/aniversariantes_mes.jasper";
    public static final String REL_SECRETARIA_CARTA_APRESENTACAO = "/WEB-INF/relatorios/secretaria/carta_apresentacao.jasper";
    public static final String REL_SECRETARIA_FICHA_SOCIO = "/WEB-INF/relatorios/secretaria/ficha_socio.jasper";

}
