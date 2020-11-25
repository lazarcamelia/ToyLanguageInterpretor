package model.types;

import model.values.BoolValue;
import model.values.IValue;

public class BoolType implements IType {
    @Override
    public boolean equals(Object another) {
        if (another instanceof BoolType)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }
}
