package br.com.sgpo.engenharia.obra.managedbean;

import br.com.sgpo.administrativo.controller.ColaboradorController;
import br.com.sgpo.administrativo.controller.MunicipioController;
import br.com.sgpo.administrativo.controller.UnidadeFederativaController;
import br.com.sgpo.administrativo.modelo.Cliente;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.administrativo.modelo.Endereco;
import br.com.sgpo.administrativo.modelo.Municipio;
import br.com.sgpo.administrativo.modelo.UnidadeFederativa;
import br.com.sgpo.engenharia.obra.Controller.ObraController;
import br.com.sgpo.engenharia.obra.modelo.Obra;
import br.com.sgpo.engenharia.projeto.Controller.ProjetoController;
import br.com.sgpo.engenharia.projeto.modelo.Projeto;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
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
import org.jfree.ui.about.ProjectInfo;
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
  

    private List<Obra> listaDeObras;
    private List<UnidadeFederativa> listaDeUnidadeFederativas;
    private List<Municipio> listaDeMunicpios;
    private DualListModel<Projeto> listaDualProjetos;
    private List<Projeto> listaProjetosSource;
    private List<Projeto> listaProjetosTarget;

    private UnidadeFederativa unidadeFederativa;
    private Obra obra;

    private boolean rederConCliente;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            obra = (Obra) lerRegistroDaSessao("obra");
            listaProjetosSource = obraController.consultarProjetosDisponiveis();
            if (obra == null) {
                obra = new Obra();
                obra.setEndereco(new Endereco());
                obra.setListaDeProjetos(new ArrayList<>());
                listaProjetosTarget = new ArrayList<>();
            } else {
                unidadeFederativa = obra.getEndereco().getUnidadeFederativa();
                listaDeMunicpios = municipioController.consultarMunicipioPor(unidadeFederativa);
                listaProjetosTarget = obra.getListaDeProjetos();
                listaProjetosSource.removeAll(listaProjetosTarget);

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

}
