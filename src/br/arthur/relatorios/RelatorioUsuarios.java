package br.arthur.relatorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.arthur.entities.User;
import br.arthur.models.UserModel;
import br.arthur.utils.RelatorioUtil;

public class RelatorioUsuarios {	
	public static void gerarTodos() {		
		UserModel um = new UserModel();
		List users = um.findAll();
		List dataReport = new ArrayList();
		Map<String, String> theUser;
		for (Object o : users) {
			User u = (User) o;
			theUser = new HashMap<>();
			theUser.put("id", String.valueOf(u.getId()));
			theUser.put("user", u.getUser());
			theUser.put("name", u.getName());
			theUser.put("email", u.getEmail());
			theUser.put("status", u.getStatus().getName());
			theUser.put("group", u.getGroup().getName());
			theUser.put("mobile", u.getMobile());
			theUser.put("phone", u.getPhone());
			dataReport.add(theUser);
		}
		

		
		boolean isCreated = RelatorioUtil.gerarRelatorio(dataReport, "RelatorioUsuarios");
		
		if(isCreated) {
			JOptionPane.showMessageDialog(null, "Relatório criado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório. Verifique se o arquivo está aberto.");
		}
	}
}
