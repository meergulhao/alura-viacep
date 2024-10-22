import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner pra leitura
        String menu = """
                Digite uma opção:
                1. Buscar um endereço pelo CEP
                2. Buscar um CEP pelo endereço
                0. Gerar resultados e sair
                """; // Menu bonitinho
        Integer opcaoMenu;

        Gson gson = new GsonBuilder() // Gson para ler/escrever json
                .setPrettyPrinting()
                .create();

        // Início
        System.out.println("==BUSCA DE ENDEREÇOS==");
        System.out.println("\n******************************\n");
        System.out.println(menu);

        // Busca o CEP digitado
        BuscaEndereco buscaEndereco = new BuscaEndereco(scanner.nextLine());

        // Cria objeto Endereço com base no JSON da BuscaEndereco
        Endereco endereco1 = gson.fromJson(buscaEndereco.getJsonBuscaEndereco(), Endereco.class);
        System.out.println(endereco1);

        System.out.println("Digite a UF: ");
        String uf = scanner.nextLine();
        System.out.println("Digite a cidade: ");
        String cidade = scanner.nextLine();
        System.out.println("Digite o logradouro: ");
        String logradouro = scanner.nextLine();

        BuscaCep buscaCep = new BuscaCep(uf, cidade, logradouro);

        Endereco endereco2 = gson.fromJson(buscaEndereco.getJsonBuscaEndereco(), Endereco.class);
        System.out.println(endereco2);
    }
}