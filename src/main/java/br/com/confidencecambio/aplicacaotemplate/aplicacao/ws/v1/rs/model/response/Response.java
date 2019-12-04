package br.com.confidencecambio.aplicacaotemplate.aplicacao.ws.v1.rs.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    @ApiModelProperty(notes = "Retorno em caso de sucesso")
    private T payload;

    @ApiModelProperty(notes = "Codigos de erro em caso de falha na requisição")
    private List<Erro> erros;

    public Response() {
    }

    public Response(final T payload) {
        this.payload = payload;
    }

    public Response(final List<Erro> errorCodes) {
        this.erros = errorCodes;
    }

    public Response(final Erro... errorCodes) {
        this.erros = Arrays.asList(errorCodes);
    }

    public T getPayload() {
        return payload;
    }

    public List<Erro> getErros() {
        return erros;
    }
}