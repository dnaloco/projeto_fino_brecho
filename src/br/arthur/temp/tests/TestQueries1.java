package br.arthur.temp.tests;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import br.arthur.entities.Group;
import br.arthur.models.GroupModel;

public class TestQueries1 {
	public static void main(String[] args) {
		GroupModel gm = new GroupModel();
		
		Iterator groups = gm.findAll().iterator();
		Group g = gm.findOneWhere("id", "1");
		
		while(groups.hasNext()) {
			Group gr = (Group) groups.next();
			System.out.println("Id: " + gr.getId() + " | Status: " + gr.getName());
		}
		
		System.out.println(g.getName());
	}
}
