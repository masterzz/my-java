package collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/**
 * Set
 * 无序，不重复。
 */
public class TestSet {
	@Test
	public void testAddString(){
		Set<String> set = new HashSet<String>();
		set.add("tom");
		set.add("tomas");
		set.add("tomason");
		set.add("tomasLee");
		//System.out.println(set.size());
		
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	@Test
	public void testAddInteger(){
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0 ; i < 17 ; i ++){
			set.add(i);
		}
		//System.out.println(set.size());
		
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	@Test
	public void testHashCode(){
		Dog d = new Dog();
		System.out.println(d.hashCode());
		
		d = new Dog();
		System.out.println(d.hashCode());
		
		d = new Dog();
		System.out.println(d.hashCode());
	}
	
	
	@Test
	public void testHashSet(){
		Dog d = new Dog(20,40);
		Set<Dog> set = new HashSet<Dog>();
		set.add(/*new Dog(20,40)*/ d);
		set.add(/*new Dog(20,40)*/ d);
//		set.add(new Dog(20,40));
		System.out.println(set.size());
	}
	
	@Test
	public void testHashSet2(){
		Set<Dog> set = new HashSet<Dog>();
		set.add(new Dog(20,40));
		set.add(new Dog(40,20));
		System.out.println(set.size());
	}
	
	@Test
	public void testHashSet3(){
		Set<Dog> set = new HashSet<Dog>();
		Dog d = new Dog(20,40);
		set.add(d);
		set.add(d);
		System.out.println(set.size());
	}
	
	@Test
	public void testHashSet4(){
		Set<Dog> set = new HashSet<Dog>();
		set.add(new Dog(20,40));
		set.add(new Dog(40,20));
		System.out.println(set.size());
	}
}

class Dog{
	int x = 1 ;
	public int height ; 
	public int weight ;
	public Dog() {
		super();
	}
	public Dog(int height, int weight) {
		super();
		this.height = height;
		this.weight = weight;
	}
	public int hashCode() {
		return height + weight ;
	}
	public boolean equals(Object obj) {
		return true ;
	}
}
