package br.com.confidencecambio.aplicacaotemplate.aplicacao.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfiguration {

    @Bean
    public RestOperations restTemplate() {
        return new RestTemplate();
    }
}