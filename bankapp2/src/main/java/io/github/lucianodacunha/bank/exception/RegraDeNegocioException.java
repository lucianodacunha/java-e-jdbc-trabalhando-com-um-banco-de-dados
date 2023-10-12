package io.github.lucianodacunha.bank.exception;

public class RegraDeNegocioException extends RuntimeException {
    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }

    public static class ContaNaoEncontradaException extends RuntimeException {
        public ContaNaoEncontradaException(String s) {
            super(s);
        }
    }
}
