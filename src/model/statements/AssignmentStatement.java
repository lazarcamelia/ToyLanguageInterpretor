package model.statements;

import model.ProgramState;
import model.adt.IHeap;
import model.types.IType;
import model.values.IValue;
import model.adt.IMap;
import model.adt.IStack;
import model.exceptions.ProgramStateExecutionException;
import model.expressions.Expression;
import model.exceptions.StatementException;

public class AssignmentStatement implements IStatement {
    private String id;
    private Expression expression;

    public AssignmentStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ProgramStateExecutionException{
        IStack<IStatement> programStack = state.getExecutionStack();
        IMap<String, IValue> symbolTable = state.getSymbolTable();
        IHeap<Integer, IValue> heapTable = state.getHeapTable();


        if (symbolTable.isDefined(id)) {
            IValue value = expression.evaluate(symbolTable, heapTable);
            IType typeOfTheCurrentValue = symbolTable.lookUp(id).getType();

            if (value.getType().equals(typeOfTheCurrentValue)) {
                symbolTable.update(id, value);
            }
            else
                throw new StatementException("Declared type of variable " + id + " and type of the " +
                        "assigned expression do not match!");
        }
        else {
            throw new StatementException("The variable " + id + " was not defined before");
        }

        return state;
    }

    public String toString() {
        return id + "=" + expression.toString();
    }

}
