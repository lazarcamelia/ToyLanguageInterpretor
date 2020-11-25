package model.statements;

import model.*;
import model.adt.IMap;
import model.exceptions.ProgramStateExecutionException;
import model.exceptions.StatementException;
import model.types.BoolType;
import model.types.IType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

public class VariableDeclarationStatement implements IStatement {
    String variableName;
    IType variableType;

    public VariableDeclarationStatement(String name, IType type) {
        variableName = name;
        variableType = type;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws ProgramStateExecutionException {
        IMap<String, IValue> symbolTable = programState.getSymbolTable();

        if (!symbolTable.isDefined(variableName)) {
              IValue value = variableType.defaultValue();
              symbolTable.add(variableName, value);
//            if (variableType.equals(new IntType())) {
//                symbolTable.add(variableName, (new IntType()).defaultValue());
//            }
//            else {
//                symbolTable.add(variableName, (new BoolType()).defaultValue());
//            }
        }
        else throw new StatementException("Variable " + variableName + " was declared before");

        return programState;
    }

    @Override
    public String toString() {
        return variableType.toString() + " " + variableName;
    }
}
