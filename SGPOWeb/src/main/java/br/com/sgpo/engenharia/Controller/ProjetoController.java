/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.Controller;

import br.com.sgpo.administrativo.controller.EnderecoController;
import br.com.sgpo.engenharia.DAO.MovimentacaoProjetoDAO;
import br.com.sgpo.engenharia.DAO.ProjetoDAO;
import br.com.sgpo.engenharia.enumeration.FaseProjeto;
import br.com.sgpo.engenharia.modelo.MovimentacaoProjeto;
import br.com.sgpo.engenharia.modelo.Projeto;
import br.com.sgpo.seguranca.modelo.Usuario;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class ProjetoController extends ControllerGenerico<Projeto, Long> implements Serializable {

    @Inject
    private ProjetoDAO dao;
    @Inject
    private MovimentacaoProjetoDAO movimentacaoProjetoDAO;
    @Inject
    private EnderecoController enderecoController;
   

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public Projeto salvarGerenciar(Projeto p) throws Exception {
          p.setEndereco(enderecoController.buscarOuCriarLogradouroPor(p.getEndereco().getAbreviacaoUnidadeFederativa(), p.getEndereco().getNomeDaCidade(),
                p.getEndereco().getCep(), p.getEndereco().getNome(), p.getEndereco().getBairro(), p.getEndereco().getNumero(),
                p.getEndereco().getComplemento()));
        return dao.atualizarGerenciar(p);
    }

    public Projeto alterarStatusProjeto(Projeto projeto, Usuario usuarioLogado) throws Exception {
        MovimentacaoProjeto mp = new MovimentacaoProjeto();
        mp.setColaborador(usuarioLogado.getColaborador());
        mp.setProjeto(projeto);
        mp.setData(new Date());
        if (projeto.getFase().equals(FaseProjeto.EM_ANDAMENTO)) {
            projeto.setFase(FaseProjeto.CONCLUIDO);
            projeto.setDataFim(new Date());
            mp.setFase(FaseProjeto.CONCLUIDO);
        } else {
            projeto.setFase(FaseProjeto.EM_ANDAMENTO);
            projeto.setDataFim(null);
            mp.setFase(FaseProjeto.EM_ANDAMENTO);
        }
        movimentacaoProjetoDAO.salvar(mp);
        return salvarGerenciar(projeto);
    }

    public int[] processarQuantidadeDeProcesso(List<Projeto> list) {
        int qtd[] = new int[2];
        int qtdProcessoAberto = 0;
        int qtdProcessoConluido = 0;
        for (Projeto p : list) {
            switch (p.getFase()) {
                case CONCLUIDO:
                    qtdProcessoConluido++;
                    break;
                case EM_ANDAMENTO:
                    qtdProcessoAberto++;
                    break;
            }
        }
        qtd[0] = qtdProcessoConluido;
        qtd[1] = qtdProcessoAberto;
        
        return qtd;
        
    }

    public List<Projeto> consultarTodosAtivos() {
        return dao.consultarTodosAtivos();
    }

   
    
    
}
