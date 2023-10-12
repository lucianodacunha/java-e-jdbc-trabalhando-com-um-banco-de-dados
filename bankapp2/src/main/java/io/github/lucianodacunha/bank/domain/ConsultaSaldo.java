package io.github.lucianodacunha.bank.domain;

import io.github.lucianodacunha.bank.service.ContaService;

import java.util.Scanner;

public class ConsultaSaldo implements Command {
    public void execute() {
        Scanner teclado = new Scanner(System.in).useDelimiter("\n");
        ContaService service = new ContaService();
        System.out.println("Digite o número da conta:");
        var numeroDaConta = teclado.nextInt();
        var saldo = service.consultarSaldo(numeroDaConta);
        System.out.println("Saldo da conta: " + saldo);
    }
}
