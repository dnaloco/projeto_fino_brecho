package br.arthur.temp.tests;


import java.util.ArrayList;
import java.util.List;

import br.arthur.utils.MyIteratorUtil;

public class anyTeste01 {
	public static void main(String[] args) {
		List<Object> testIter = new ArrayList();
		
		testIter.add("arthur 1");
		testIter.add("arthur 2");
		testIter.add("arthur 3");
		testIter.add("arthur 4");
		testIter.add("arthur 5");
		testIter.add("arthur 6");
		testIter.add("arthur 7");
		
		MyIteratorUtil iter = new MyIteratorUtil(testIter);
		System.out.println(iter.last());
		while(iter.hasNext()) {
			System.out.println(iter.next());
			
		}
		System.out.println(iter.first());
		while(iter.hasPrevious()) {
			System.out.println(iter.previous());
		}
		System.out.println(iter.last());
		while(iter.hasNext()) {
			System.out.println(iter.next());
			
		}
		
		//System.out.println(iter.last());
		//System.out.println(iter.first());
	}
}
