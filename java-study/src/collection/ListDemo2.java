package collection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * add()
 * get(int index);
 */
public class ListDemo2 {
	public static void main(String[] args) {
		int MAX = 1000000 ;
		List<Cat> list = new ArrayList<Cat>();
		long start = System.currentTimeMillis() ;
		for(int i = 0 ; i < MAX ; i ++){
			list.add(new Cat("tom" + i, i));
		}
		
		System.out.println("put : " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis() ;
		//遍历集合
		Cat cat = null ;
		for(int i = 0 ; i < MAX ; i ++){
			cat = list.get(i);
		}
		System.out.println("get : " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis() ;
		//遍历集合
		for(int i = 0 ; i < MAX ; i ++){
			Cat cat0 = list.get(i);
		}
		System.out.println("get : " + (System.currentTimeMillis() - start));
		
		System.out.println("========================");
		/**
		 * 通过Iterator实现对List的遍历访问
		 */
		Iterator<Cat> it = list.iterator();
		start = System.currentTimeMillis() ;
		while(it.hasNext()){
			Cat cat1 =  it.next();
//			int index = list.indexOf(cat1);
//			System.out.println(index);
		}
		System.out.println(System.currentTimeMillis() - start);
	}
	
	/**
	 * 清空集合
	 */
	@Test
	public void testClear(){
		//创建集合
		int MAX = 330 ;
		List<Cat> list = new ArrayList<Cat>();
		long start = System.currentTimeMillis() ;
		for(int i = 0 ; i < MAX ; i ++){
			list.add(new Cat("tom" + i, i));
		}
		
		list.clear();
		System.out.println(list.size());
	}
	
	/**
	 * 清空集合
	 */
	@Test
	public void testRemove(){
		//创建集合
		int MAX = 330 ;
		List<Cat> list = new ArrayList<Cat>();
		long start = System.currentTimeMillis() ;
		for(int i = 0 ; i < MAX ; i ++){
			list.add(new Cat("tom" + i, i));
		}
		
		list.remove(1);
		System.out.println(list.size());
	}
	
	/**
	 * indexOf集合
	 */
	@Test
	public void testIndexOf(){
		//创建集合
		int MAX = 330 ;
		Cat obj = null ;
		List<Cat> list = new ArrayList<Cat>();
		long start = System.currentTimeMillis() ;
		for(int i = 0 ; i < MAX ; i ++){
			if(i == 24){
				obj = new Cat("tom" + i, i) ;
				list.add(obj);
			}
			list.add(new Cat("tom" + i, i));
		}
		Cat c = new Cat("tom24", 24);
		int index = list.indexOf(c);
		System.out.println(index);
		
		//
//		Cat cc = new Cat("jerry", 12);
//		cc.equals(null);
	}
	
	/**
	 * 测试重复性
	 */
	@Test
	public void testRepeat(){
		Cat c = new Cat("tom",3);
		List<Cat> list = new ArrayList<Cat>();
		list.add(c);
		list.add(c);
		list.add(c);
		System.out.println(list.size());
		list.get(0).setName("tomas");
		System.out.println(list.get(2).getName());
	}
}
