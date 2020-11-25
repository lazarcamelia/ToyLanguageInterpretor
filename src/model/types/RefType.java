package model.types;

import model.values.IValue;
import model.values.RefValue;

public class RefType implements IType {
    private IType inner;

    public RefType(IType inner)  {
        this.inner = inner;
    }

    public IType getInnerType() {
        return inner;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RefType)
            return inner.equals(((RefType) obj).getInnerType());
        else
            return false;
    }

    @Override
    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public IValue defaultValue() {
        return new RefValue(0, inner);
    }
}
