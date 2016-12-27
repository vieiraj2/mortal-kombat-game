package controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;

public class AcessoBD {
	
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Statement getStm() {
		return stm;
	}

	public void setStm(Statement stm) {
		this.stm = stm;
	}

	public PreparedStatement getPstm() {
		return pstm;
	}

	public void setPstm(PreparedStatement pstm) {
		this.pstm = pstm;
	}

	public AcessoBD() {
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mortalkombat";
			this.con = DriverManager.getConnection(url, "root", "root");
			System.out.println("Conexão com o banco de dados efetuada com sucesso.");
			System.out.println("URL: " + url);
			System.out.println("Connection: " + con);
		}
		catch (Exception e) { 
			System.out.println("Erro ao abrir conexão com o banco de dados.");
			e.printStackTrace();			
		}
	}
	
	public void FecharBD(){
		try { 
			con.close();
			System.out.println("Conexão finalizada com sucesso.");
		}
		catch (Exception e) { 
			System.out.println("Erro ao fechar conexão com o banco de dados.");
		}
	}
}
