package model.statements;

import model.ProgramState;
import model.exceptions.ProgramStateExecutionException;

public class NopStatement implements IStatement {

    public NopStatement() {

    }

    @Override
    public ProgramState execute(ProgramState programState) throws ProgramStateExecutionException{
        return programState;
    }

    @Override
    public String toString() {
        return "nop";
    }
}
