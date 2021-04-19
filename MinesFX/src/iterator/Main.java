package iterator;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		    List<String> list = Arrays.asList("one", "two", "three");
		    Set<String> set = new TreeSet<>();
		    set.addAll(Arrays.asList("B", "A", "D", "C", "E"));
		    Combined<String> c = new Combined<>(list, set);
		for (String s : c) 
		        System.out.print(s + " ");
		}


}
