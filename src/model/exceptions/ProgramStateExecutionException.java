package model.exceptions;

public class ProgramStateExecutionException extends RuntimeException {
    public ProgramStateExecutionException(final String message) {
        super(message);
    }
}
