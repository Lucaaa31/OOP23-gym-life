package gymlife.model;

import gymlife.model.api.Pair;

public class PairImpl<T1, T2> implements Pair<T1, T2> {

	private T1 x;
	private T2 y;

	public PairImpl(final T1 x, final T2 y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public T1 getX() {
		return this.x;
	}

	@Override
	public T2 getY() {
		return this.y;
	}

	@Override
	public void setX(final T1 x) {
		this.x = x;
	}

	@Override
	public void setY(final T2 y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
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
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

}
