package collection;

public class Cat {
	private String name ;
	private int age ;
	public Cat(String name , int age){
		this.name = name ;
		this. age = age ;
	}
	public String getName(){
		return this.name ;
	}
	public void setName(String name){
		this.name = name ;
	}
	public int getAge(){
		return age ;
	}
	public void setAge(int age){
		this.age = age ;
	}
	
	public boolean equals(Object obj) {
		//obj为null
		if(obj == null){
			return false ;
		}
		//是否是同一对象
		if(this == obj){
			return true ;
		}
		//得到obj的类
		Class objClazz = obj.getClass();
		if(objClazz != Cat.class){
			return false ;
		}
		//
		Cat b = (Cat)obj;
		String bname = b.getName();
		int bage = b.getAge() ;
		//
		boolean nameEqual = false ;
		if(name == null){
			if(bname == null){
				nameEqual = true ;
			}
//			else{
//				nameEqual = false ;
//			}
		}
		else{
			nameEqual = name.equals(bname);
//			if(bname == null){
//				nameEqual = false ;
//			}
//			else{
//			}
		}
		//boolean ageEqual = age == bage ;
		return nameEqual && (age == bage) ;
	}
}
