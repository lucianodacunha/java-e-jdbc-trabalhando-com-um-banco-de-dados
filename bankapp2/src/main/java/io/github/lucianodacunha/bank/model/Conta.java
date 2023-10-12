package io.github.lucianodacunha.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Conta {

    private Integer numero;
    private BigDecimal saldo;
    private Cliente titular;

    public Conta(Integer numero, Cliente titular, BigDecimal saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }


    public boolean possuiSaldo() {
        return this.saldo.compareTo(BigDecimal.ZERO) != 0;
    }

    public void sacar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }

    public void depositar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return numero.equals(conta.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return  "%05d %-20s %-11s %7.2f"
            .formatted(this.getNumero(), this.getTitular().getNome(),
            this.titular.getCpf(), this.getSaldo());
    }

    public Integer getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return titular;
    }
}
