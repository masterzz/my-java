package collection;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 测试TreeSet 
 *
 */
public class TestTreeSet {
	/**
	 * 
	 */
	@Test
	public void testAddInteger(){
		Set<Integer> set = new TreeSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(1);
		set.add(4);
		System.out.println(set.size());
	}
	
	@Test
	public void testAddDog(){
		Set<Dog> set = new TreeSet<Dog>(/*new DogComparator()*/);
		set.add(new Dog("太黄"));
		set.add(new Dog("大黄"));
		set.add(new Dog("二黄"));
		set.add(new Dog("三黄"));
		System.out.println(set.size());
		System.out.println("大黄".compareTo("二黄"));
		System.out.println((int)'大');
		System.out.println((int)'三');
		
		
		for(Iterator<Dog> it = set.iterator();it.hasNext();){
			System.out.println(it.next().name);
		}
	}
	
	/**
	 * Dog对比器
	 */
	class DogComparator implements Comparator<Dog>{
		public int compare(Dog o1, Dog o2) {
			if(o1 == null){
				if(o2 == null){
					return 0 ;
				}
				else{
					return -1 ;
				}
			}
			else{
				if(o2 == null){
					return 1 ;
				}
				else{
					return o1.name.compareTo(o2.name) ;
				}
			}
		}
	}
	
	class Dog implements Comparable<Dog>{
		private String name = "" ;
		public Dog(String name) {
			super();
			this.name = name;
		}
		
		public int compareTo(Dog o) {
			if(o == null){
				return 1 ;
			}
			return this.name.compareTo(o.name);
		}
	}
}
