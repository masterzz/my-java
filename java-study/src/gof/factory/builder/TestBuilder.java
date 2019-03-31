package gof.factory.builder;

public class TestBuilder {
	public void test1(){
		Phone p = new Phone()
				.setBrand("a")
				.setName("b")
				.setProductArea("c")
				.setPrice((int)'d');
		System.out.println(p.getName());
	}
}
