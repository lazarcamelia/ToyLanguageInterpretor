package model.adt;

import java.util.HashMap;
import java.util.Map;

public class ADTMap<T1, T2> implements IMap<T1, T2>{
    private HashMap<T1, T2> myMap;

    public ADTMap() {
        myMap = new HashMap<T1, T2>();
    }

    @Override
    public void add(T1 v1, T2 v2) {
        myMap.put(v1, v2);
    }

    @Override
    public void update(T1 v1, T2 v2) {
        myMap.replace(v1, v2);
    }

    @Override
    public void delete(T1 v1) {
        myMap.remove(v1);
    }

    @Override
    public T2 lookUp(T1 id) {
        return myMap.get(id);
    }

    @Override
    public boolean isDefined(T1 id) {
        if (myMap.get(id) == null)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return myMap.toString();
    }

    @Override
    public Map<T1, T2> getContent() {
        return this.myMap;
    }
}
