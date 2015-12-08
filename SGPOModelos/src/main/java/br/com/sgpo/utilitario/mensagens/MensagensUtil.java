package br.com.sgpo.utilitario.mensagens;

import br.com.sgpo.utilitarios.ResourceUtil;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ari
 */
public  class MensagensUtil implements Serializable {

    public static final String ARQUIVO_RECEBIDO = "arquivo_recebido";
    public static final String CEP_NAO_ENCONTRADO = "cep_nao_encontrado";
    public static final String CONFIRMACAO = "confirmacao";
    public static final String CONSULTA_VAZIA = "consulta_vazia";
    public static final String DOCUMENTO_INVALIDO = "documento_invalido";
    public static final String ERRO_PESSOA = "erro_pessoa";
    public static final String ERRO_RELATORIO = "erro_relatorio";
    public static final String FALHA_CONSULTA = "falha_consulta";
    public static final String REGISTRO_ATUALIZADO = "registro_atualizado";
    public static final String REGISTRO_DESATIVADO = "registro_desativado";
    public static final String REGISTRO_FALHA = "registro_falha";
    public static final String REGISTRO_SUCESSO = "registro_sucesso";
    public static final String SENHA_INVALIDA = "senha_diferente";
    public static final String SERVIDOR_INDISPONIVEL = "servidor_indisponivel";
    public static final String EXCLUIR_SUCESSO = "excluir_sucesso";
    public static final String EXCLUIR_FALHA = "excluir_falha";

    

    public static void enviarMessageInfo(String idMensagem) {
        System.out.println(ResourceUtil.lerBundle(idMensagem, ResourceUtil.MENSAGENS));
        enviarMenssagem(null, FacesMessage.SEVERITY_INFO, "Informação", ResourceUtil.lerBundle(idMensagem, ResourceUtil.MENSAGENS));
    }

    public static void enviarMessageWarn(String idMensagem) {
        enviarMenssagem(null, FacesMessage.SEVERITY_WARN, "Atenção", ResourceUtil.lerBundle(idMensagem, ResourceUtil.MENSAGENS));
    }

    public static void enviarMessageFatal(String idMensagem) {
        enviarMenssagem(null, FacesMessage.SEVERITY_FATAL, "Fatal", ResourceUtil.lerBundle(idMensagem, ResourceUtil.MENSAGENS));
    }

    public static void enviarMessageErro(String idMensagem, Exception erro, String nomeClasse) {
        enviarMenssagem(null, FacesMessage.SEVERITY_ERROR, "Erro", ResourceUtil.lerBundle(idMensagem, ResourceUtil.MENSAGENS));

    }

    public static void enviarMessageErro(Exception erro) {
        enviarMenssagem(null, FacesMessage.SEVERITY_ERROR, "Erro", erro.getMessage());
    }

    public static void enviarMessageErro(String idMensagem) {
        enviarMenssagem(null, FacesMessage.SEVERITY_ERROR, "Erro", ResourceUtil.lerBundle(idMensagem, ResourceUtil.MENSAGENS));
    }

    public static void enviarMenssagem(String clientId, FacesMessage.Severity severity, String sumario, String detalhe) {
        FacesContext c = FacesContext.getCurrentInstance();
        FacesMessage m = new FacesMessage(severity, sumario, detalhe);
        c.addMessage(clientId, m);
    }

   
    

    public static void main(String[] args) {
      
//        System.out.println(MensagensUtil.getMsg("confirmacao", MensagensUtil.MENSAGENS, new Locale("pt","Br")));
//        System.out.println(ResourceUtil.lerBundle("confirmacao",ResourceUtil.MENSAGENS,new Locale("en", "US")));
    }
}
