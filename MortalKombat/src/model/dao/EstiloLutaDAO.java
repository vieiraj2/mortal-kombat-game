package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.entity.EstiloLuta;

public class EstiloLutaDAO {
	
	private Connection con;
	private PreparedStatement pstm;

	public EstiloLutaDAO() {
		this.con = null;
	}
	
	public EstiloLutaDAO(Connection con) {
		this.con = con;
	}
	
	public int insert (EstiloLuta estilo) throws SQLException {
		String sql = "INSERT INTO mortalkombat.estiloluta (Nome_Estilo) VALUES (?);";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, estilo.getNomeEstilo());
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}

	public ArrayList<EstiloLuta> getAll() throws SQLException {
		ArrayList<EstiloLuta> lista = new ArrayList<EstiloLuta>();			
		String sql = "SELECT * FROM mortalkombat.estiloluta;";
		pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next())
			lista.add(new EstiloLuta(Integer.parseInt(rs.getString("idEstilo")), rs.getString("Nome_Estilo")));
		return lista;
	}

	public int delete(int id) throws SQLException {
		String sql = "DELETE FROM mortalkombat.estiloluta WHERE idEstilo = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, id);
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}

	public int update(EstiloLuta _old, EstiloLuta _new) throws SQLException {
		String sql = "UPDATE mortalkombat.estiloluta SET Nome_Estilo=? WHERE idEstilo = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, _new.getNomeEstilo());
		pstm.setInt(2, _old.getIdEstilo());
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}
	
	public DefaultTableModel getData(){
		DefaultTableModel dm = new DefaultTableModel();
		
		dm.addColumn("ID");
		dm.addColumn("Nome");
		
		try {
			String sql = "SELECT * FROM mortalkombat.estiloluta";
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				String id_estilo = rs.getString(1);
				String nome_estilo = rs.getString(2);
				
				dm.addRow(new String []{id_estilo, nome_estilo});
			}
			
			return dm;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;		
	}
}
