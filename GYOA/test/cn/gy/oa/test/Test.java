package cn.gy.oa.test;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	@org.junit.Test
	public void test01() {
		System.out.println(Math.round(-11.6));//-10.8
		System.out.println(Math.round(-10.9));//
		
		String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);//false
        System.out.println(s1 == s3);//false为什么是true
        System.out.println(s1 == s1.intern());//true
        String originStr = "abc";//bca
//        System.out.println("a".substring(1));
        System.out.println();
        System.out.println(myreverse("abcd"));
//        System.out.println(reverse("abcd"));
//        System.out.println(originStr.substring(1) + originStr.charAt(0));//bca
        //originStr.substring(1) = c
        //其实效率上的比较是什么原因呢？
        //其实就是
        /*
         * 一个 StringBuilder 对象（创建对象时需要耗费时间和内存），随着循环次数的增大，
         *  result 字符串就会越来越长，把 result 中的字符复制到新建的 StringBuilder 
         *  中花费的时间也就越长，而且 StringBuilder(result).append(str).toString() 
         *  会创建一个临时的字符串，随着循环次数的增加，这个操作花费的时间也会越来越长。
         *  总之，随着循环变量 i 的增大，每次循环会变得越来越慢。
         */
	}
	
	/**
	 * 出现了栈溢出===>为什么?
	 * 
	 * abcd
	 * 	1:bcd a
	 *  2:cd  b
	 *  3:d   c
	 *  4:d	  d
	 *  一次截取一个放到堆栈中
	 * @param originStr
	 * @return
	 */
	public static String myreverse(String originStr) {
//		System.out.println("递归截取:" + originStr);
		if(originStr == null || originStr.length() <= 1)
			return originStr;
		return myreverse(originStr.substring(1) + originStr.charAt(0));
	}
	/*
	 * 表5.懒惰限定符
代码/语法	说明
*?	重复任意次，但尽可能少重复
+?	重复1次或更多次，但尽可能少重复
??	重复0次或1次，但尽可能少重复
{n,m}?	重复n到m次，但尽可能少重复
{n,}?	重复n次以上，但尽可能少重复
重复后面加？表示少重复
	 */
	
	@org.junit.Test
	public void test02() throws UnsupportedEncodingException {
		System.out.println(myrev("abcd"));
		System.out.println(myrev("abcde"));
		System.out.println(myrev2("abcde"));
		System.out.println(myrev3("abcde"));
		String a = "abc";
		char[] cs = a.toCharArray();
		int x = 7;
	    int y = 8;
	    System.out.println(x+":"+y);
	    x = x^y; //x被y异或一次
	    //首先是异或第一次
	    y = x^y; //此时x存放的是x与y的异或，因此相当于被y又异或一次，x被y异或两次，得到x并赋给y
	    x = x^y; //此时x存放的是x与y的异或，而y存放的就是x的值，因此再异或一次y就相当于异或x一次就得到x
	    System.out.println(x+":"+y);
	    String s1 = "你好";
	    String s2 = new String(s1.getBytes("GB2312"), "ISO-8859-1");
	    System.out.println(s2);
	    Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH));    // 0 - 11
        System.out.println(cal.get(Calendar.DATE));
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal.get(Calendar.MINUTE));
        System.out.println(cal.get(Calendar.SECOND));
 
        // Java 8
//        LocalDateTime dt = LocalDateTime.now();
//        System.out.println(dt.getYear());
//        System.out.println(dt.getMonthValue());     // 1 - 12
//        System.out.println(dt.getDayOfMonth());
//        System.out.println(dt.getHour());
//        System.out.println(dt.getMinute());
//        System.out.println(dt.getSecond());
        String str = "北京市(朝阳区)(西城区)(海淀区)";
        Pattern p = Pattern.compile(".*?(?=\\()");//.匹配任意字符除了换行 \\(==》转成了(
        //不然()是一种分组
        //	(?=exp)	匹配exp前面的位置
        //  (?=\\() 匹配(前面的位置
        //往前面加?表示 0个或者1个
        Matcher m = p.matcher(str);
        if(m.find()) {
            System.out.println(m.group());
        }
	}
	/*
	 * 
表4.常用分组语法
分类	代码/语法	说明
捕获	(exp)	匹配exp,并捕获文本到自动命名的组里
(?<name>exp)	匹配exp,并捕获文本到名称为name的组里，也可以写成(?'name'exp)
(?:exp)	匹配exp,不捕获匹配的文本，也不给此分组分配组号
零宽断言	(?=exp)	匹配exp前面的位置
(?<=exp)	匹配exp后面的位置
(?!exp)	匹配后面跟的不是exp的位置
(?<!exp)	匹配前面不是exp的位置
注释	(?#comment)	这种类型的分组不对正则表达式的处理产生任何影响，用于提供注释让人阅读
	 */
	
	
	//2729772006
	public static String myrev(String s) {//abcd
		int len = s.length();
		if(len <= 1) {
			return s;
		}
		//abc
		//最后a
		//bc==>cb
		String left = s.substring(0, len / 2);//ab 
		String right = s.substring(len / 2, len);//cd 
		return myrev(right) + myrev(left);//d c b a
	}
	
	public static String myrev2(String s) {
		String reverse = "";
		for(int i = 0; i < s.length(); i ++) {
			reverse = s.charAt(i) + reverse;
		}
		return reverse;
	}
	
	public static String myrev3(String s) {
		char[] str = s.toCharArray();
		
		int begin = 0;
		int end = s.length() - 1;
		//进行异或操作交换
		while(begin < end) {
			str[begin] = (char) (str[begin] ^ str[end]);
			str[end] = (char) (str[begin] ^ str[end]);
			str[begin] = (char) (str[begin] ^ str[end]);
			begin++;
			end--;
		}
		return new String(str);
	}
	
}
