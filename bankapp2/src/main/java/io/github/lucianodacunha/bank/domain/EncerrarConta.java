package io.github.lucianodacunha.bank.domain;

import io.github.lucianodacunha.bank.service.ContaService;

import java.util.Scanner;

public class EncerrarConta implements Command{
    public void execute() {
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        ContaService service = new ContaService();

        System.out.println("Digite o número da conta:");
        var numeroDaConta = teclado.nextInt();

        service.encerrar(numeroDaConta);

        System.out.println("Conta encerrada com sucesso!");
    }
}
