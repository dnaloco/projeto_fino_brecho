package br.arthur.models;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.Entrada;
import br.arthur.entities.Imagem;
import br.arthur.entities.Marca;
import br.arthur.entities.HeaderEntrada;
import br.arthur.entities.Saida;
import br.arthur.entities.Situacao;
import br.arthur.entities.Tipo;
import br.arthur.utils.HibernateUtil;

public class EntradaModel {
	private Entrada entity;
	private static Session session;

	public static void close() {
		session.close();
	}
	
}
