package br.arthur.temp.tests;


import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class anyTeste01 {
	public static void main(String[] args) {
		Date tstamp = new Date();
		long teste = tstamp.getTime();
		
		long id = Long.parseLong(String.valueOf(tstamp.getTime()).concat("1"));
		System.out.println(id);

	}
}
