package iterator;

import java.util.Iterator;



public class Combined<E> implements Iterable<E>{


	private Iterator<E> first;
	private Iterator<E> second;
	private int count1 = 0 ;
	private int count2 = 0 ;

	

	public Combined(Iterable<E> first, Iterable<E> second) {
		this.first =  first.iterator();
		this.second =  second.iterator();

	}


	private class Iterator2 implements Iterator<E>{

		@Override
		public boolean hasNext() {
			
			return first.hasNext() || second.hasNext();
		}

		@Override
		public E next() {
			if(hasNext()) {
				
				if(second.hasNext()) {
					if(count2<count1) {
						count2++;
						
						return (E)second.next();
					}
				}
				if(first.hasNext()) {
					count1++;
				 return (E)first.next();}
				 
			
				}
				
				
				count2++;
				return (E)second.next();
			
			}
		}
		
		
	
		
	
	
	
	@Override
	public Iterator<E> iterator() {
		
		return new Iterator2();
	}
	

	
}
