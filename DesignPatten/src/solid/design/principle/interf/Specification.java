package solid.design.principle.interf;

public interface Specification<T> {

	boolean isSatisfied(T items);

}