package br.com.confidencecambio.aplicacaotemplate.aplicacao.ws.v1.rs.model.response;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "informações de erro")
public class Erro {

    private String codigo;
    private String mensagem;

    public Erro() {
    }

    public Erro(final String mensagem) {
        this.mensagem = mensagem;
    }

    public Erro(final String mensagem, final String codigo) {
        this.mensagem = mensagem;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
