package model.statements;

import model.ProgramState;
import model.adt.IHeap;
import model.adt.IMap;
import model.adt.IStack;
import model.exceptions.ProgramStateExecutionException;
import model.exceptions.StatementException;
import model.expressions.Expression;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

public class WhileStatement implements IStatement {
    private Expression expression;

    public WhileStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ProgramStateExecutionException {
        IStack<IStatement> executionStack = state.getExecutionStack();
        IMap<String, IValue> symbolTable = state.getSymbolTable();
        IHeap<Integer, IValue> heapTable = state.getHeapTable();

        IValue valueExpression = this.expression.evaluate(symbolTable, heapTable);

        if (valueExpression.getType().equals(new BoolType())) {
            BoolValue valueOfExpression = (BoolValue)valueExpression;

            if (!(valueOfExpression).getValue()) {
                executionStack.pop();
            }
            else {
                IStatement nextStatement = executionStack.pop();
                executionStack.push(nextStatement);
                executionStack.push(this);
                executionStack.push(nextStatement);
            }
        }
        else
            throw new StatementException("The type of the expression is not bool!");

        return state;
    }

    @Override
    public String toString() {
        return "while (" + this.expression.toString() + ")";
    }
}
