package br.arthur.utils;

import java.util.List;

public class MyIteratorUtil {
	List<Object> iter;
	private int index;
	
	public MyIteratorUtil(List iter) {
		this.iter = iter;
		this.index = 0;
	}
	
	public boolean hasNext() {
		if(iter.size() > index + 1) {
			return true;
		}
		
		return false;
	}
	
	public Object next() {
		index += 1;
		if(hasNext()) {
			return iter.get(index);
		} else {
			return iter.get(iter.size() - 1);
		}
	}
	
	public boolean hasPrevious() {
		
		if(index - 1 >= 0) {
			return true;
		}
		
		return false;
	}
	
	public Object previous() {

		if(hasPrevious()) {
			index -= 1;
			return iter.get(index);
		} else {
			return iter.get(0);
		}

	}
	
	public Object first() {
		index = 0;
		return iter.get(index);
	}
	
	public Object last() {
		index = iter.size() - 1;
		return iter.get(index);
	}
	
	public Object actual() {
		return iter.get(index);
	}
}
