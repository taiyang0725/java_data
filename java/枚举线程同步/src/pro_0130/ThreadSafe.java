package pro_0130;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//多个线程访问集合安全问题
public class ThreadSafe {
	public static void main(String[] args) {
		// 该方法返回指定集合对象对应的同步对象，从而解决多线程并发访问集合的线程安全问题
		Collection c = Collections.synchronizedCollection(new ArrayList());

		List l = (List) Collections.synchronizedList(new ArrayList());

		Set s = Collections.synchronizedSet(new HashSet());

		Map m = Collections.synchronizedMap(new HashMap());

	}
//创建不可变集合的方法
	public void unmodifiable() {
		
		List list = (List) Collections.emptyList();// 创建一个空的不可变的List对象
		
		Set set=Collections.singleton("wangan");//创建一个只有一个元素，切不可改变的Set对象
		
		Map scores=new HashMap();//创建一个普通的Map对象
		scores.put("语文", "86");
		scores.put("数学", "95");
		
		Map map=Collections.unmodifiableMap(scores);//返回普通Map对象的不可变版本
		
		//下面任意程序都会UnsupportedOperationException 不可操作异常
		
		list.add("可以吗");
		set.add(12);
		scores.put(5, 500);		
		
		

	}

}
