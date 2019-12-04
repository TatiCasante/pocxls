package br.com.confidencecambio.aplicacaotemplate.aplicacao.ws.v1.rs.model.response;

import java.math.BigDecimal;
import java.util.Date;

public class RowResponse {
    private String associacao;
    private String data;
    private String descricao;
    private String debito;
    private String credito;
    private String saldo;
    private String conciliacao;
    private String origem;
    private String proposta;
    private String loja;

    public RowResponse() {
    }

    public void setAssociacao(String associacao) {
        this.associacao = associacao;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDebito(String debito) {
        this.debito = debito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public void setConciliacao(String conciliacao) {
        this.conciliacao = conciliacao;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setProposta(String proposta) {
        this.proposta = proposta;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public RowResponse(String associacao, String data, String descricao, String debito, String credito, String saldo, String conciliacao, String origem, String proposta, String loja) {
        this.associacao = associacao;
        this.data = data;
        this.descricao = descricao;
        this.debito = debito;
        this.credito = credito;
        this.saldo = saldo;
        this.conciliacao = conciliacao;
        this.origem = origem;
        this.proposta = proposta;
        this.loja = loja;
    }

    public String getAssociacao() {
        return associacao;
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDebito() {
        return debito;
    }

    public String getCredito() {
        return credito;
    }

    public String getSaldo() {
        return saldo;
    }

    public String getConciliacao() {
        return conciliacao;
    }

    public String getOrigem() {
        return origem;
    }

    public String getProposta() {
        return proposta;
    }

    public String getLoja() {
        return loja;
    }
}
