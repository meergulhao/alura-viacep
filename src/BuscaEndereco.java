import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscaEndereco { // Criei uma classe para buscar um endereço pelo CEP
    private String cep;
    private String jsonBuscaEndereco;

    public BuscaEndereco(String cep) { // Adicionei no próprio construtor a requisição para a API ViaCep
        this.cep = cep;
        String endpoint = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            jsonBuscaEndereco = response.body();

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getJsonBuscaEndereco() { // Getter do json da resposta, para ser usado no programa
        return jsonBuscaEndereco;
    }
}