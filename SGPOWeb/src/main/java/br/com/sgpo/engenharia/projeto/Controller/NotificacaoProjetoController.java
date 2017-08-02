/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.Controller;

import br.com.sgpo.administrativo.controller.ContaEmailController;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.administrativo.modelo.ContaEmail;
import br.com.sgpo.engenharia.projeto.DAO.NotificacaoProjetoDAO;
import br.com.sgpo.engenharia.projeto.modelo.NotificacaoProjeto;
import br.com.sgpo.engenharia.projeto.modelo.Projeto;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author ari
 */
@Stateless
public class NotificacaoProjetoController extends ControllerGenerico<NotificacaoProjeto, Long> implements Serializable {

    @Inject
    private NotificacaoProjetoDAO dao;
    @Inject
    private ContaEmailController contaEmailController;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public List<NotificacaoProjeto> consultarTodos(Projeto projeto) {
        return dao.consultarTodos(projeto);
    }

    public void salvar(NotificacaoProjeto notificacaoProjeto, List<Colaborador> listaColaboradorTarget) throws EmailException, Exception {
        ContaEmail contaEmail = contaEmailController.cosultar(notificacaoProjeto.getProjeto().getEmpresa()).get(0);
        List<String> emails = new ArrayList<>();
        String titulo;

        if (!listaColaboradorTarget.isEmpty()) {
            for (Colaborador c : listaColaboradorTarget) {
                emails.add(c.getEmail());
            }
            titulo = "Notificação Projeto - "+ notificacaoProjeto.getProjeto().getNome();
            contaEmail.enviarEmailHtml(emails, notificacaoProjeto.getMotivo(), titulo);
        }
        dao.salvar(notificacaoProjeto);

    }

    public List<NotificacaoProjeto> consultarTodosDoDia() {
       return dao.consultarTodosDoDia();
    }

}
