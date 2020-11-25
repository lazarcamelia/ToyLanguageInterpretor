package model.statements;

import model.ProgramState;
import model.adt.IHeap;
import model.adt.IMap;
import model.exceptions.InappropriateFilenameException;
import model.exceptions.ProgramStateExecutionException;
import model.expressions.Expression;
import model.types.IType;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStastement implements IStatement {
    private Expression expression;

    public OpenRFileStastement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ProgramStateExecutionException {
        IMap<String, IValue> symbolTable = state.getSymbolTable();
        IMap<StringValue, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, IValue> heapTable = state.getHeapTable();


        IValue evalResult = expression.evaluate(symbolTable, heapTable);
        IType resultType = evalResult.getType();
        if (resultType.equals(new StringType())) {
            StringValue filename = (StringValue)evalResult;
            if (!fileTable.isDefined(filename)) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(filename.getValue()));
                    fileTable.add(filename, reader);
                } catch (FileNotFoundException e) {
                    throw new InappropriateFilenameException("The file cannot be opened!");
                }
            }
            else
                throw new InappropriateFilenameException("The file is already oped!");

        }
        else
            throw new InappropriateFilenameException("The type of the expression is not StringType");
        return state;
    }
}
