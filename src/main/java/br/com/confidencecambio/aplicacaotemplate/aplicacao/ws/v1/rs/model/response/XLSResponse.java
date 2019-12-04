package br.com.confidencecambio.aplicacaotemplate.aplicacao.ws.v1.rs.model.response;

import java.util.Date;
import java.util.List;

public class XLSResponse {
    String dtInicioProcessamento;
    String dtTerminoProcessamento;
    List<SheetResponse> sheets;

    public XLSResponse(String dtInicioProcessamento, String dtTerminoProcessamento, List<SheetResponse> sheets) {
        this.dtInicioProcessamento = dtInicioProcessamento;
        this.dtTerminoProcessamento = dtTerminoProcessamento;
        this.sheets = sheets;
    }

    public XLSResponse() {
    }

    public void setDtInicioProcessamento(String dtInicioProcessamento) {
        this.dtInicioProcessamento = dtInicioProcessamento;
    }

    public void setDtTerminoProcessamento(String dtTerminoProcessamento) {
        this.dtTerminoProcessamento = dtTerminoProcessamento;
    }

    public void setSheets(List<SheetResponse> sheets) {
        this.sheets = sheets;
    }

    public String getDtInicioProcessamento() {
        return dtInicioProcessamento;
    }

    public String getDtTerminoProcessamento() {
        return dtTerminoProcessamento;
    }

    public List<SheetResponse> getSheets() {
        return sheets;
    }
}
