package model.expressions;


import model.adt.IHeap;
import model.adt.IMap;
import model.exceptions.EvaluateExpressionException;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

public class RelationalExpression implements Expression {
    private Expression left;
    private Expression right;
    private String operator;

    public RelationalExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(IMap<String, IValue> table, IHeap<Integer, IValue> heapTable) throws EvaluateExpressionException {
        IValue valueLeft, valueRight;
        BoolValue result = null;

        valueLeft = this.left.evaluate(table, heapTable);

        if (valueLeft.getType().equals(new IntType())) {
            valueRight = this.right.evaluate(table, heapTable);

            if (valueRight.getType().equals(new IntType())) {
                int leftVal = ((IntValue)valueLeft).getValue();
                int rightVal = ((IntValue)valueRight).getValue();

                switch (operator) {
                    case "<": {
                        result = new BoolValue(leftVal < rightVal);
                        break;
                    }
                    case "<=": {
                        result = new BoolValue(leftVal <= rightVal);
                        break;
                    }
                    case "==": {
                        result = new BoolValue(leftVal == rightVal);
                        break;
                    }
                    case "!=": {
                        result = new BoolValue(leftVal != rightVal);
                        break;
                    }
                    case ">": {
                        result = new BoolValue(leftVal > rightVal);
                        break;
                    }
                    case ">=": {
                        result = new BoolValue(leftVal >= rightVal);
                        break;
                    }
                }
            }
            else {
                throw new EvaluateExpressionException("The second operand is not an integer!");
            }
        }
        else
            throw new EvaluateExpressionException("The first operand is not an integer!");

        return result;
    }

    @Override
    public String toString() {
        return this.left.toString() + this.operator + this.right.toString();
    }
}
