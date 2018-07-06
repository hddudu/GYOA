package cn.gy.oa.test;

import java.lang.reflect.ParameterizedType;

public class TestReflect extends TT2<Integer>{
	public static void main(String[] args) {
		System.out.println(((ParameterizedType)new TestReflect().getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);//class java.lang.Integer
		System.out.println(((ParameterizedType)new TestReflect().getClass()
				.getGenericSuperclass()).getActualTypeArguments().length);//1
		System.out.println(((ParameterizedType)new TestReflect().getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);//cn.gy.oa.test.TT2<java.lang.Integer>
		Class c = (Class) ((ParameterizedType)new TestReflect().getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		System.out.println(c.getSimpleName());//获取类名: Integer
//		 getGenericSuperclass() 通过反射获取当前类表示的实体（类，接口，基本类型或void）的直接父类的Type，
//		 getActualTypeArguments()返回参数数组。
		
	}
}
