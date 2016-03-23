/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.managedbean;

import br.com.sgpo.administrativo.controller.ColaboradorController;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.engenharia.obra.Controller.ObraController;
import br.com.sgpo.engenharia.obra.modelo.Obra;
import br.com.sgpo.engenharia.projeto.Controller.NotificacaoProjetoController;
import br.com.sgpo.engenharia.projeto.Controller.ProjetoController;
import br.com.sgpo.engenharia.projeto.modelo.NotificacaoProjeto;
import br.com.sgpo.engenharia.projeto.modelo.Projeto;
import br.com.sgpo.financeiro.controller.FaturaController;
import br.com.sgpo.financeiro.dto.DemonstrativoFinanceiroDTO;
import br.com.sgpo.financeiro.modelo.FaturaOperacao;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.IndexMB;
import br.com.sgpo.utilitario.UtilitarioNavegacaoMB;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class DadosInicialMB extends BeanGenerico implements Serializable {

    @Inject
    private ProjetoController projetoController;
    @Inject
    private NotificacaoProjetoController notificacaoProjetoController;
    @Inject
    private UtilitarioNavegacaoMB navegacaoMB;
    @Inject
    private ColaboradorController colaboradorController;
    @Inject
    private ObraController obraController;
    @Inject
    private IndexMB indexMB;
    @Inject
    private FaturaController faturaController;

    private List<FaturaOperacao> listaDeFaturas;
    private List<Projeto> listaDeProjetos;
    private List<NotificacaoProjeto> listaDeNotificacaoProjetos;
    private List<Obra> listaDeObras;

    private DualListModel<Colaborador> listDualColaboradores;
    private List<Colaborador> listaColaboradorSource;
    private List<Colaborador> listaColaboradorTarget;

    private NotificacaoProjeto notificacaoProjeto;
    private Projeto projeto;
    private DemonstrativoFinanceiroDTO demonstrativoFinanceiro;
    private FaturaOperacao faturaOperacao;

    private Date dataReferencia;

    @PostConstruct
    @Override
    public void init() {
        try {
            listaDeProjetos = projetoController.consultarTodosAtivos();
            notificacaoProjeto = new NotificacaoProjeto();
            projeto = new Projeto();

            
            listaDeObras = obraController.consultarObrasEmAndamento();

            dataReferencia = new Date();
            listaDeFaturas = faturaController.consultarPor(dataReferencia);
            demonstrativoFinanceiro = new DemonstrativoFinanceiroDTO(listaDeFaturas, dataReferencia);

            listaColaboradorSource = colaboradorController.consultarTodosOrdenadorPor("nome");
            listaColaboradorTarget = new ArrayList<>();
            listDualColaboradores = new DualListModel<>(listaColaboradorSource, listaColaboradorTarget);

        } catch (Exception ex) {
            Logger.getLogger(DadosInicialMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNotificacaoProjeto() {
        try {
            notificacaoProjeto.setColaborador(navegacaoMB.getUsuarioLogado().getColaborador());
            notificacaoProjeto.setData(new Date());
            notificacaoProjeto.setProjeto(projeto);
            notificacaoProjetoController.salvar(notificacaoProjeto, listDualColaboradores.getTarget());
            notificacaoProjeto = new NotificacaoProjeto();
            listaDeNotificacaoProjetos = notificacaoProjetoController.consultarTodos(projeto);
            indexMB.init();
        } catch (Exception ex) {
            Logger.getLogger(DadosInicialMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarDemonstrativoFinanceiro() {
        listaDeFaturas = faturaController.consultarPor(dataReferencia);
        demonstrativoFinanceiro = new DemonstrativoFinanceiroDTO(listaDeFaturas, dataReferencia);
    }

    public void salvarFatura(){
        try {
            faturaController.atualizar(faturaOperacao);
            consultarDemonstrativoFinanceiro();
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(DadosInicialMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluirFatura(FaturaOperacao fo) {
        try {
            demonstrativoFinanceiro.removerFatura(fo);
            faturaController.excluir(fo);
            consultarDemonstrativoFinanceiro();
            MensagensUtil.enviarMessageInfo(MensagensUtil.EXCLUIR_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.EXCLUIR_FALHA);
            Logger.getLogger(DadosInicialMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarNotificacoes(Projeto p) {
        listaDeNotificacaoProjetos = notificacaoProjetoController.consultarTodos(p);
        projeto = p;
    }

    public List<Projeto> getListaDeProjetos() {
        return listaDeProjetos;
    }

    public List<Obra> getListaDeObras() {
        return listaDeObras;
    }

    public List<NotificacaoProjeto> getListaDeNotificacaoProjetos() {
        return listaDeNotificacaoProjetos;
    }

    public NotificacaoProjeto getNotificacaoProjeto() {
        return notificacaoProjeto;
    }

    public void setNotificacaoProjeto(NotificacaoProjeto notificacaoProjeto) {
        this.notificacaoProjeto = notificacaoProjeto;
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DualListModel<Colaborador> getListDualColaboradores() {
        return listDualColaboradores;
    }

    public void setListDualColaboradores(DualListModel<Colaborador> listDualColaboradores) {
        this.listDualColaboradores = listDualColaboradores;
    }

    public DemonstrativoFinanceiroDTO getDemonstrativoFinanceiro() {
        return demonstrativoFinanceiro;
    }

    public Date getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(Date dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public FaturaOperacao getFaturaOperacao() {
        return faturaOperacao;
    }

    public void setFaturaOperacao(FaturaOperacao faturaOperacao) {
        this.faturaOperacao = faturaOperacao;
    }

}
