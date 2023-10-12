package io.github.lucianodacunha.bank.domain;

import io.github.lucianodacunha.bank.service.ContaService;

import java.util.Scanner;

public class RealizarSaque implements Command {
    public void execute() {
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        ContaService service = new ContaService();

        System.out.println("Digite o número da conta:");
        var numeroDaConta = teclado.nextInt();

        System.out.println("Digite o valor do saque:");
        var valor = teclado.nextBigDecimal();

        service.realizarSaque(numeroDaConta, valor);
        System.out.println("Saque realizado com sucesso!");
    }
}
