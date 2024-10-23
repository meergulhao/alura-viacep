package br.com.alura.viacep.modulos;

public class Endereco { // Na aula foi criado como Record, faz sentido
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public Endereco(String cep, String logradouro, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    @Override
    public String toString() {
        return "CEP: " + cep + "\nLogradouro: " + logradouro + "\nBairro: " + bairro +
                "\nCidade: " + localidade + "\nEstado: " + uf;
    }
}
