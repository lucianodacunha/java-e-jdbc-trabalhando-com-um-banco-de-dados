package io.github.lucianodacunha.bank.dao;

import io.github.lucianodacunha.bank.exception.RegraDeNegocioException;
import io.github.lucianodacunha.bank.model.Cliente;
import io.github.lucianodacunha.bank.model.Conta;
import io.github.lucianodacunha.bank.model.DadosAberturaConta;
import io.github.lucianodacunha.bank.model.DadosCadastroCliente;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ContaDAO {

    private Connection conn;

    public ContaDAO(Connection connection) {
        this.conn = connection;
    }

    public void salvar(DadosAberturaConta dadosDaConta) {
        var cliente = new Cliente(dadosDaConta.dadosCliente());
        var conta = new Conta(dadosDaConta.numero(), cliente, BigDecimal.ZERO);
        PreparedStatement ps = null;

        String sql = "INSERT INTO conta (" +
                "numero, saldo, cliente_nome, cliente_cpf, cliente_email)" +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, conta.getNumero());
            ps.setBigDecimal(2, BigDecimal.ZERO);
            ps.setString(3, dadosDaConta.dadosCliente().nome());
            ps.setString(4, dadosDaConta.dadosCliente().cpf());
            ps.setString(5, dadosDaConta.dadosCliente().email());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Set<Conta> listar(){
        Set contas = new HashSet();
        String sql = "SELECT * FROM conta WHERE ativo = true";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Integer numero = rs.getInt(1);
                BigDecimal saldo = rs.getBigDecimal(2);
                String nome = rs.getString(3);
                String cpf = rs.getString(4);
                String email = rs.getString(5);

                DadosCadastroCliente dadosCadastroCliente =
                        new DadosCadastroCliente(nome, cpf, email);
                Cliente cliente = new Cliente(dadosCadastroCliente);

                contas.add(new Conta(numero, cliente, saldo));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert ps != null;
                ps.close();
                assert rs != null;
                rs.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return contas;
    }

    public Conta buscarPorNumeroDaConta(Integer numeroProcurado) {
        Conta conta = null;
        String sql = "SELECT * FROM conta WHERE numero = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, numeroProcurado);
            rs = ps.executeQuery();

            if (rs.next()) {
                Integer numero = rs.getInt(1);
                BigDecimal saldo = rs.getBigDecimal(2);
                String nome = rs.getString(3);
                String cpf = rs.getString(4);
                String email = rs.getString(5);

                DadosCadastroCliente dadosCadastroCliente =
                        new DadosCadastroCliente(nome, cpf, email);
                Cliente cliente = new Cliente(dadosCadastroCliente);
                conta = new Conta(numero, cliente, saldo);
            } else {
                throw new RegraDeNegocioException.ContaNaoEncontradaException("Nenhuma conta cadastrada com esse número");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RegraDeNegocioException.ContaNaoEncontradaException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                assert ps != null;
                ps.close();
                assert rs != null;
                rs.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return conta;
    }

    public void alterar(Conta conta) {
        PreparedStatement ps = null;
        String sql = "UPDATE conta SET saldo = ? WHERE numero = ?";
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, conta.getSaldo());
            ps.setInt(2, conta.getNumero());
            ps.execute();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removerLogico(Conta conta) {
        PreparedStatement ps = null;
        String sql = "UPDATE conta SET ativo = false WHERE numero = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, conta.getNumero());
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}