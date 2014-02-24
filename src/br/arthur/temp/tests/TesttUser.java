package br.arthur.temp.tests;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;

import br.arthur.entities.Group;
import br.arthur.entities.Permission;
import br.arthur.entities.Status;
import br.arthur.entities.User;
import br.arthur.utils.HibernateUtil;
import br.arthur.utils.PasswordUtil;

public class TesttUser {
	public static void main(String[] args) {
		//System.out.println(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date myDate = new Date(stamp.getTime());
		
		String dataStr = new SimpleDateFormat("dd/MM/yyyy").format(myDate);

		
		Pattern p = Pattern.compile("(^[0-9]+).*");
		Matcher m = p.matcher("40 %");
		
		m.find();
		
		int it = Integer.parseInt(m.group(1));
		System.out.println(it);

		if (m.find()) {
		    System.out.println(m.group(1));
		}
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
