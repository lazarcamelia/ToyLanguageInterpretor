package model.values;

import model.types.IType;
import model.types.RefType;

public class RefValue implements IValue {
    private int address;
    private IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    public int getAddress() {
        return address;
    }

    public String toString() {
        return "(" + String.valueOf(this.address) + "," + this.locationType.toString() + ")";
    }

}
