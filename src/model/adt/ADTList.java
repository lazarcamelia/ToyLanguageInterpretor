package model.adt;

import java.util.ArrayList;

public class ADTList<T> implements IList<T> {
    private ArrayList<T> myList;

    public ADTList() {
        myList = new ArrayList<>();
    }

    @Override
    public void add(T v) {
        myList.add(v);
    }

    @Override
    public T pop() {return myList.remove(myList.size() - 1);}

    @Override
    public String toString() {
        return myList.toString();
    }
}
