package br.arthur.temp.tests;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import br.arthur.entities.Group;
import br.arthur.entities.Permission;
import br.arthur.entities.Status;
import br.arthur.entities.User;
import br.arthur.models.UserModel;
import br.arthur.utils.HibernateUtil;
import br.arthur.utils.PasswordUtil;

public class TesttUser {
	public static void main(String[] args) {
		insertOneUserAndHisDependencies();		
		UserModel u = new UserModel();
		User findU = u.findOneWhere("email", "'arthur@teste.com'");
		
		System.out.println(findU.getUser());
	}

	private static void insertOneUserAndHisDependencies() {
		Timestamp d = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Set<Permission> perms = new HashSet();
		
		Permission perm = new Permission("fazer algo");
		Group g = new Group("admin");
		Status s = new Status("ativo");
		User u = new User();
		
		
		perms.add(perm);
		
		g.setPermissions(perms);
		
		u.setName("Arthur S. Costa");
		u.setEmail("arthur@teste.com");
		String pass = "artdna";
		String saltUser = PasswordUtil.generateSalt();
		String hashUser = PasswordUtil.generateHash(pass, saltUser);
		u.setSalt(saltUser);
		u.setHash(hashUser);
		u.setStatus(s);
		u.setGroup(g);
		u.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		session.beginTransaction();
		
		session.save(perm);
		session.save(g);
		session.save(s);
		session.save(u);
		
		session.getTransaction().commit();
	}
}
