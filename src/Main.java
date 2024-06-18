import entidades.Funcionario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 3.1 Inserir todos os funcionários na mesma ordem da tabela
        List<Funcionario> listaDeFuncionarios = new ArrayList<>();
        String caminhoDaPastaDoProjeto = System.getProperty("user.dir");
        String caminhoDoArquivo = caminhoDaPastaDoProjeto + "/src/anexo/tabela-funcionarios.csv";
        Scanner sc = new Scanner(System.in);
        System.out.println("O Sistema Operacional é Windows? S/N");
        String opcao = sc.next();
        if (opcao.toLowerCase().equals("s")) {
            caminhoDoArquivo = caminhoDoArquivo.replace("/", "\\");
        }
        sc.close();
        DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoArquivo))) {
            br.readLine();
            String umaLinha = br.readLine();
            while (umaLinha != null) {
                String[] linha = umaLinha.split(",");
                String nome = linha[0];
                LocalDate dataNascimento = LocalDate.parse(linha[1], formatadorDeData);
                BigDecimal salario = new BigDecimal(linha[2]);
                String funcao = linha[3];
                Funcionario funcionario = new Funcionario(nome, dataNascimento, salario, funcao);
                listaDeFuncionarios.add(funcionario);
                umaLinha = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Todos os funcionários: ");
        listarFuncionarios(listaDeFuncionarios);

        // 3.2 Remover o funcionário João
        listaDeFuncionarios.removeIf(f -> f.getNome().equals("João"));
        System.out.println("Funcionário João Removido: ");
        listarFuncionarios(listaDeFuncionarios);

        // 3.3 Imprimindo todos os funcionários com as datas e salários formatados
        System.out.println("Todos os funcionários com os dados formatados: ");
        listarFuncionarios(listaDeFuncionarios);

        // 3.4 Aumento de 10% do salário
        aumentarDezPorCentoDaListaDeFuncionarios(listaDeFuncionarios);
        System.out.println("Lista de funcionários atualizada com o aumento salarial.");
        listarFuncionarios(listaDeFuncionarios);
    }

    private static void listarFuncionarios(List<Funcionario> lista) {
        System.out.println("Nome\tData Nascimento\tSalário\t\t\tFunção");
        for (Funcionario f : lista) {
            System.out.println(f);
        }
        System.out.println("---------------------------------------------------------");
        System.out.println();
    }

    private static void aumentarDezPorCentoDaListaDeFuncionarios(List<Funcionario> lista) {
        for (Funcionario f : lista) {
            f.aumentarDezPorCentoDoSalário();
        }
    }

}