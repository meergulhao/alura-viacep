package br.com.alura.viacep.principal;

import br.com.alura.viacep.buscas.BuscaCep;
import br.com.alura.viacep.buscas.BuscaEndereco;
import br.com.alura.viacep.modulos.Endereco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Principal {
    public static void main(String[] args) throws RuntimeException, IOException {
        Scanner scanner = new Scanner(System.in); // Scanner pra leitura
        String menu = """
                MENU:
                1. Buscar um endereço pelo CEP
                2. Buscar um CEP pelo endereço
                3. Gerar arquivo e sair
                
                Digite uma opção:
                """; // Menu bonitinho
        Integer opcaoMenu = 0;

        // Lista de endereços para adicionar os buscados
        List<Endereco> listaEnderecos = new ArrayList<>();

        // Gson para ler/escrever json
        Gson gson = new GsonBuilder() // Na aula, o Gson foi adicionado e usado na classe da busca
                .setPrettyPrinting()
                .create();

        // Início
        System.out.println("\n==BUSCA DE ENDEREÇOS==");
        System.out.println("""
                Este programa busca endereços e gera um arquivo JSON.
                Busque pelo CEP e receba um endereço completo.
                Busque por partes do endereço e receba um endereço completo.
                Busque quantos endereços forem necessários, e ao sair,
                um arquivo JSON será gerado com todos os endereços buscados.
                """);

        while (opcaoMenu != 3) {
            try { // Trata exception
                System.out.println("******************************\n");
                System.out.println(menu);
                opcaoMenu = parseInt(scanner.nextLine());

                switch (opcaoMenu) {

                    // Busca um endereço pelo CEP, retorna o endereço completo
                    case 1:
                        System.out.println("Digite o CEP que deseja buscar:");

                        // Busca o CEP digitado
                        BuscaEndereco buscaEndereco = new BuscaEndereco(scanner.nextLine());

                        // Cria objeto Endereço com base no JSON da BuscaEndereco
                        Endereco enderecoPorCep = gson.fromJson(buscaEndereco.getJsonBuscaEndereco(), Endereco.class);
                        // Adiciona na lista
                        listaEnderecos.add(enderecoPorCep);
                        System.out.println("Resultado:\n" + enderecoPorCep);
                        break;

                    // Busca um CEP pelo endereço, retorna o endereço completo
                    case 2: // Na aula não usaram o metodo do ViaCep de buscar pelo endereço, então pontos pra mim (=

                        // Variáveis para a busca por endereço
                        System.out.println("Digite a UF:");
                        String uf = scanner.nextLine();
                        System.out.println("Digite a cidade:");
                        String cidade = scanner.nextLine();
                        System.out.println("Digite o logradouro:");
                        String logradouro = scanner.nextLine();

                        // Busca o endereço digitado
                        BuscaCep buscaCep = new BuscaCep(uf, cidade, logradouro);

                        // Cria objeto Endereço com base no JSON da BuscaCep
                        Endereco[] cepPorEndereco = gson.fromJson(buscaCep.getJsonBuscaCep(), Endereco[].class);
                        // Adiciona na lista
                        listaEnderecos.add(cepPorEndereco[0]);
                        System.out.println("Resultado:\n" + cepPorEndereco[0]);
                        break;

                    // Gera o arquivo json e encerra
                    case 3:
                        if (listaEnderecos.isEmpty()) { // Não gera o arquivo se a lista estiver vazia
                            System.out.println("Programa finalizado.");
                        }
                        else { // Na aula, o gerador de arquivo foi criado também em uma classe
                            FileWriter gerarJson = new FileWriter("enderecos.json");
                            gerarJson.write(gson.toJson(listaEnderecos));
                            gerarJson.close();
                            System.out.println("Arquivo gerado! Programa finalizado.");
                        }
                        break;
                    default: // Caso seja inserida uma opção além das possíveis
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (IllegalArgumentException e) { // Trata exception
                {
                    System.out.println("Opção inválida!");
                }
            }
        }
    }
}