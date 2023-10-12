package io.github.lucianodacunha.bank.domain;

import io.github.lucianodacunha.bank.service.ContaService;

import java.util.Scanner;

public class RealizarDeposito implements Command{
    public void execute() {
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        ContaService service = new ContaService();

        System.out.println("Digite o número da conta:");
        var numeroDaConta = teclado.nextInt();

        System.out.println("Digite o valor do depósito:");
        var valor = teclado.nextBigDecimal();

        service.realizarDeposito(numeroDaConta, valor);

        System.out.println("Depósito realizado com sucesso!");
    }
}
