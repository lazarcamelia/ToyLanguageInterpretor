package model.statements;

import model.ProgramState;
import model.adt.IStack;
import model.exceptions.ProgramStateExecutionException;

public class CompoundStatement implements IStatement {
    IStatement firstStatement;
    IStatement secondStatement;

    public CompoundStatement(IStatement left, IStatement right) {
        firstStatement = left;
        secondStatement = right;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ProgramStateExecutionException {
        IStack<IStatement> currentStack = state.getExecutionStack();
        currentStack.push(secondStatement);
        currentStack.push(firstStatement);

        return state;
    }

    public String toString() {
        return ("(" + firstStatement.toString() + ";" + secondStatement.toString() + ")");
    }
}
