package naivebayes.classification;

import java.util.ArrayList;
import java.util.List;

public class ClassificationContainerImpl implements ClassificationContainer {

	private final List<Classification> container = new ArrayList<Classification>();

	public ClassificationContainerImpl() {
	}

	public boolean isEmpty() {
		return container.isEmpty();
	}

	public List<Classification> getClassifications() {
		return container;
	}

	public Classification get(final int i) {
		return container.get(i);
	}

	public Classification remove(final int i) {
		return container.remove(i);
	}

	public boolean add(final Classification classification) {
		return container.add(classification);
	}

}
