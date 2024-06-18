package entidades;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    private DecimalFormat salarioFormatado = new DecimalFormat("###,###.00");

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getSalarioFormatado() {
        return salarioFormatado.format(this.getSalario());
    }

    public String getFuncao() {
        return funcao;
    }

    @Override
    public String toString() {
        return this.getNome() + "\t" +
                this.getDataNascimentoFormatada() + "\t\t" +
                this.getSalarioFormatado() + "\t\t" +
                this.getFuncao();
    }
}
