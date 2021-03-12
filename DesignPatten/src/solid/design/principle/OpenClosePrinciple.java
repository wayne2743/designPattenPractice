package solid.design.principle;


import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import solid.design.principle.interf.Filter;
import solid.design.principle.interf.Specification;

public class OpenClosePrinciple{

	public static void main(String[] args) {
		ProductFilter pf = new ProductFilter();
		List<Product> products = Stream.of(new Product("Sun",Color.blue,Size.large),
											new Product("Moon",Color.red,Size.small),
											 new Product("Start",Color.red,Size.medium)
										).collect(Collectors.toList());
		
//		System.out.println(pf.filterByColor(products, Color.red).collect(Collectors.toList()));
		BetterFilter bf = new BetterFilter();
//		System.out.println(bf.filterBySpecification(products, new ColorSpecification(Color.red)));
		System.out.println(bf.filterBySpecification(products, new SizeSpecification(Size.large)));

	}
}

enum Color{
	red, blue, green
}

enum Size{
	large, medium, small
}

// Product bean
class Product{
	private String name;
	private Color color;
	private Size size;
	
	
	
	public Product(String name, Color color, Size size) {
		super();
		this.name = name;
		this.color = color;
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", color=" + color + ", size=" + size + "]";
	}
	
}

class ColorSpecification implements Specification<Product>{
	private Color color;	

	public ColorSpecification(Color color) {
		this.color = color;
	}

	@Override
	public boolean isSatisfied(Product product) {
		return product.getColor() == this.color ;
	}
}

class SizeSpecification implements Specification<Product>{
	private Size size;
	
	
	public SizeSpecification(Size size) {
		super();
		this.size = size;
	}


	@Override
	public boolean isSatisfied(Product items) {
		return items.getSize()==this.size;
	}

}


class BetterFilter implements Filter<Product>{

	@Override
	public List<Product> filterBySpecification(List<Product> products, Specification<Product> specification) {
		return products.stream().filter(p -> specification.isSatisfied(p)).collect(Collectors.toList());
	};	
}



class ProductFilter{
	public Stream<Product> filterByColor(List<Product> products, Color color) {
		return products.stream().filter(p -> p.getColor() == color);
		
	}
}