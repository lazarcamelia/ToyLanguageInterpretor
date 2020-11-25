package model.statements;

import model.ProgramState;
import model.adt.IHeap;
import model.values.IValue;
import model.adt.IList;
import model.adt.IMap;
import model.exceptions.ProgramStateExecutionException;
import model.expressions.Expression;

public class PrintStatement implements IStatement {
    private  Expression expression;

    public PrintStatement(Expression exp) {
        this.expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ProgramStateExecutionException{
        IList<IValue> outputList = state.getOutputList();
        IMap<String, IValue> symbolTable = state.getSymbolTable();
        IHeap<Integer, IValue> heapTable = state.getHeapTable();


        outputList.add(expression.evaluate(symbolTable, heapTable));

        return state;
    }

    @Override
    public String toString() {
        return ("print(" + expression.toString() + ")");
    }

}
