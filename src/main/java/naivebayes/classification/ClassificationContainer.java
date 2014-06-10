package naivebayes.classification;

import java.util.List;

public interface ClassificationContainer {

	boolean isEmpty();

	List<Classification> getClassifications();

	Classification get(int i);

	Classification remove(int i);

	boolean add(Classification classification);

}
