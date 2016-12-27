package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Usuario;

public class UsuarioDAO {
	
	private Connection con;
	private PreparedStatement pstm;

	public UsuarioDAO() {
		this.con = null;
	}
	
	public UsuarioDAO(Connection con) {
		this.con = con;
	}

	public ArrayList<Usuario> getAll() throws SQLException {
		String sql = "SELECT * FROM usuario;";
		pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		while (rs.next())
			lista.add(new Usuario(Integer.parseInt(rs.getString("idUsuario")), rs.getString("Login"), rs.getString("Senha"), rs.getString("Acesso")));
		return lista;
	}
}
