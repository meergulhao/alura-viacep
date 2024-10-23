import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws RuntimeException {
        Scanner scanner = new Scanner(System.in); // Scanner pra leitura
        String menu = """
                MENU:
                1. Buscar um endereço pelo CEP
                2. Buscar um CEP pelo endereço
                3. Gerar resultados e sair
                
                Digite uma opção:
                """; // Menu bonitinho
        Integer opcaoMenu = 0;

        // Lista de endereços para adicionar os buscados
        List<Endereco> listaEnderecos = new ArrayList<>();

        Gson gson = new GsonBuilder() // Gson para ler/escrever json
                .setPrettyPrinting()
                .create();

        // Início
        System.out.println("\n==BUSCA DE ENDEREÇOS==");


        while (opcaoMenu != 3) {
            try { // Trata exception
                System.out.println("\n******************************\n");
                System.out.println(menu);
                opcaoMenu = parseInt(scanner.nextLine());

                switch (opcaoMenu) {
                    case 1: // Busca um endereço pelo CEP, retorna o endereço completo
                        System.out.println("Digite o CEP que deseja buscar:");

                        // Busca o CEP digitado
                        BuscaEndereco buscaEndereco = new BuscaEndereco(scanner.nextLine());

                        // Cria objeto Endereço com base no JSON da BuscaEndereco
                        Endereco enderecoPorCep = gson.fromJson(buscaEndereco.getJsonBuscaEndereco(), Endereco.class);
                        listaEnderecos.add(enderecoPorCep); // Adiciona na lista
                        System.out.println("Resultado:\n" + enderecoPorCep);
                        break;
                    case 2: // Busca um CEP pelo endereço, retorna o endereço completo

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
                        listaEnderecos.add(cepPorEndereco[0]); // Adiciona na lista
                        System.out.println("Resultado:\n" + cepPorEndereco[0]);
                        break;
                    case 3: // Encerra
                        System.out.println("Finalizado");
                        break;
                    default:
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