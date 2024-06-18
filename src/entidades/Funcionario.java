package entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    @Override
    public String toString() {
        return this.getNome() + "\t" +
                this.getDataNascimento() + "\t\t" +
                this.getSalario() + "\t\t" +
                this.funcao;
    }
}
