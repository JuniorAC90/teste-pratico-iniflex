package entidades;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Funcionario extends Pessoa implements Comparable <Pessoa> {
    private BigDecimal salario;
    private String funcao;

    private DecimalFormat valorFormatado = new DecimalFormat("###,###.00");

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getValorFormatado() {
        return valorFormatado.format(this.getSalario());
    }

    public String getFuncao() {
        return funcao;
    }

    public void aumentarDezPorCentoDoSalario() {
        BigDecimal dezPorCentoDoSalario = this.getSalario().multiply(new BigDecimal("0.10"));
        this.salario = this.getSalario().add(dezPorCentoDoSalario);
    }

    public String quantidadeDeSalariosMinimos() {
        Double quantidade = this.getSalario()
                            .divide(new BigDecimal("1212.00"), 2, RoundingMode.HALF_UP).doubleValue();
        return valorFormatado.format(quantidade);
    }

    @Override
    public String toString() {
        return this.getNome() + "\t" +
                this.getDataNascimentoFormatada() + "\t\t" +
                this.getValorFormatado() + "\t\t" +
                this.getFuncao();
    }

    @Override
    public int compareTo(Pessoa pessoa) {
        return this.getNome().compareTo(pessoa.getNome());
    }
}
