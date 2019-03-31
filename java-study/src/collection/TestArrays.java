package collection;

import org.junit.Test;

import java.util.*;

public class TestArrays {
	@Test
	public void test1(){
		int[] arr = {3,1,2,5} ;
		Arrays.sort(arr);
		for(int i = 0 ; i < arr.length ; i ++){
			System.out.println(arr[i]);
		}
		//填充
		//Arrays.fill(arr, 100);
		System.out.println();
		
		int index = Arrays.binarySearch(arr, 3);
		System.out.println(index);
	}
	
	/**
	 * 集合工具类
	 */
	@Test
	public void testColletions(){
		List<String> list = new LinkedList<String>();
		list.add("jerry");
		list.add("john");
		list.add("bob");
//		Collections.fill(list, "tom");
//		String s = Collections.max(list);
		Collections.reverse(list);
	}
	
	/**
	 * 增强for循环
	 */
	@Test
	public void testProFor(){
		List<String> list = new ArrayList<String>();
		list.add("tom");
		list.add("tomas");
		list.add("tomasLee");
		list.add("tomson");
		//for
		for(int i = 0 ; i < list.size() ; i ++){
			System.out.println(list.get(i));
		}
		//iterator
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		//增强for循环
		for(String s : list){
			System.out.println(s);
		}
		//
		int[] arr = {1,2,3,4};
		for(int i : arr){
			System.out.println(i);
		}
		//
		Map<String, String> map = new HashMap<String,String>();
		map.put("1", "tom1");
		map.put("2", "tom2");
		map.put("3", "tom3");
		//迭代entry集合
//		Set<Entry<String, String>> set = map.entrySet() ;
//		for(Entry<String, String> e : set){
//			String key = e.getKey();
//			String value = e.getValue();
//			System.out.println(key + " = " + value );
//		}
		Set<String> set = map.keySet();
		set = null; 
		for(String s : set){
			System.out.println(s + " = " + map.get(s));
		}
	}
	
	/**
	 * 测试变长参数
	 */
	@Test
	public void testVarParam(){
		List<String> list = new ArrayList<String>();
		addEle(list/*,"tom","tomas","tomasLee","xxx","yyy"*/);
		System.out.println(list.size());
	}
	
	private void addEle(List list,String...x){
		for(String s : x){
			list.add(s);
		}
	}
}
