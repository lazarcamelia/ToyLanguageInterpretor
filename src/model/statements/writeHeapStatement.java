package model.statements;

import model.ProgramState;
import model.adt.IHeap;
import model.adt.IMap;
import model.exceptions.ProgramStateExecutionException;
import model.exceptions.StatementException;
import model.expressions.Expression;
import model.types.IType;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;

public class writeHeapStatement implements IStatement {
    private String variableName;
    private Expression expression;

    public writeHeapStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ProgramStateExecutionException {
        IMap<String, IValue> symbolTable = state.getSymbolTable();
        IHeap<Integer, IValue> heapTable = state.getHeapTable();

        if (symbolTable.isDefined(variableName)) {
            IType typeOfVar = symbolTable.lookUp(variableName).getType();
            if (typeOfVar.toString().contains("Ref")) {
                RefValue valueOfVar = (RefValue)symbolTable.lookUp(variableName);
                int address = valueOfVar.getAddress();
                if (heapTable.isDefined(address)) {
                    IValue valueOfExpression = this.expression.evaluate(symbolTable, heapTable);
                    RefType refTypeOfVariableName = (RefType)typeOfVar;
                    IType innerTypeOfTheVariableName = refTypeOfVariableName.getInnerType();

                    if (valueOfExpression.getType().equals(innerTypeOfTheVariableName)) {
                        heapTable.update(address, valueOfExpression);
                    }
                    else
                        throw new StatementException("The type of the variable and the type of the expression do not match");
                }
                else
                    throw new StatementException("The address doesn't exist in the heap");
            }
            else
                throw new StatementException("The variable " + variableName + " is not of type reference");
        }
        else
            throw new StatementException("The variable " + variableName + " is not defined");

        return state;
    }

    @Override
    public String toString() {
        return "writeHeap(" + this.variableName + "," + this.expression.toString() + ")";
    }
}
