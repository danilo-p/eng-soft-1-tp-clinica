/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Abre uma conexão com o banco de dados.
 *
 * TODO: Receber credenciais do banco de dados através de um arquivo de
 * configuração.
 */
public class ConnectionDatabase {

	private static final String URL_MYSQL = "jdbc:mysql://localhost/clinica";
	private static final String USER = "root";
	private static final String PASS = "";

	public static Connection getConnection() {
		System.out.println("Conectando ao Banco de Dados");
		try {
			return DriverManager.getConnection(URL_MYSQL, USER, PASS);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
