package naivebayes.classification;

import java.util.ArrayList;
import java.util.List;

public class ClassificationContainerImpl implements ClassificationContainer {

	private final List<Classification> container = new ArrayList<Classification>();

	public ClassificationContainerImpl() {
	}

	@Override
	public boolean isEmpty() {
		return container.isEmpty();
	}

	@Override
	public List<Classification> getClassifications() {
		return container;
	}

	@Override
	public Classification get(final int i) {
		return container.get(i);
	}

	@Override
	public Classification remove(final int i) {
		return container.remove(i);
	}

	@Override
	public boolean add(final Classification classification) {
		return container.add(classification);
	}

}
