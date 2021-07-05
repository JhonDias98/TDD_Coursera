package pilha.exceptions;

public class PilhaVaziaException extends RuntimeException {
    public PilhaVaziaException(String mensagem) {
        super(mensagem);
    }

}