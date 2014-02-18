package br.arthur.temp.tests;

import java.util.HashSet;

import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.Group;
import br.arthur.entities.Marca;
import br.arthur.entities.Permission;
import br.arthur.entities.Status;
import br.arthur.entities.Tipo;
import br.arthur.utils.HibernateUtil;

public class PopulateDefault {
	public static void main(String[] args) {
		populateToUsers();
		populateToProdutos();
	}
	
	private static void populateToProdutos() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String[] tiposStr = {
				"novo",
				"semi novo",
				"usado",
				"de época",
				"artesanal",
				"raro"
		};
		
		String[] marcasStr = {
				"prada",
				"gucci",
				"giorgio armani",
				"versace",
				"louis vuitton",
				"fendi",
				"channel",
				"rolex",
				"tom ford",
				"emporio armani",
				"armani exchange",
				"calvin klein",
				"tommy hilfiger",
				"ralph lauren",
				"lacoste",
				"schon",
				"john",
				"donna karan's",
				"valentino",
				"YSL",
				"ferragamo",
				"halston",
				"fiorucci",
				"pucci",
				"gianni",
				"kenneth cole",
				"michael kors",
				"D&G",
				"BCBG",
				"alexandre herchcovitch",
				"oscar de la renta",
				"cavallera",
				"brooksfield",
				"dior",
				"dolce & gabbana",
				"billabong",
				"maresia",
				"quick silver",
				"7 for all mankind",
				"ecko",
				"eclectic",
				"ellus",
				"empress brasil",
				"hang loose",
				"lee",
				"levis"
		};
		
		String[] catsStr = {
				"calçados",
				"acessório",
				"bandana",
				"quimono",
				"farda",
				"gravata",
				"cinto",
				"gravata borboleta",
				"calcinha",
				"calça",
				"camisa",
				"camiseta",
				"corpete",
				"saia",
				"termo",
				"toga",
				"sombrero",
				"sobretudo",
				"schort",
				"jeans",
				"lingerie",
				"pijama",
				"vestido",
				"minissaia",
				"meia",
				"biquini",
				"maiô",
				"blusa",
				"legging",
				"regata",
				"top",
				"macaquinho",
				"polo",
				"moletom",
				"casaco",
				"jaqueta",
				"livro",
				"tenis",
				"sapato",
				"salto"
		};
		
		session.beginTransaction();
		
		for(String tipo : tiposStr) {
			Tipo t = new Tipo(tipo);
			session.save(t);
		}
		
		for(String marca : marcasStr) {
			Marca m = new Marca(marca);
			session.save(m);
		}
		
		for(String cat : catsStr) {
			Categoria c = new Categoria(cat);
			session.save(c);
		}

		session.getTransaction().commit();
		session.close();
	}

	private static void populateToUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String[] permStr = {
				"criar produto",
				"atualizar produto",
				"excluir produto",
				"visualizar produto",
				
				"criar consignatário",
				"atualizar consignatário",
				"excluir consignatário",
				"visualizar consignatário",
				
				"criar usuário",
				"atualizar usuário",
				"excluir usuário",
				"visualizar usuário",
				
				"atualizar grupo",
				"visualizar grupo",
				
				"fazer backup"
			};
		
		String[] groupsStr = {
				"gerente",
				"vendedor"
			};
			
		String[] statusStr = {
				"ativo",
				"inativo"
			};

		HashSet<Permission> perms = new HashSet();

		session.beginTransaction();

		for(String p : permStr) {
			Permission perm = new Permission(p);
			session.save(perm);

			perms.add(perm);
		}

		for(String g : groupsStr) {
			Group group = new Group(g);
			group.setPermissions(perms);
			session.save(group);
		}

		for(String s : statusStr) {
			Status status = new Status(s);
			session.save(status);
		}

		session.getTransaction().commit();
		session.close();
	}
}
