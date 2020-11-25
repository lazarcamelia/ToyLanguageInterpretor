package model.expressions;

import model.adt.IHeap;
import model.values.IValue;
import model.adt.IMap;
import model.exceptions.EvaluateExpressionException;

public class ValueExpression implements Expression {
    private IValue value;

    public  ValueExpression(IValue val) {
        value = val;
    }

    @Override
    public IValue evaluate(IMap<String, IValue> table, IHeap<Integer, IValue> heapTable) throws EvaluateExpressionException {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
