package model.expressions;

import model.adt.IHeap;
import model.adt.IMap;
import model.exceptions.EvaluateExpressionException;
import model.values.IValue;
import model.values.RefValue;

public class readHeapExpression implements Expression {
    private Expression expression;

    public readHeapExpression(Expression expression) { this.expression = expression; }


    @Override
    public IValue evaluate(IMap<String, IValue> table, IHeap<Integer, IValue> heapTable) throws EvaluateExpressionException {
        IValue expressionValue = expression.evaluate(table, heapTable);

        IValue valueFromHeap;

        if (expressionValue.getType().toString().contains("Ref")) {
            RefValue refValue = (RefValue)expressionValue;

            int address = refValue.getAddress();
            if (heapTable.isDefined(address)) {
                valueFromHeap = heapTable.getValue(address);
            }
            else
                throw new EvaluateExpressionException("The address" + String.valueOf(address) + " is not in the heap");
        }
        else
            throw new EvaluateExpressionException("The expression " + expression.toString() + " is not a reference");

        return valueFromHeap;
    }

    @Override
    public String toString() {
        return "readHeap(" + this.expression.toString() + ")";
    }

}
