/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.Controller;

import br.com.sgpo.engenharia.projeto.DAO.MovimentacaoDocumentoDAO;
import br.com.sgpo.engenharia.enumeration.TipoMovimentacaoDocumento;
import br.com.sgpo.engenharia.projeto.DAO.DocumentoProjetoDAO;
import br.com.sgpo.engenharia.projeto.modelo.DocumentoProjeto;
import br.com.sgpo.engenharia.projeto.modelo.MovimentacaoDocumento;
import br.com.sgpo.seguranca.modelo.Usuario;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class DocumentoProjetoController extends ControllerGenerico<DocumentoProjeto, Long> implements Serializable {

    @Inject
    private DocumentoProjetoDAO dao;
    @Inject
    private MovimentacaoDocumentoDAO movimentacaoDAO;
   

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public void addDocumento(DocumentoProjeto documentoProjeto, byte[] conteudo, Usuario usuario,String descricao) throws IOException, Exception {

        MovimentacaoDocumento md = new MovimentacaoDocumento();
        md.setData(new Date());
        md.setTipo(TipoMovimentacaoDocumento.UPLOAD);
        md.setColaboradorNovo(usuario.getColaborador());
        md.setDescricao(descricao);

        if (documentoProjeto.getId() == null) {
            md.setColaboradorAntigo(usuario.getColaborador());
        } else {
            md.setColaboradorAntigo(documentoProjeto.getUsuario().getColaborador());
        }

        documentoProjeto.setUsuario(usuario);
        documentoProjeto.setAtivo(true);
        documentoProjeto.setDataUpload(new Date());
        documentoProjeto = dao.atualizarGerenciar(documentoProjeto);

        md.setDocumentoProjeto(documentoProjeto);
        movimentacaoDAO.salvar(md);

        if (conteudo != null) {
            documentoProjeto.addDocumento(conteudo);
        }

    }

    public void registrarDownload(DocumentoProjeto dp, Usuario u,String descricao) throws Exception {
        MovimentacaoDocumento md = new MovimentacaoDocumento();
        md.setColaboradorAntigo(dp.getUsuario().getColaborador());
        md.setColaboradorNovo(u.getColaborador());
        md.setData(new Date());
        md.setDocumentoProjeto(dp);
        md.setTipo(TipoMovimentacaoDocumento.DOWNLOAD);
        md.setDescricao(descricao);
        movimentacaoDAO.salvar(md);

        dp.setAtivo(false);
        dp.setUsuario(u);
        dao.atualizar(dp);

       
        
    }
}
