package collection;

import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;

public class TestMap {
	@Test
	public void testNestedMap(){
		//  楼栋编号                       单元编号
		Map<Integer,Map<String,List<String>>> allBuilding = new HashMap<Integer,Map<String,List<String>>>();
		for(int i = 1 ; i <= 10 ; i ++){
			//   单元编号         人员名单
			Map<String,List<String>> allCells = new HashMap<String,List<String>>();
			//5个单元
			for(int j = 1 ; j <= 5 ; j ++){
				List<String> allNames = new ArrayList<String>();
				for(int k = 1 ; k <= 20 ; k ++){
					allNames.add("tom." + i + "." + j + "." + k);
				}
				allCells.put(i + "." + j, allNames);
			}
			allBuilding.put(i, allCells);
		}
		System.out.println("kk");
		Set<Entry<Integer,Map<String,List<String>>>> entries = allBuilding.entrySet();
		//迭代所有楼栋
		for(Iterator<Entry<Integer,Map<String,List<String>>>> it = entries.iterator(); it.hasNext() ; ){
			Entry<Integer,Map<String,List<String>>> e = it.next();
			Integer no = e.getKey();
			System.out.println(no + "栋=====>");
			Map<String,List<String>> value = e.getValue();
			Set<Entry<String,List<String>>> allCells0 =  value.entrySet();
			//迭代所有单元
			for(Iterator<Entry<String,List<String>>> itt = allCells0.iterator() ; itt.hasNext() ; ){
				Entry<String,List<String>> ee = itt.next();
				String cellNo =  ee.getKey();
				System.out.println(no + "栋=====>" + cellNo + "单元====>");
				List<String> allPeople0 = ee.getValue();
				//迭代所有人名单
				for(int i = 0 ; i < allPeople0.size() ; i ++){
					System.out.println(allPeople0.get(i));
				}
			}
		}
	}
}
 