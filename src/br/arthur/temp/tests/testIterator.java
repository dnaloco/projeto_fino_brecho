package br.arthur.temp.tests;

import java.util.ArrayList;

public class testIterator {
	private static int pos = 0;
	private static ArrayList teste;
	
	public static void main(String[] args) {
		teste = new ArrayList();
		
		teste.add("um");
		teste.add("dois");
		teste.add("três");
		teste.add("quatro");
		
		while(hasNext()) {
			System.out.println(next());
		}
		
		while(hasPrevious()) {
			System.out.println(previous());
		}
		
	}
	
	public static Object next() {

		Object o = teste.get(pos);
		pos++;
		if(pos == teste.size()) pos = teste.size() - 1;
		return o;
	}
	
	public static Object previous() {
		Object o = teste.get(pos);
		pos--;
		if(pos < 0) pos = 0;
		return o;
	}
	
	public static boolean hasNext() {
		if (pos  < teste.size()) {
			return true;
		}
		return false;
	}
	
	public static boolean hasPrevious() {
		if (pos > 0) {
			return true;
		}
		return false;
	}
}
