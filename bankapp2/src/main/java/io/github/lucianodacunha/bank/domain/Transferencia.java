package io.github.lucianodacunha.bank.domain;

import io.github.lucianodacunha.bank.service.ContaService;

import java.util.Scanner;

public class Transferencia implements Command{
    public void execute() {
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        ContaService service = new ContaService();

        System.out.println("Digite o número da conta de origem:");
        var numeroDaContaOrigem = teclado.nextInt();

        System.out.println("Digite o número da conta de destino:");
        var numeroDaContaDestino = teclado.nextInt();

        System.out.println("Digite o valor a ser transferido:");
        var valor = teclado.nextBigDecimal();

        service.realizarSaque(numeroDaContaOrigem, valor);
        service.realizarDeposito(numeroDaContaDestino, valor);
        service.realizarTransferencia(
                numeroDaContaOrigem, numeroDaContaDestino, valor);
        System.out.println("Transferência realizado com sucesso!");

    }
}
