/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.managedbean;

import br.com.sgpo.engenharia.Controller.ProjetoController;
import br.com.sgpo.engenharia.modelo.Projeto;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.relatorio.RelatorioSession;
import br.com.sgpo.utilitarios.relatorios.AssistentedeRelatorio;
import br.com.sgpo.utilitarios.relatorios.PastasRelatorio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class RelatorioProjetoColaboradorMB extends BeanGenerico implements Serializable {

    @Inject
    private ProjetoController projetoController;

    private List<Projeto> listaDeProjetos;
    private int[] qtdProjeto;

    @PostConstruct
    @Override
    public void init() {
        listaDeProjetos = new ArrayList<>();
    }

    public void consultarProjetos() {
        try {
            listaDeProjetos = projetoController.consultarTodos("responsavel.nome", "responsavel.nome", getValorCampoConsuta());
            qtdProjeto = projetoController.processarQuantidadeDeProcesso(listaDeProjetos);

        } catch (Exception ex) {
            Logger.getLogger(RelatorioProjetoColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Projeto> getListaDeProjetos() {
        return listaDeProjetos;
    }

    public void geraImpressaoProjetosColaborador() {
        try {
            Map<String, Object> m = new HashMap<>();
            m.put("qtdAberto", qtdProjeto[0]);
            m.put("qtdConcluido", qtdProjeto[1]);
            m.put("total", qtdProjeto[1]+qtdProjeto[0]);
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(listaDeProjetos, m, PastasRelatorio.RESOURCE_ENGENHARIA, PastasRelatorio.REL_PROJETO_COLABORADOR, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
//            erroCliente.adicionaErro(e);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int[] getQtdProjeto() {
        return qtdProjeto;
    }

  

}
