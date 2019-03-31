package io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试流操作
 */
public class TestNestedMap {
	public void test1(){
		//学校 === 年级集合
		Map<String, Map<String, List<Student>>> grades = new HashMap<String, Map<String, List<Student>>>();
		for(int i = 1 ; i <= 6 ; i ++){
			//年级 === 班级的集合
			Map<String, List<Student>> classes = new HashMap<String, List<Student>>();
			for(int j = 1 ; j <= 10 ; j ++){
				//班级 === 学生集合
				List<Student> students = new ArrayList<Student>();
				for(int k = 1 ; i <= 50 ; k ++){
					Student s = new Student();
					s.setName("tom" + i + "." + j + "." + k );
					students.add(s);
				}
				classes.put(i + "." + j + "班", students);
			}
			grades.put(i + "年级", classes);
		}
		
		//输出
//		for(Entry<String, Map<String, List<Student>>> entry : grades.entrySet()){
//			for(){
//				for(){
//					
//				}
//			}
//		}
	}
}

class Student{
	private String name ;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
