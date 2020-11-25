package model.expressions;

import model.adt.IHeap;
import model.values.IValue;
import model.adt.IMap;
import model.exceptions.EvaluateExpressionException;

public interface Expression {
    IValue evaluate(IMap<String, IValue> table, IHeap<Integer, IValue> heapTable) throws EvaluateExpressionException;
}
