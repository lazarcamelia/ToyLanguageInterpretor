package model.statements;

import model.adt.IHeap;
import model.types.BoolType;
import model.values.BoolValue;
import model.ProgramState;
import model.values.IValue;
import model.adt.IMap;
import model.adt.IStack;
import model.exceptions.ProgramStateExecutionException;
import model.expressions.Expression;
import model.exceptions.StatementException;


public class IfStatement implements IStatement {
    Expression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public String toString() {
        return "(IF(" + expression.toString() + ") THEN(" + thenStatement.toString()
                + ")ELSE(" + elseStatement.toString() + "))";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws ProgramStateExecutionException {
        IMap<String, IValue> symbolTable = programState.getSymbolTable();
        IStack<IStatement> executionStack = programState.getExecutionStack();
        IHeap<Integer, IValue> heapTable = programState.getHeapTable();


        IValue valueExpression = expression.evaluate(symbolTable, heapTable);

        if (valueExpression.getType().equals(new BoolType())) {
            BoolValue conditionValue = (BoolValue)valueExpression;

            if (conditionValue.getValue()) {
                executionStack.push(thenStatement);
            }
            else {
                executionStack.push(elseStatement);
            }
        }
        else throw new StatementException("The condition is not a boolean value!");

        return programState;
    }
}
