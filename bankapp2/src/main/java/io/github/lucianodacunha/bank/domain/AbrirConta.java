package io.github.lucianodacunha.bank.domain;

import io.github.lucianodacunha.bank.model.DadosAberturaConta;
import io.github.lucianodacunha.bank.model.DadosCadastroCliente;
import io.github.lucianodacunha.bank.service.ContaService;

import java.util.Scanner;

public class AbrirConta implements Command{
    public void execute() {
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        ContaService service = new ContaService();

        System.out.println("Digite o número da conta:");
        var numeroDaConta = teclado.nextInt();

        System.out.println("Digite o nome do cliente:");
        var nome = teclado.next();

        System.out.println("Digite o cpf do cliente:");
        var cpf = teclado.next();

        System.out.println("Digite o email do cliente:");
        var email = teclado.next();

        service.abrir(new DadosAberturaConta(numeroDaConta, new DadosCadastroCliente(nome, cpf, email)));

        System.out.println("Conta aberta com sucesso!");
    }
}
