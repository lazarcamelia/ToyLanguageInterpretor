package model.expressions;

import model.adt.IHeap;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;
import model.adt.IMap;
import model.exceptions.EvaluateExpressionException;

public class LogicExpression implements Expression {
    private Expression left;
    private Expression right;
    private int operator;

    public LogicExpression(String op, Expression leftExpr, Expression rightExpr) {
        left = leftExpr;
        right = rightExpr;

        if (op.equals("&&"))
            operator = 1;
        else if (op.equals("||"))
            operator = 2;
        else
            throw new EvaluateExpressionException("No operator found!");

    }

    public IValue evaluate(IMap<String, IValue> symbolTable, IHeap<Integer, IValue> heapTable) throws EvaluateExpressionException {
        IValue valueOnLeft;
        IValue valueOnRight;

        valueOnLeft = left.evaluate(symbolTable, heapTable);

        if (valueOnLeft.getType().equals(new BoolType())) {
            valueOnRight = right.evaluate(symbolTable, heapTable);

            if (valueOnRight.getType().equals(new BoolType())) {
                BoolValue leftBoolValue = (BoolValue)valueOnLeft;
                BoolValue rightBoolValue = (BoolValue)valueOnRight;

                boolean leftOperand, rightOperand;

                leftOperand = leftBoolValue.getValue();
                rightOperand = rightBoolValue.getValue();

                return getResultFromOperation(leftOperand, rightOperand);
            }
            else {
                throw new EvaluateExpressionException("Second operand is not a boolean!");
            }
        }
        else {
            throw new EvaluateExpressionException("First operator is not a boolean");
        }
    }

    private BoolValue getResultFromOperation(boolean leftOperand, boolean rightOperand) {
        if (operator == 1) {
            return new BoolValue(leftOperand && rightOperand);
        }
        else if (operator == 2) {
            return new BoolValue(leftOperand || rightOperand);
        }
        else {
            throw new EvaluateExpressionException("No operator identified!");
        }
    }

    @Override
    public String toString() {
        if (operator == 1) {
            return left.toString() + "&&" + right.toString();
        }
        return left.toString() + "||" + right.toString();
    }
}
