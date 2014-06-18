package naivebayes.classification;

import naivebayes.vocabulary.Vocabulary;

public abstract class Classification {

	private final String name;
	private final double priory;
	private final Vocabulary vocabulary;

	public Classification(final String name, final double priory, final Vocabulary vocabulary, final ClassificationContainer classificationContainer) {
		this.name = name;
		this.priory = priory;
		this.vocabulary = vocabulary;
		classificationContainer.add(this);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the priory
	 */
	public double getPriory() {
		return priory;
	}

	/**
	 * @return the vocabulary
	 */
	public Vocabulary getVocabulary() {
		return vocabulary;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Classification [name=" + name + ", priory=" + priory + ", vocabulary=" + vocabulary + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(priory);
		result = prime * result + (int) (temp ^ temp >>> 32);
		result = prime * result + (vocabulary == null ? 0 : vocabulary.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Classification other = (Classification) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (Double.doubleToLongBits(priory) != Double.doubleToLongBits(other.priory)) {
			return false;
		}
		if (vocabulary == null) {
			if (other.vocabulary != null) {
				return false;
			}
		} else if (!vocabulary.equals(other.vocabulary)) {
			return false;
		}
		return true;
	}

}
