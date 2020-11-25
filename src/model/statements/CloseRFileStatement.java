package model.statements;

import model.ProgramState;
import model.adt.IHeap;
import model.adt.IMap;
import model.exceptions.CloseRFileException;
import model.exceptions.ProgramStateExecutionException;
import model.expressions.Expression;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements  IStatement{
    private Expression expression;

    public CloseRFileStatement(Expression expression) {
        this.expression = expression;
    }


    @Override
    public ProgramState execute(ProgramState state) throws ProgramStateExecutionException {
        IMap<String, IValue> symbolTable = state.getSymbolTable();
        IMap<StringValue, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, IValue> heapTable = state.getHeapTable();

        IValue expressionResult = expression.evaluate(symbolTable, heapTable);
        if (!(expressionResult.getType()).equals(new StringType())) {
            throw new CloseRFileException("The name of the file is not of type String!");
        }
        StringValue filename = (StringValue)expressionResult;
        if (!fileTable.isDefined(filename)) {
            throw new CloseRFileException("The file " + filename.getValue() + "was not opened already");
        }
        BufferedReader fileDescriptor = fileTable.lookUp(filename);
        try {
            fileDescriptor.close();
        } catch (IOException e) {
            throw new CloseRFileException("The file cannot be closed");
        }
        fileTable.delete(filename);
        return state;
    }
}
