package model.expressions;

import model.adt.IHeap;
import model.types.IntType;
import model.values.IntValue;
import model.values.IValue;
import model.adt.IMap;
import model.exceptions.EvaluateExpressionException;

public class ArithmeticExpression implements Expression {
    private Expression left;
    private Expression right;
    private int operator; //1 - plus, 2 - minus, 3 - multiplication, 4 - division

    public ArithmeticExpression(char op, Expression leftExp, Expression rightExpr) {
        left = leftExp;
        right = rightExpr;

        if (op == '+')
            operator = 1;
        else if (op == '-')
            operator = 2;
        else if (op == '*')
            operator = 3;
        else if (op == '/')
            operator = 4;
        else
            throw new EvaluateExpressionException("No operator found!");
    }

    public IValue evaluate(IMap<String, IValue> table, IHeap<Integer, IValue> heapTable) throws EvaluateExpressionException{
        IValue valueOnLeft;
        IValue valueOnRight;

        valueOnLeft = left.evaluate(table, heapTable);

        if (valueOnLeft.getType().equals(new IntType())) {
            valueOnRight = right.evaluate(table, heapTable);

            if (valueOnRight.getType().equals(new IntType())) {
                IntValue leftIntValue = (IntValue)valueOnLeft;
                IntValue rightIntValue = (IntValue)valueOnRight;

                int leftOperand, rightOperand;

                leftOperand = leftIntValue.getValue();
                rightOperand = rightIntValue.getValue();

                return getResultFromOperation(leftOperand, rightOperand);
            }
            else {
                throw new EvaluateExpressionException("Second operand is not an integer!");
            }
        }
        else {
            throw new EvaluateExpressionException("First operator is not an integer");
        }
    }

    private IntValue getResultFromOperation(int leftOperand, int rightOperand) {
        if (operator == 1) {
            return new IntValue(leftOperand + rightOperand);
        }
        else if (operator == 2) {
            return new IntValue(leftOperand - rightOperand);
        }
        else if (operator == 3) {
            return new IntValue(leftOperand * rightOperand);
        }
        else if (operator == 4) {
            if (rightOperand == 0) {
                throw new EvaluateExpressionException("Division by zero!");
            }
            return new IntValue(leftOperand / rightOperand);
        }
        else {
            throw new EvaluateExpressionException("No operator identified!");
        }
    }

    @Override
    public String toString() {
        if (operator == 1) {
            return left.toString() + "+" + right.toString();
        }
        else if (operator == 2) {
            return left.toString() + "-" + right.toString();

        }
        else if (operator == 3) {
            return left.toString() + "*" + right.toString();

        }
        return left.toString() + "/" + right.toString();
    }
}
