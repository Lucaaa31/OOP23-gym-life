package gymlife.model.api;

public interface Pair<T1,T2>{
    
    T1 getX();

    T2 getY();

    void setX(final T1 x);

    void setY(final T2 y);
}
