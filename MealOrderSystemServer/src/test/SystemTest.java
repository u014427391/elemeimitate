package test;

import java.util.List;
import java.util.Vector;

import com.moss.dao.BusinessDao;

public class SystemTest {

	public static void main(String[] args) {
		
		BusinessDao dao = new BusinessDao();
		List<Vector> list = dao.getAllBusinessInfo();
		for(Vector v:list){
			System.out.println(v.get(0));
			System.out.println(v.get(1));
			System.out.println(v.get(2));
			System.out.println(v.get(3));
			System.out.println(v.get(4));
			System.out.println(v.get(5));
			System.out.println(v.get(6));
			System.out.println(v.get(7));
			System.out.println(v.get(8));
		}

		
	}
}
