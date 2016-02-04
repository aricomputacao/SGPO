/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.managedbean;

import br.com.sgpo.administrativo.controller.ColaboradorController;
import br.com.sgpo.administrativo.controller.MunicipioController;
import br.com.sgpo.administrativo.controller.UnidadeFederativaController;
import br.com.sgpo.administrativo.modelo.Cliente;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.administrativo.modelo.Endereco;
import br.com.sgpo.administrativo.modelo.Municipio;
import br.com.sgpo.administrativo.modelo.UnidadeFederativa;
import br.com.sgpo.engenharia.projeto.Controller.DocumentoProjetoController;
import br.com.sgpo.engenharia.projeto.Controller.MovimentacaoProjetoController;
import br.com.sgpo.engenharia.projeto.Controller.NotificacaoProjetoController;
import br.com.sgpo.engenharia.projeto.Controller.ProjetoController;
import br.com.sgpo.engenharia.projeto.Controller.TipoProjetoController;
import br.com.sgpo.engenharia.enumeration.FaseProjeto;
import br.com.sgpo.engenharia.enumeration.TipoExtencaoArquivo;
import br.com.sgpo.engenharia.projeto.modelo.DocumentoProjeto;
import br.com.sgpo.engenharia.projeto.modelo.MovimentacaoProjeto;
import br.com.sgpo.engenharia.projeto.modelo.NotificacaoProjeto;
import br.com.sgpo.engenharia.projeto.modelo.Projeto;
import br.com.sgpo.engenharia.projeto.modelo.TipoProjeto;
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
import org.primefaces.model.DualListModel;
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
    @Inject
    private MunicipioController municipioController;
    @Inject
    private UnidadeFederativaController unidadeFederativaController;
    @Inject
    private ColaboradorController colaboradorController;

    private UnidadeFederativa unidadeFederativa;
    private NotificacaoProjeto notificacaoProjeto;
    private Projeto projeto;
    private DocumentoProjeto documento;

    private List<UnidadeFederativa> listaDeUnidadeFederativas;
    private List<Municipio> listaDeMunicpios;
    private List<NotificacaoProjeto> listaDeNotificacaoProjetos;
    private List<MovimentacaoProjeto> listaMovimentacaoProjetos;
    private List<TipoProjeto> listaTipoProjetos;
    private List<Projeto> listaDeProjetos;
    private List<DocumentoProjeto> listaDeDocumentos;
    private DualListModel<Colaborador> listDualColaboradores;
    private List<Colaborador> listaColaboradorSource ;
    private List<Colaborador> listaColaboradorTarget;
   
    private boolean rederConCliente;
    private UploadedFile arquivoUpload;
    private byte docTemporario[];
    private String descricaoDocumento;
    
    
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
                projeto.setEndereco(new Endereco());
            } else {
                listaDeDocumentos = documentoProjetoController.consultarLike("projeto.nome", projeto.getNome());
                listaMovimentacaoProjetos = movimentacaoProjetoController.consultarTodos(projeto);
                listaDeNotificacaoProjetos = notificacaoController.consultarTodos(projeto);
                unidadeFederativa = projeto.getEndereco().getUnidadeFederativa();
                listaDeMunicpios = municipioController.consultarMunicipioPor(unidadeFederativa);
            }
            listaDeUnidadeFederativas = unidadeFederativaController.consultarTodosOrdenadorPor("sigla");
            listaTipoProjetos = tipoProjetoController.consultarTodosOrdenadorPor("nome");
            listaDeProjetos = new ArrayList<>();
            listaColaboradorSource = colaboradorController.consultarTodosOrdenadorPor("nome");
            listaColaboradorTarget= new ArrayList<>();
            listDualColaboradores = new DualListModel<>(listaColaboradorSource,listaColaboradorTarget);
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

    public void addNotificacao() {
        try {
            notificacaoProjeto.setColaborador(navegacaoMB.getUsuarioLogado().getColaborador());
            notificacaoProjeto.setData(new Date());
            notificacaoProjeto.setProjeto(projeto);
            notificacaoController.salvar(notificacaoProjeto,listDualColaboradores.getTarget());
            listaDeNotificacaoProjetos = notificacaoController.consultarTodos(projeto);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addDocumento() {
        try {
            documento.setProjeto(projeto);
            documentoProjetoController.addDocumento(documento, docTemporario, navegacaoMB.getUsuarioLogado(),descricaoDocumento);
            listaDeDocumentos = documentoProjetoController.consultarLike("projeto.nome", projeto.getNome());
            documento = new DocumentoProjeto();
            docTemporario = null;
            descricaoDocumento = "";
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageFatal(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarMuncipioPorUf() {
        listaDeMunicpios = municipioController.consultarMunicipioPor(unidadeFederativa);
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
            documentoProjetoController.registrarDownload(dp, navegacaoMB.getUsuarioLogado(),descricaoDocumento);
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

    public List<UnidadeFederativa> getListaDeUnidadeFederativas() {
        return listaDeUnidadeFederativas;
    }

    public List<Municipio> getListaDeMunicpios() {
        return listaDeMunicpios;
    }

    public UnidadeFederativa getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }

    public String getDescricaoDocumento() {
        return descricaoDocumento;
    }

    public void setDescricaoDocumento(String descricaoDocumento) {
        this.descricaoDocumento = descricaoDocumento;
    }

   
    public DualListModel<Colaborador> getListDualColaboradores() {
        return listDualColaboradores;
    }

    public void setListDualColaboradores(DualListModel<Colaborador> listDualColaboradores) {
        this.listDualColaboradores = listDualColaboradores;
    }

    
    
}
