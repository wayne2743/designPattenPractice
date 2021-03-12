package solid.design.principle.interf;

import java.util.List;

public interface Filter<T> {

	List<T> filterBySpecification(List<T> products, Specification<T> specification);

}