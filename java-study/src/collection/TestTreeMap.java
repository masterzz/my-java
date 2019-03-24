package collection;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;


public class TestTreeMap {
	@Test
	public void testPut(){
		Map<Dog, String> map = new TreeMap<Dog,String>();
		map.put(new Dog("大黄"), "大黄不错!");
		map.put(new Dog("二黄"), "二黄很不错!");
		map.put(new Dog("三黄"), "三黄非常不错!");
		System.out.println(map.size());
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
