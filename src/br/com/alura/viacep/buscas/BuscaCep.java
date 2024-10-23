package br.com.alura.viacep.buscas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscaCep { // Criei uma classe para buscar um CEP pelo endereço
    private String uf;
    private String localidade;
    private String logradouro;
    private String jsonBuscaCep;

    public BuscaCep(String uf, String localidade, String logradouro) { // Requisição para a API ViaCep no construtor
        this.uf = uf;
        this.localidade = localidade;
        this.logradouro = logradouro;
        String endpoint = "https://viacep.com.br/ws/" + uf + "/" +
                localidade.replace(" ", "%20") + "/" +
                logradouro.replace(" ", "%20") + "/json/";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            jsonBuscaCep = response.body();

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getJsonBuscaCep() { // Getter do json da resposta, para ser usado no programa
        return jsonBuscaCep;
    }
}
