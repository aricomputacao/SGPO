package br.com.sgpo.engenharia.obra.managedbean;

import br.com.sgpo.administrativo.controller.MunicipioController;
import br.com.sgpo.administrativo.controller.UnidadeFederativaController;
import br.com.sgpo.administrativo.modelo.Cliente;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.administrativo.modelo.Endereco;
import br.com.sgpo.administrativo.modelo.Fornecedor;
import br.com.sgpo.administrativo.modelo.Municipio;
import br.com.sgpo.administrativo.modelo.UnidadeFederativa;
import br.com.sgpo.engenharia.enumeration.TipoEquipamento;
import br.com.sgpo.engenharia.obra.Controller.EquipamentoObraController;
import br.com.sgpo.engenharia.obra.Controller.ItemObraController;
import br.com.sgpo.engenharia.obra.Controller.ObraController;
import br.com.sgpo.engenharia.obra.modelo.Equipamento;
import br.com.sgpo.engenharia.obra.modelo.EquipamentoObra;
import br.com.sgpo.engenharia.obra.modelo.Item;
import br.com.sgpo.engenharia.obra.modelo.ItemObra;
import br.com.sgpo.engenharia.obra.modelo.Obra;
import br.com.sgpo.engenharia.projeto.modelo.Projeto;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import br.com.sgpo.utilitario.relatorio.RelatorioSession;
import br.com.sgpo.utilitarios.enumeration.Mes;
import br.com.sgpo.utilitarios.relatorios.AssistentedeRelatorio;
import br.com.sgpo.utilitarios.relatorios.PastasRelatorio;
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
import org.primefaces.model.DualListModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class ObraMB extends BeanGenerico implements Serializable {

    @Inject
    private ObraController obraController;
    @Inject
    private MunicipioController municipioController;
    @Inject
    private UnidadeFederativaController unidadeFederativaController;
    @Inject
    private ItemObraController itemObraController;
    @Inject
    private EquipamentoObraController equipamentoObraController;

    private List<Obra> listaDeObras;
    private List<UnidadeFederativa> listaDeUnidadeFederativas;
    private List<Municipio> listaDeMunicpios;
    private DualListModel<Projeto> listaDualProjetos;
    private List<Projeto> listaProjetosSource;
    private List<Projeto> listaProjetosTarget;
    private List<ItemObra> listaItemObras;
    private List<EquipamentoObra> listaDeEquipamentoObras;
    private List<EquipamentoObra> listaDeEquipamentoObrasLocados;
    private List<EquipamentoObra> listaDeEquipamentoObrasProrios;

    private UnidadeFederativa unidadeFederativa;
    private Obra obra;
    private ItemObra itemObra;
    private EquipamentoObra equipamentoObra;
    private Mes mesReferencia;

    private boolean rederConCliente;

    private void initItemObra() {
        itemObra = new ItemObra();
        itemObra.setObra(new Obra());
        itemObra.setPago(false);
    }

    private void initEquipamentosDaObra() {
        listaDeEquipamentoObras = equipamentoObraController.cosultarAtivosPor(obra);
        listaDeEquipamentoObrasLocados = equipamentoObraController.processarLocadosAtivos(listaDeEquipamentoObras);
        listaDeEquipamentoObrasProrios = equipamentoObraController.processarProriosAtivos(listaDeEquipamentoObras);
    }

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            obra = (Obra) lerRegistroDaSessao("obra");
            initItemObra();
            equipamentoObra = new EquipamentoObra();
            listaProjetosSource = obraController.consultarProjetosDisponiveis();

            if (obra == null) {
                obra = new Obra();
                obra.setEndereco(new Endereco());
                obra.setListaDeProjetos(new ArrayList<>());
                listaProjetosTarget = new ArrayList<>();
                listaItemObras = new ArrayList<>();
                listaDeEquipamentoObras = new ArrayList<>();
                listaDeEquipamentoObrasLocados = new ArrayList<>();
                listaDeEquipamentoObrasProrios = new ArrayList<>();
            } else {
                unidadeFederativa = obra.getEndereco().getUnidadeFederativa();
                listaDeMunicpios = municipioController.consultarMunicipioPor(unidadeFederativa);
                listaProjetosTarget = obra.getListaDeProjetos();
                listaProjetosSource.removeAll(listaProjetosTarget);
                listaItemObras = itemObraController.consultarPor(obra);
                itemObra.setObra(obra);
                initEquipamentosDaObra();
                equipamentoObra.setObra(obra);
            }
            listaDualProjetos = new DualListModel<>(listaProjetosSource, listaProjetosTarget);
            listaDeUnidadeFederativas = unidadeFederativaController.consultarTodosOrdenadorPor("sigla");
        } catch (Exception ex) {
            Logger.getLogger(ObraMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvar() {
        try {
            obra = obraController.salvarGerenciar(obra);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ObraMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addItemObra() {
        try {
            itemObraController.salvarouAtualizar(itemObra);
            initItemObra();
            listaItemObras = itemObraController.consultarPor(obra);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ObraMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pagarItemObra(ItemObra io) {
        try {
            io.setPago(true);
            io.setDataPagamento(new Date());
            itemObraController.atualizar(io);
            listaItemObras = itemObraController.consultarPor(obra);
        } catch (Exception ex) {
            Logger.getLogger(ObraMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addEquipamentoObra() {
        try {
            equipamentoObraController.salvarouAtualizar(equipamentoObra);
            equipamentoObra = new EquipamentoObra();
            initEquipamentosDaObra();
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ObraMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desativarEquipamentoObra(EquipamentoObra ep) {
        try {
            ep.setAtivo(false);
            equipamentoObraController.atualizar(ep);
            initEquipamentosDaObra();
        } catch (Exception ex) {
            Logger.getLogger(ObraMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public void gerenciarProjeto() {
        try {
            obra = obraController.salvarGerenciar(obra, listaDualProjetos.getTarget());
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ObraMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarObras() {
        try {
            listaDeObras = obraController.consultarTodos("id", getCampoConsuta(), getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(ObraMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetarItemObra() {
        itemObra = new ItemObra();
    }

    public void geraImpressaoItensDaObraQuinzena() {
        try {
            List<ItemObra> itensObra = itemObraController.consultarPor(obra, mesReferencia);
            Map<String, Object> m = new HashMap<>();
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(itensObra, m, PastasRelatorio.RESOURCE_ENGENHARIA, PastasRelatorio.REL_ITENS_OBRA_QUINZENA, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
//            erroCliente.adicionaErro(e);
        }
    }

    public void geraImpressaoItensDaObraMes() {
        try {
            List<ItemObra> itensObra = itemObraController.consultarPor(obra);
            Map<String, Object> m = new HashMap<>();
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(itensObra, m, PastasRelatorio.RESOURCE_ENGENHARIA, PastasRelatorio.REL_ITENS_OBRA_MES, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
        }
    }

    public void geraImpressaoItensDoFornecedor(Fornecedor f) {
        try {
            List<ItemObra> itensObra = itemObraController.consultarPor(obra, f);
            Map<String, Object> m = new HashMap<>();
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(itensObra, m, PastasRelatorio.RESOURCE_ENGENHARIA, PastasRelatorio.REL_ITENS_FORNECEDOR, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
        }
    }

    public void geraImpressaoItensEtapaTipo() {
        try {
            List<ItemObra> itensObra = itemObraController.consultarOrdenadoPorEtapaTipo(obra);
            Map<String, Object> m = new HashMap<>();
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(itensObra, m, PastasRelatorio.RESOURCE_ENGENHARIA, PastasRelatorio.REL_ITENS_ETAPA_TIPO, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
        }
    }

    public void geraImpressaoEquipamentoObra() {
        try {
            Map<String, Object> m = new HashMap<>();
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(listaDeEquipamentoObras, m, PastasRelatorio.RESOURCE_ENGENHARIA, PastasRelatorio.REL_EQUIPAMENTOS_OBRA, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
        }
    }

    public boolean renderLink() {
        return obra.getId() != null;
    }

    public void consultarMuncipioPorUf() {
        listaDeMunicpios = municipioController.consultarMunicipioPor(unidadeFederativa);
    }

    public void processarCampoConsulta() {
        rederConCliente = getCampoConsuta().equals("cliente.nome");
    }

    public void setarCliente(Cliente c) {
        obra.setCliente(c);
    }

    public void setarResponsavel(Colaborador c) {
        obra.setResponsavel(c);
    }

    public void setarFornecedorItem(Fornecedor f) {
        itemObra.setFornecedor(f);
    }

    public void setarFornecedorEquipamento(Fornecedor f) {
        equipamentoObra.setFornecedor(f);
    }

    public void setarItem(Item i) {
        itemObra.setItem(i);
    }

    public void setarEquipamento(Equipamento ep) {
        equipamentoObra.setEquipamento(ep);
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Descricao", "descricao");
        map.put("Cliente", "cliente.nome");
        return map;
    }

    public List<Obra> getListaDeObras() {
        return listaDeObras;
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

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public boolean isRederConCliente() {
        return rederConCliente;
    }

    public DualListModel<Projeto> getListaDualProjetos() {
        return listaDualProjetos;
    }

    public void setListaDualProjetos(DualListModel<Projeto> listaDualProjetos) {
        this.listaDualProjetos = listaDualProjetos;
    }

    public ItemObra getItemObra() {
        return itemObra;
    }

    public void setItemObra(ItemObra itemObra) {
        this.itemObra = itemObra;
    }

    public List<ItemObra> getListaItemObras() {
        return listaItemObras;
    }

    public Mes getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(Mes mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public Mes[] getListaDeMeses() {
        return Mes.values();
    }

    public EquipamentoObra getEquipamentoObra() {
        return equipamentoObra;
    }

    public void setEquipamentoObra(EquipamentoObra equipamentoObra) {
        this.equipamentoObra = equipamentoObra;
    }

    public List<EquipamentoObra> getListaDeEquipamentoObras() {
        return listaDeEquipamentoObras;
    }

    public List<EquipamentoObra> getListaDeEquipamentoObrasLocados() {
        return listaDeEquipamentoObrasLocados;
    }

    public List<EquipamentoObra> getListaDeEquipamentoObrasProrios() {
        return listaDeEquipamentoObrasProrios;
    }

}
