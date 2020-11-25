package model.statements;

import model.ProgramState;
import model.adt.IHeap;
import model.adt.IMap;
import model.exceptions.ProgramStateExecutionException;
import model.exceptions.ReadFileException;
import model.expressions.Expression;
import model.types.IntType;
import model.types.StringType;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement {
    private Expression expression;
    private String variableName;

    public ReadFileStatement(Expression expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ProgramStateExecutionException {
        IMap<String, IValue> symbolTable = state.getSymbolTable();
        IMap<StringValue, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, IValue> heapTable = state.getHeapTable();


        if (symbolTable.isDefined(variableName)) {
            IValue valueOfVariable = symbolTable.lookUp(variableName);

            if ((valueOfVariable.getType()).equals(new IntType())) {
                IValue expressionResult = this.expression.evaluate(symbolTable, heapTable);

                if ((expressionResult.getType()).equals(new StringType())) {
                    StringValue fileKey = (StringValue)expressionResult;
                    if (fileTable.isDefined(fileKey)) {
                        BufferedReader bufferedReader = fileTable.lookUp(fileKey);

                        String readLine = null;
                        try {
                            readLine = bufferedReader.readLine();
                        } catch (IOException e) {
                            throw new ReadFileException("Invalid value in file!");
                        }
                        IntValue valueOnLine = null;

                        if (readLine == null)
                            valueOnLine = new IntValue(0);
                        else {
                            int value = Integer.parseInt(readLine);
                            valueOnLine = new IntValue(value);
                        }

                        symbolTable.update(variableName, valueOnLine);

                        return state;
                    }
                    throw new ReadFileException("The file was not already opened!");
                }
                else
                    throw new ReadFileException("The result of the expression is not of type string");
            }
            else
                throw new ReadFileException("The variable is not of type int");
        }
        else
            throw new ReadFileException("The file was not opened already!");

    }
}
