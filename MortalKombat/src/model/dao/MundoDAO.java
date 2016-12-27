package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.entity.Mundo;

public class MundoDAO {
	
	private Connection con;
	private PreparedStatement pstm;

	public MundoDAO() {
		this.con = null;
	}
	
	public MundoDAO(Connection con) {
		this.con = con;
	}
	
	public int insert (Mundo mundo) throws SQLException {
		String sql = "INSERT INTO mortalkombat.mundo (Nome_Mundo) VALUES (?);";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, mundo.getNomeMundo());
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}

	public ArrayList<Mundo> getAll() throws SQLException {
		ArrayList<Mundo> lista = new ArrayList<Mundo>();			
		String sql = "SELECT * FROM mortalkombat.mundo;";
		pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next())
			lista.add(new Mundo(Integer.parseInt(rs.getString("idMundo")), rs.getString("Nome_Mundo")));
		return lista;
	}

	public int delete(int id) throws SQLException {
		String sql = "DELETE FROM mortalkombat.mundo WHERE idMundo = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, id);
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}

	public int update(Mundo _old, Mundo _new) throws SQLException {
		String sql = "UPDATE mortalkombat.mundo SET Nome_Mundo=? WHERE idMundo = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, _new.getNomeMundo());
		pstm.setInt(2, _old.getIdMundo());
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}
	
	public DefaultTableModel getData(){
		DefaultTableModel dm = new DefaultTableModel();
		
		dm.addColumn("ID");
		dm.addColumn("Nome");
		
		try {
			String sql = "SELECT * FROM mortalkombat.mundo";
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				String id_mundo = rs.getString(1);
				String nome_mundo = rs.getString(2);
				
				dm.addRow(new String []{id_mundo, nome_mundo});
			}
			
			return dm;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;		
	}
}
