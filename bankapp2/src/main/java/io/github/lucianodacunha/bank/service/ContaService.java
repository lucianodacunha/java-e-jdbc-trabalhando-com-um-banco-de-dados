package io.github.lucianodacunha.bank.service;

import io.github.lucianodacunha.bank.dao.ConnectionFactory;
import io.github.lucianodacunha.bank.dao.ContaDAO;
import io.github.lucianodacunha.bank.exception.RegraDeNegocioException;
import io.github.lucianodacunha.bank.model.Conta;
import io.github.lucianodacunha.bank.model.DadosAberturaConta;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

public class ContaService {

    private final ConnectionFactory connection;

    public ContaService() {
        this.connection = new ConnectionFactory();
    }

    public Set<Conta> listarContasAbertas() {
        Connection conn = connection.recuperarConexao();
        return new ContaDAO(conn).listar();
    }

    public Conta buscarContaPorNumero(Integer numero) {
        Connection conn = null;
        Conta conta = null;

        try {
            conn = connection.recuperarConexao();
            conta = new ContaDAO(conn).buscarPorNumeroDaConta(numero);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return conta;
    }


    public BigDecimal consultarSaldo(Integer numeroDaConta) {
        Connection conn = connection.recuperarConexao();
        var conta =  new ContaDAO(conn).buscarPorNumeroDaConta(numeroDaConta);
        return conta.getSaldo();
    }

    public void abrir(DadosAberturaConta dadosDaConta) {

        Connection conn = connection.recuperarConexao();
        new ContaDAO(conn).salvar(dadosDaConta);

    }

    public void realizarSaque(Integer numeroDaConta, BigDecimal valor) {
        var conta =  this.buscarContaPorNumero(numeroDaConta);

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do saque deve ser " +
                    "superior a zero!");
        }

        if (valor.compareTo(conta.getSaldo()) > 0) {
            throw new RegraDeNegocioException("Saldo insuficiente!");
        }

        conta.sacar(valor);
        Connection conn = connection.recuperarConexao();
        new ContaDAO(conn).alterar(conta);
    }

    public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) {
        var conta =  this.buscarContaPorNumero(numeroDaConta);

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do deposito deve ser " +
                    "superior a zero!");
        }

        conta.depositar(valor);
        Connection conn = connection.recuperarConexao();
        new ContaDAO(conn).alterar(conta);
    }

    public void encerrar(Integer numeroDaConta) {
        Connection conn = connection.recuperarConexao();
        var conta =  new ContaDAO(conn).buscarPorNumeroDaConta(numeroDaConta);

        if (conta.possuiSaldo()) {
            System.out.println("Conta possui saldo, deseja sacar? [s/n] ");
            Scanner teclado = new Scanner(System.in).useDelimiter("\n");
            var resp = teclado.nextLine();

            if (resp.equalsIgnoreCase("s")){
                conn = connection.recuperarConexao();
                new ContaDAO(conn).removerLogico(conta);
            }
        }
    }

    public void realizarTransferencia(
            int numeroDaContaOrigem,
            int numeroDaContaDestino, BigDecimal valor) {
        realizarSaque(numeroDaContaOrigem, valor);
        realizarDeposito(numeroDaContaDestino, valor);
    }
}
