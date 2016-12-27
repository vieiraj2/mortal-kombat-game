package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.entity.Arma;

public class ArmaDAO {
	
	private Connection con;
	private PreparedStatement pstm;

	public ArmaDAO() {
		this.con = null;
	}
	
	public ArmaDAO(Connection con) {
		this.con = con;
	}
	
	public int insert (Arma arma) throws SQLException {
		String sql = "INSERT INTO mortalkombat.arma (Nome_Arma, Ataque) VALUES (?,?);";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, arma.getNomeArma());
		pstm.setInt(2, arma.getAtaqueArma());
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}

	public ArrayList<Arma> getAll() throws SQLException {
		ArrayList<Arma> lista = new ArrayList<Arma>();			
		String sql = "SELECT * FROM mortalkombat.arma;";
		pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next())
			lista.add(new Arma(Integer.parseInt(rs.getString("idArma")),rs.getString("Nome_Arma"), Integer.parseInt(rs.getString("Ataque"))));
		return lista;
	}

	public int delete(int id) throws SQLException {
		String sql = "DELETE FROM mortalkombat.arma WHERE idArma = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, id);
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}

	public int update(Arma _old, Arma _new) throws SQLException {
		String sql = "UPDATE mortalkombat.arma SET Nome_Arma=?, Ataque=? WHERE idArma = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, _new.getNomeArma());
		pstm.setInt(2, _new.getAtaqueArma());
		pstm.setInt(3, _old.getIdArma());
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}
	
	public DefaultTableModel getData(){
		DefaultTableModel dm = new DefaultTableModel();
		
		dm.addColumn("ID");
		dm.addColumn("Nome");
		dm.addColumn("Ataque");
		
		try {
			String sql = "SELECT * FROM mortalkombat.arma";
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				String id_arma = rs.getString(1);
				String nome_arma = rs.getString(2);
				String ataque_arma = rs.getString(3);
				
				dm.addRow(new String []{id_arma, nome_arma, ataque_arma});
			}
			
			return dm;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;		
	}
}
