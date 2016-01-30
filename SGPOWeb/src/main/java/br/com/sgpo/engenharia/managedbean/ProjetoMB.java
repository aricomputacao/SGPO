/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.managedbean;

import br.com.sgpo.administrativo.modelo.Cliente;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.engenharia.Controller.DocumentoProjetoController;
import br.com.sgpo.engenharia.Controller.MovimentacaoProjetoController;
import br.com.sgpo.engenharia.Controller.NotificacaoProjetoController;
import br.com.sgpo.engenharia.Controller.ProjetoController;
import br.com.sgpo.engenharia.Controller.TipoProjetoController;
import br.com.sgpo.engenharia.enumeration.FaseProjeto;
import br.com.sgpo.engenharia.enumeration.TipoExtencaoArquivo;
import br.com.sgpo.engenharia.modelo.DocumentoProjeto;
import br.com.sgpo.engenharia.modelo.MovimentacaoProjeto;
import br.com.sgpo.engenharia.modelo.NotificacaoProjeto;
import br.com.sgpo.engenharia.modelo.Projeto;
import br.com.sgpo.engenharia.modelo.TipoProjeto;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.UtilitarioNavegacaoMB;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import br.com.sgpo.utilitarios.StringUtil;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class ProjetoMB extends BeanGenerico implements Serializable {

    @Inject
    private UtilitarioNavegacaoMB navegacaoMB;
    @Inject
    private ProjetoController projetoController;
    @Inject
    private DocumentoProjetoController documentoProjetoController;
    @Inject
    private TipoProjetoController tipoProjetoController;
    @Inject
    private MovimentacaoProjetoController movimentacaoProjetoController;
    @Inject
    private NotificacaoProjetoController notificacaoController;
    
    private NotificacaoProjeto notificacaoProjeto;
    private Projeto projeto;
    private DocumentoProjeto documento;

    private List<NotificacaoProjeto> listaDeNotificacaoProjetos;
    private List<MovimentacaoProjeto> listaMovimentacaoProjetos;
    private List<TipoProjeto> listaTipoProjetos;
    private List<Projeto> listaDeProjetos;
    private List<DocumentoProjeto> listaDeDocumentos;
    private boolean rederConCliente;
    private UploadedFile arquivoUpload;
    private byte docTemporario[];

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            projeto = (Projeto) lerRegistroDaSessao("projeto");
            documento = new DocumentoProjeto();
            notificacaoProjeto = new NotificacaoProjeto();
            if (projeto == null) {
                projeto = new Projeto();
                projeto.setFase(FaseProjeto.EM_ANDAMENTO);
            } else {
                listaDeDocumentos = documentoProjetoController.consultarLike("projeto.nome", projeto.getNome());
                listaMovimentacaoProjetos = movimentacaoProjetoController.consultarTodos(projeto);
                listaDeNotificacaoProjetos = notificacaoController.consultarTodos(projeto);
            }

            listaTipoProjetos = tipoProjetoController.consultarTodosOrdenadorPor("nome");
            listaDeProjetos = new ArrayList<>();
            rederConCliente = false;
        } catch (Exception ex) {
            Logger.getLogger(ProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            projeto = projetoController.salvarGerenciar(projeto);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void alterarStatusProjeto() {
        try {
            projeto = projetoController.alterarStatusProjeto(projeto, navegacaoMB.getUsuarioLogado());
            listaMovimentacaoProjetos = movimentacaoProjetoController.consultarTodos(projeto);
            MensagensUtil.enviarMessageInfo(MensagensUtil.PROJETO_CONCLUIDO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageWarn(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void consultarProjetos() {
        try {
            listaDeProjetos = projetoController.consultarTodos("id", getCampoConsuta(), getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(ProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNotificacao(){
        try {
            notificacaoProjeto.setColaborador(navegacaoMB.getUsuarioLogado().getColaborador());
            notificacaoProjeto.setData(new Date());
            notificacaoProjeto.setProjeto(projeto);
            notificacaoController.salvar(notificacaoProjeto);
            listaDeNotificacaoProjetos = notificacaoController.consultarTodos(projeto);
        } catch (Exception ex) {
            Logger.getLogger(ProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addDocumento() {
        try {
            documento.setProjeto(projeto);
            documentoProjetoController.addDocumento(documento, docTemporario, navegacaoMB.getUsuarioLogado());
            listaDeDocumentos = documentoProjetoController.consultarLike("projeto.nome", projeto.getNome());
            documento = new DocumentoProjeto();
            docTemporario = null;
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageFatal(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fileUploud(FileUploadEvent event) {
        try {

            docTemporario = event.getFile().getContents();
            documento.setNome(StringUtil.removeAccentos(event.getFile().getFileName()).trim()
                    .replaceAll(documento.getExtencaoArquivo().getExtencao(), ""));
        } catch (Exception ex) {
            Logger.getLogger(ProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StreamedContent download(DocumentoProjeto dp) {
        try {
            documentoProjetoController.reistrarDownload(dp, navegacaoMB.getUsuarioLogado());
            listaDeDocumentos = documentoProjetoController.consultarLike("projeto.nome", projeto.getNome());

            return dp.download();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setarDocumento(DocumentoProjeto dp) {
        documento = dp;
    }

    public boolean renderAtalhoF1() {
        return projeto.getId() != null;
    }

    public boolean renderAtalhoF2() {
        return projeto.getId() != null;
    }

    public void processarCampoConsulta() {
        rederConCliente = getCampoConsuta().equals("cliente.nome");
    }

    public void setarCliente(Cliente c) {
        projeto.setCliente(c);
    }

    public void setarResponsavel(Colaborador c) {
        projeto.setResponsavel(c);
    }

    public List<TipoProjeto> getListaTipoProjetos() {
        return listaTipoProjetos;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public FaseProjeto[] getListaDeFasesProjeto() {
        return FaseProjeto.values();
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Nome", "nome");
        map.put("Cliente", "cliente.nome");
        return map;
    }

    public List<Projeto> getListaDeProjetos() {
        return listaDeProjetos;
    }

    public boolean isRederConCliente() {
        return rederConCliente;
    }

    public DocumentoProjeto getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoProjeto documento) {
        this.documento = documento;
    }

    public List<DocumentoProjeto> getListaDeDocumentos() {
        return listaDeDocumentos;
    }

    public TipoExtencaoArquivo[] getListaDeExtencao() {
        return TipoExtencaoArquivo.values();
    }

    public UploadedFile getArquivoUpload() {
        return arquivoUpload;
    }

    public void setArquivoUpload(UploadedFile arquivoUpload) {
        this.arquivoUpload = arquivoUpload;
    }

    public List<MovimentacaoProjeto> getListaMovimentacaoProjetos() {
        return listaMovimentacaoProjetos;
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

    
}
