package model.values;


import model.types.BoolType;
import model.types.IType;
import model.types.StringType;

public class StringValue implements IValue{
    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return value;
    }

    public IType getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StringValue)
            return true;
        else
            return false;
    }
}
