package br.arthur.temp.tests;

import br.arthur.entities.FormaPagto;
import br.arthur.models.FormaPagtoModel;

public class AnyTest2 {

	public static void main(String[] args) {
		FormaPagtoModel fpm = new FormaPagtoModel();
		
		FormaPagto fp = fpm.findOneWhere("name", "'Dinheiro'");
		
		System.out.println(fp.getName());
		
	}

}
;