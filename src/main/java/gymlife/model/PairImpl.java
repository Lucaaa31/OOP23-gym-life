package gymlife.model;

import gymlife.model.api.Pair;

public class PairImpl<T1, T2> implements Pair<T1, T2> {

    private T1 X;
    private T2 Y;

    public PairImpl(final T1 x, final T2 y){
        this.X = x;
        this.Y = y;
    }

    @Override
    public T1 getX() {
        return this.X;
    }

    @Override
    public T2 getY() {
        return this.Y;
    }

    @Override
    public void setX(final T1 x) {
        this.X = x;
    }

    @Override
    public void setY(final T2 y) {
        this.Y = y;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((X == null) ? 0 : X.hashCode());
		result = prime * result + ((Y == null) ? 0 : Y.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PairImpl other = (PairImpl) obj;
		if (X == null) {
			if (other.X != null)
				return false;
		} else if (!X.equals(other.X))
			return false;
		if (Y == null) {
			if (other.Y != null)
				return false;
		} else if (!Y.equals(other.Y))
			return false;
		return true;
	}

}
