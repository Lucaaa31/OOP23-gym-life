package gymlife.model.api;

public interface Pair<T,R>{
    /**
     * 
     * @return Returns the first element of the pair.
     */
    T getX();

    /**
     * 
     * @return Returns the second element of the pair.
     */
    R getY();

    /**
     * 
     * @param x New value to assign to first element.
     */
    void setX(T x);

    /**
     * 
     * @param y New value to assign to second element.
     */
    void setY(R y);
}
