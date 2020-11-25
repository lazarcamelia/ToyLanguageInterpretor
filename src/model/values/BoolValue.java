package model.values;

import model.types.BoolType;
import model.types.IType;

public class BoolValue implements IValue {
    private boolean value;

    public  BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf((value));
    }

    public IType getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoolValue)
            return true;
        else
            return false;
    }
}
