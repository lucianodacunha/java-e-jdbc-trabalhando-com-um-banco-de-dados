package io.github.lucianodacunha.bank.domain;

public class CommandExecutor {
    public void executeCommand(Command command){
        command.execute();
    }
}
