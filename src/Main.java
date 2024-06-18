import entidades.Funcionario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

        // 3.5 Agrupando os funcionários por função
        Map<String, List<Funcionario>> funcionariosAgrupados = agruparFuncionariosPorFuncao(listaDeFuncionarios);

        // 3.6 Imprimindo os funcionários agrupados por função
        listarFuncionariosAgrupadosPorFuncao(funcionariosAgrupados);

        // 3.8 Imprimindo os funcionários que fazem aniversário no mês 10 e 12
        listarFuncionariosAniversariantesMes10e12(listaDeFuncionarios);

        // 3.9 Imprimindo o funcionário com a maior idade
        imprimirFuncionarioComMaiorIdade(listaDeFuncionarios);

        // 3.10 Imprimindo a lista em ordem alfabética
        Collections.sort(listaDeFuncionarios);
        System.out.println("Todos os funcionários em ordem alfabética: ");
        listarFuncionarios(listaDeFuncionarios);

        // 3.11 Imprimir o total de salários
        imprimirTotalDeSalarios(listaDeFuncionarios);

        // 3.12 Imprimir quantos salários mínimos ganha cada funcionário
        imprimirTotalDeSalariosMinimoPorFuncionario(listaDeFuncionarios);
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
            f.aumentarDezPorCentoDoSalario();
        }
    }

    private static Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> lista) {
        Map<String, List<Funcionario>> mapFuncionarios = new HashMap<>();
        for (Funcionario f : lista) {
           if (!mapFuncionarios.containsKey(f.getFuncao())) {
               mapFuncionarios.put(f.getFuncao(), new ArrayList<>());
               mapFuncionarios.get(f.getFuncao()).add(f);
           } else {
               mapFuncionarios.get(f.getFuncao()).add(f);
           }
        }
        return mapFuncionarios;
    }

    private static void listarFuncionariosAgrupadosPorFuncao(Map<String, List<Funcionario>> mapFuncionarios) {
        Set<String> chaves = mapFuncionarios.keySet();
        for (String chave : chaves) {
            System.out.println(chave);
            System.out.println("Funcionários: ");
            System.out.println("Nome\tData Nascimento\tSalário\t\t\tFunção");
            for (Funcionario f : mapFuncionarios.get(chave)) {
                System.out.println(f);
            }
            System.out.println();
        }
    }

    private static void listarFuncionariosAniversariantesMes10e12(List<Funcionario> lista) {
        System.out.println("Lista de funcionários que fazem aniversário no mês 10 e 12.");
        System.out.println("Nome\tData Nascimento\tSalário\t\t\tFunção");
        for (Funcionario f : lista) {
            if (f.getDataNascimento().getMonth().equals(Month.OCTOBER)
                    || f.getDataNascimento().getMonth().equals(Month.DECEMBER))
                System.out.println(f);
        }
        System.out.println("---------------------------------------------------------");
        System.out.println();
    }

    private static void imprimirFuncionarioComMaiorIdade(List<Funcionario> lista) {
        System.out.println("Funcionário com Maior idade: ");
        System.out.println("Nome\tIdade");
        Integer maiorIdade = null;
        Funcionario funcionariocomMaiorIdade = null;
        for (Funcionario f : lista) {
            if (maiorIdade == null) {
                maiorIdade = f.getIdade();
                funcionariocomMaiorIdade = f;
            } else {
                if (f.getIdade() > maiorIdade) {
                    maiorIdade = f.getIdade();
                    funcionariocomMaiorIdade = f;
                }
            }
        }

        if (funcionariocomMaiorIdade != null) {
            System.out.println(funcionariocomMaiorIdade.getNome() + "\t" + funcionariocomMaiorIdade.getIdade());
        }

        System.out.println("---------------------------------------------------------");
        System.out.println();
    }

    private static void imprimirTotalDeSalarios(List<Funcionario> lista) {
        BigDecimal totalDeSalarios = lista.stream()
                                        .map(Funcionario::getSalario)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total de Salários: ");
        DecimalFormat salarioFormatado = new DecimalFormat("###,###.00");
        String totalDeSalariosFormatado = salarioFormatado.format(totalDeSalarios);
        System.out.println(totalDeSalariosFormatado);
        System.out.println("---------------------------------------------------------");
        System.out.println();
    }

    private static void imprimirTotalDeSalariosMinimoPorFuncionario(List<Funcionario> lista) {
        System.out.println("Quantidade de salários mínimos por funcionário: ");
        System.out.println("Nome\tQuantidade de salários mínimos");

        for (Funcionario f : lista) {
            System.out.println(f.getNome() + "\t" + f.quantidadeDeSalariosMinimos());
        }

        System.out.println("---------------------------------------------------------");
        System.out.println();
    }

}