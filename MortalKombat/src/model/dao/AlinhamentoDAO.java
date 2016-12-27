package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.entity.Alinhamento;

public class AlinhamentoDAO {

	private Connection con;
	private PreparedStatement pstm;

	public AlinhamentoDAO() {
		this.con = null;
	}
	
	public AlinhamentoDAO(Connection con) {
		this.con = con;
	}
	
	public int insert (Alinhamento ali) throws SQLException {
		String sql = "INSERT INTO mortalkombat.alinhamento (Nome_Ali) VALUES (?);";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, ali.getNomeAlinhamento());
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}

	public ArrayList<Alinhamento> getAll() throws SQLException {
		ArrayList<Alinhamento> lista = new ArrayList<Alinhamento>();			
		String sql = "SELECT * FROM mortalkombat.alinhamento ORDER BY idAlinhamento;";
		pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next())
			lista.add(new Alinhamento(Integer.parseInt(rs.getString("idAlinhamento")), rs.getString("Nome_Ali")));
		return lista;
	}

	public int delete(int id) throws SQLException {
		String sql = "DELETE FROM mortalkombat.alinhamento WHERE idAlinhamento = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, id);
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}

	public int update(Alinhamento _old, Alinhamento _new) throws SQLException {
		String sql = "UPDATE mortalkombat.alinhamento SET Nome_Ali=? WHERE idAlinhamento = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, _new.getNomeAlinhamento());
		pstm.setInt(2, _old.getIdAlinhamento());
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}
	
	public DefaultTableModel getData(){
		DefaultTableModel dm = new DefaultTableModel();
		
		dm.addColumn("ID");
		dm.addColumn("Nome");
		
		try {
			String sql = "SELECT * FROM mortalkombat.alinhamento ORDER BY idAlinhamento";
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				String id_alinhamento = rs.getString(1);
				String nome_alinhamento = rs.getString(2);
				
				dm.addRow(new String []{id_alinhamento, nome_alinhamento});
			}
			
			return dm;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;		
	}
}
