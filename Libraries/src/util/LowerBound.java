package util;

import java.util.Arrays;
import java.util.Comparator;

// http://qiita.com/taskie/items/b4e45e2005aa38e90bcb
public class LowerBound {

	public static void main(String[] args) {
		Integer[] test = new Integer[]{1,2,3,3,4};
		
		System.out.println(lowerBound(new Integer(3), test));
		System.out.println(lowerBound(new Integer(4), test));
	}
	
	
	
	static <T extends Comparable> int lowerBound(T t, T[] ar) {
		return ~Arrays.binarySearch(ar, t, new Comparator<T>() {
			public int compare(T x, T y) {return (x.compareTo(y) >= 0) ? 1 : -1;}
		});
	}
	
	static <T extends Comparable> int upperBound(T t, T[] ar) {
		return ~Arrays.binarySearch(ar, t, new Comparator<T>() {
			public int compare(T x, T y) {return (x.compareTo(y) > 0) ? 1 : -1;}
		});
	}
}
