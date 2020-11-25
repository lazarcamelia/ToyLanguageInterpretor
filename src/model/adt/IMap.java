package model.adt;


import java.util.Map;

public interface IMap<T1, T2> {
    void add(T1 v1, T2 v2);
    void update(T1 v1, T2 v2);
    void delete(T1 v1);
    T2 lookUp(T1 id);
    boolean isDefined(T1 id);
    String toString();
    Map<T1, T2> getContent();

}
