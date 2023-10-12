package io.github.lucianodacunha.bank.domain;

import io.github.lucianodacunha.bank.service.ContaService;

public class ListarContas implements Command{
    @Override
    public void execute() {
        ContaService service = new ContaService();

        System.out.println("Contas cadastradas:");
        var contas = service.listarContasAbertas();
        System.out.println(
                "%-5s %-20s %-11s %s".formatted("NRO", "TITULAR", "CPF", "SALDO"));
        contas.stream().forEach(System.out::println);
    }
}
