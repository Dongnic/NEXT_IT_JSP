import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.study.free.vo.FreeBoardVO;

public class Main {
	public static void main(String[] args) throws Exception {
		// 이것이 자바다 11.6 Class 클래스
		// 자바 reflection
		Class clazz = Class.forName("com.study.free.vo.FreeBoardVO");
		// Class '클래스'는 '클래스'들에 대한 정보를 가지고 있는 '클래스'
		// Class '클래스' 타입의 객체 clazz는 FreeBoardVO '클래스' 에 대한 정보를 가지고 있다.
		
		// 객체 얻기
		// 필드에 대한 정보얻기, 메소드에 대한 정보얻기
		// boNo~~boDelYn, toString, getBoNo setBoNo ~~~ getBoDelYn, setBoDelYn
		
		// 객체 얻기
		FreeBoardVO freeBoard = (FreeBoardVO)clazz.newInstance();
		
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			System.out.println("필드 이름: " + field.getName());
			System.out.println("필드 타입: " + field.getType().getTypeName());
			System.out.println("\n===================================\n");
		}
		
		// 메소드
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods) {
			System.out.println("메소드 이름 : " + method.getName());
			System.out.println("메소드 return type : " + method.getReturnType().getTypeName());
			System.out.println("메소드 파라미터 : " + method.getParameterTypes());
			
			if(method.getParameterCount()==1) {
				System.out.println("메소드 파라미터 타입 : "+ method.getParameterTypes()[0].getTypeName());
			}
			System.out.println("================================================================");
		}
		
		// 모든 set 메소드를 한 번에 실행시켜봅시다.
		System.out.println("모든 set메소드 실행 전 : " + freeBoard);
		for(Method method : methods) {
			if(method.getName().startsWith("set")) {
				if(!method.getParameterTypes()[0].getTypeName().equals("int")) {
					method.invoke(freeBoard, "abc");
				}
			}
		}
		System.out.println("모든 set메소드 실행 후 : " + freeBoard);
		
		
		
		
		
		
		
	}
}