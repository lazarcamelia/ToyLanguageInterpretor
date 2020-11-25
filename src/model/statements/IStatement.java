package model.statements;

import model.ProgramState;
import model.exceptions.ProgramStateExecutionException;

public interface IStatement {
        ProgramState execute(ProgramState state) throws ProgramStateExecutionException;
        String toString();
}
