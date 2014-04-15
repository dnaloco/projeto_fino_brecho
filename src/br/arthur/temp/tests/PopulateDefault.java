package br.arthur.temp.tests;

import java.util.HashSet;

import org.hibernate.Session;

import br.arthur.entities.CatLastId;
import br.arthur.entities.Categoria;
import br.arthur.entities.Cor;
import br.arthur.entities.Estado;
import br.arthur.entities.FormaPagto;
import br.arthur.entities.Group;
import br.arthur.entities.Marca;
import br.arthur.entities.Permission;
import br.arthur.entities.Situacao;
import br.arthur.entities.Status;
import br.arthur.entities.Tamanho;
import br.arthur.entities.Tipo;
import br.arthur.utils.HibernateUtil;

public class PopulateDefault {
	public static void main(String[] args) {
		populateToUsers();
		populateToConsigs();
		populateToProdutos();
	}
	
	private static void populateToConsigs() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String[] UFs = {
				"RO",
				"AC",
				"AM",
				"RR",
				"PA",
				"AP",
				"TO",
				"MA",
				"PI",
				"CE",
				"RN",
				"PB",
				"PE",
				"AL",
				"SE",
				"BA",
				"MG",
				"ES",
				"RJ",
				"SP",
				"PR",
				"SC",
				"RS",
				"MS",
				"MT",
				"GO",
				"DF"
		};

		session.beginTransaction();
		
		for(String uf : UFs) {
			Estado e = new Estado(uf);
			session.save(e);
		}
		
		session.getTransaction().commit();
		session.close();
	}

	private static void populateToProdutos() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String[] situacoesStr = {
			"avaliando",
			"dispon�vel"
		};
		
		String[] tiposStr = {
				"novo",
				"semi novo",
				"usado",
				"de �poca",
				"artesanal",
				"raro"
		};
		
		String[] marcasStr = {
				"selecionar",
				"gen�rica",
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
				"selecionar",
				"gen�rica",
				"cal�ados",
				"acess�rio",
				"bandana",
				"quimono",
				"farda",
				"gravata",
				"cinto",
				"gravata borboleta",
				"calcinha",
				"cal�a",
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
				"mai�",
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
		
		String [] formasPagto = {
				"dinheiro",
				"d�bido",
				"cr�dito"
		};

		String [] cores = {
				"selecionar",
				"gen�rica",
				"azul",
				"amarelo",
				"verde",
				"vermelho"
		};
		
		String [] tamanhos = {
				"selecionar",
				"gen�rica",
				"30",
				"32",
				"34",
				"36",
				"37",
				"38",
				"39",
				"40",
				"41",
				"42",
				"PP",
				"P",
				"M",
				"G",
				"GG"
		};
		
		session.beginTransaction();
		
		for(String situacao : situacoesStr) {
			Situacao s = new Situacao(situacao);
			session.save(s);
		}
		
		for(String tipo : tiposStr) {
			Tipo t = new Tipo(tipo);
			session.save(t);
		}
		
		for(String marca : marcasStr) {
			Marca m = new Marca(marca);
			session.save(m);
		}
		
		for(String cor : cores) {
			Cor c = new Cor(cor);
			session.save(c);
		}
		
		for(String tamanho : tamanhos) {
			Tamanho t = new Tamanho(tamanho);
			session.save(t);
		}
		
		for(String cat : catsStr) {
			Categoria c = new Categoria(cat, true);
			session.save(c);
			CatLastId clid = new CatLastId(c.getId(), 1);
			session.save(clid);
		}
		
		for(String fp : formasPagto) {
			FormaPagto f = new FormaPagto(fp);
			session.save(f);
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
				
				"criar consignat�rio",
				"atualizar consignat�rio",
				"excluir consignat�rio",
				"visualizar consignat�rio",
				
				"criar usu�rio",
				"atualizar usu�rio",
				"excluir usu�rio",
				"visualizar usu�rio",
				
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
