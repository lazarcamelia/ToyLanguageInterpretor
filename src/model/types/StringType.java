package model.types;

import model.values.IValue;
import model.values.StringValue;

public class StringType implements IType{
    @Override
    public boolean equals(Object another) {
        if (another instanceof StringType)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public IValue defaultValue() {
        return new StringValue(null);
    }
}
