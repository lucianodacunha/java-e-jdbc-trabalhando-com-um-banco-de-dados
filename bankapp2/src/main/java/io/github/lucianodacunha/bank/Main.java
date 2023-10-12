package io.github.lucianodacunha.bank;

import io.github.lucianodacunha.bank.domain.*;
import io.github.lucianodacunha.bank.exception.RegraDeNegocioException;
import io.github.lucianodacunha.bank.domain.CommandExecutor;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    private Scanner teclado = new Scanner(System.in).useDelimiter("\n");

    public void showMenu() {
        Locale.setDefault(Locale.US);
        CommandExecutor command = new CommandExecutor();

        var opcao = exibirMenu();
        while (opcao != 8) {
            try {
                switch (opcao) {
                    case 1 -> command.executeCommand(new ListarContas());
                    case 2 -> command.executeCommand(new AbrirConta());
                    case 3 -> command.executeCommand(new EncerrarConta());
                    case 4 -> command.executeCommand(new ConsultaSaldo());
                    case 5 -> command.executeCommand(new RealizarSaque());
                    case 6 -> command.executeCommand(new RealizarDeposito());
                    case 7 -> command.executeCommand(new Transferencia());
                    default -> System.out.println("Opção inválida.");
                }
            } catch (RegraDeNegocioException e) {
                System.out.println("Erro: " +e.getMessage());
                System.out.println("Pressione qualquer tecla e de ENTER para " +
                    "voltar ao menu");
                teclado.next();
            }
            System.out.println(
                    "Pressione qualquer tecla e de ENTER para voltar ao " +
                            "menu principal");
            teclado.next();
            opcao = exibirMenu();
        }

        System.out.println("Finalizando a aplicação.");
    }

    private int exibirMenu() {
        System.out.println("""
                BANK APP - ESCOLHA UMA OPÇÃO:
                1 - Listar Contas
                2 - Abrir Conta
                3 - Encerramentar Conta
                4 - Saldo
                5 - Saque
                6 - Depósito
                7 - Transferência
                8 - Sair
                """);
        return teclado.nextInt();
    }
}
