package br.com.confidencecambio.aplicacaotemplate.aplicacao.ws.v1.rs.model.response;

import java.util.List;

public class SheetResponse {
    private String saldoInicial;
    private List<RowResponse> rows;

    public SheetResponse(String saldoInicial, List<RowResponse> rows) {
        this.saldoInicial = saldoInicial;
        this.rows = rows;
    }

    public SheetResponse() {
    }

    public void setSaldoInicial(String saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public void setRows(List<RowResponse> rows) {
        this.rows = rows;
    }

    public String getSaldoInicial() {
        return saldoInicial;
    }

    public List<RowResponse> getRows() {
        return rows;
    }

}
