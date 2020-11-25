package model.expressions;

import model.adt.IHeap;
import model.values.IValue;
import model.adt.IMap;
import model.exceptions.EvaluateExpressionException;

public class VariableExpression implements Expression {
    private String variableName;

    public VariableExpression(String varName) {
        variableName = varName;
    }

    public IValue evaluate(IMap<String, IValue> table, IHeap<Integer, IValue> heapTable) throws EvaluateExpressionException {
        if (table.isDefined(variableName)) {
            return table.lookUp(variableName);
        }
        else
            throw new EvaluateExpressionException("The variable is not defined!");
    }

    @Override
    public String toString() {
        return variableName;
    }
}
