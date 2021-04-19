package iterator;

import java.util.Iterator;


public class TwoArrays implements Iterable<Integer>{
	

	private int[] a1,a2;
	private int count1 = 0 ;
	private int count2 = 0 ;

	public TwoArrays(int[] a1, int[] a2) {
		this.a1 = a1;
		this.a2 = a2;
	}
	
	
	@SuppressWarnings("unused")
	private class Iterator1 implements Iterator<Integer>{
		
	@Override
	public Integer next() {
		if(hasNext()) {
		
		if(count2<a2.length) {
			if(count2<count1) {
				return a2[count2++];
			}
		}
		if(count1<a1.length)
		 return a1[count1++];
		 
	
		}
		
		return a2[count2++];
	
	}


	@Override
	public boolean hasNext() {
		return count1 < a1.length || count2 < a2.length;
	}

}


	@Override
	public Iterator<Integer> iterator() {
		return new Iterator1();
	}
}