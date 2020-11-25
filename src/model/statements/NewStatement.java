package model.statements;

import model.ProgramState;
import model.adt.IHeap;
import model.adt.IMap;
import model.exceptions.ProgramStateExecutionException;
import model.exceptions.ReferenceException;
import model.exceptions.StatementException;
import model.expressions.Expression;
import model.types.IType;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;

public class NewStatement implements IStatement {
    private String variableName;
    private Expression expression;

    public NewStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ProgramStateExecutionException {
        IMap<String, IValue> symbolTable = state.getSymbolTable();
        IHeap<Integer, IValue> heap = state.getHeapTable();

        if (symbolTable.isDefined(variableName)) {
            IType varType = symbolTable.lookUp(variableName).getType();

            if (varType.toString().contains("Ref")) {
                IValue expressionResult = expression.evaluate(symbolTable, heap);
                RefType refVar = (RefType)varType;

                if (expressionResult.getType().equals(refVar.getInnerType())) {
                    heap.add(expressionResult);

                    RefValue refValue = new RefValue(heap.getAddress(expressionResult), expressionResult.getType());
                    symbolTable.update(variableName, refValue);
                }
            }
            else
                throw new ReferenceException("The variable " + variableName + " is not of type RefType");
        }
        else
            throw new StatementException("The variable " + variableName + " is not defined");

        return state;
    }

    public String toString() {
        return "new(" + this.variableName + "," + this.expression.toString() + ")";
    }

}
