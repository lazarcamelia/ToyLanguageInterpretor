package model.values;

import model.types.BoolType;
import model.types.IType;
import model.types.IntType;

public class IntValue implements IValue {
    private int value;

    public  IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf((value));
    }

    public IType getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntValue)
            return true;
        else
            return false;
    }
}
