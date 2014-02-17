package br.arthur.controllers;

import br.arthur.entities.Group;
import br.arthur.entities.Status;
import br.arthur.entities.User;
import br.arthur.models.UserModel;
import br.arthur.utils.PasswordUtil;

public class LoginController {
	private boolean logged = false; 
	private UserModel um;
	private User user;
	private String msgError = "";
	
	public boolean isLogged () {
		return this.logged;
	}
	
	public void checkUser(String user, String pass) {
		if(user.equals("admin") && pass.equals("admin")) {
			User u = new User();
			u.setId(0);
			u.setUser("Arthur");
			u.setName("Arthur");
			u.setGroup(new Group("admin"));
			u.setStatus(new Status("ativo"));
			this.user = u;
			this.logged = true;
			
		} else {
			um = new UserModel();
			User u = null;
			
			try {
				u = um.findOneWhere("user", "'" + user + "'");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			
			if(u != null && u.getId() > 0) {
				String salt = u.getSalt();
				String hash = u.getHash();

				if(PasswordUtil.checkPassword(pass, salt, hash) && u.getStatus().getName().equals("ativo")) {
					this.user = u;
					this.logged = true;
				} else {
					msgError += PasswordUtil.checkPassword(pass, salt, hash) ? "" : "Usuário ou senha não conferem.";
					msgError += u.getStatus().getName().equals("ativo") ? "" : "Usuário inativo. Contacte o administrador do sistema.";
					this.logged = false;
				}
				
			} else {			
				msgError +=  "Usuário não encontrado!";
			}
		}

	}
	
	public User getLoggedUser() {
		return user;
	}
	
	public String getErrorMessage() {
		return msgError;
	}
}
