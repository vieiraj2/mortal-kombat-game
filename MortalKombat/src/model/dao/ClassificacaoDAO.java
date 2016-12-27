package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.entity.Classificacao;

public class ClassificacaoDAO {
	
	private Connection con;
	private PreparedStatement pstm;

	public ClassificacaoDAO() {
		this.con = null;
	}
	
	public ClassificacaoDAO(Connection con) {
		this.con = con;
	}
	
	public int insert (Classificacao cla) throws SQLException {
		String sql = "INSERT INTO mortalkombat.classificacao (Nome_Cla) VALUES (?);";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, cla.getNomeClassificacao());
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}

	public ArrayList<Classificacao> getAll() throws SQLException {
		ArrayList<Classificacao> lista = new ArrayList<Classificacao>();			
		String sql = "SELECT * FROM mortalkombat.classificacao;";
		pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next())
			lista.add(new Classificacao(Integer.parseInt(rs.getString("idClassificacao")), rs.getString("Nome_Cla")));
		return lista;
	}

	public int delete(int id) throws SQLException {
		String sql = "DELETE FROM mortalkombat.classificacao WHERE idClassificacao = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, id);
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}

	public int update(Classificacao _old, Classificacao _new) throws SQLException {
		String sql = "UPDATE mortalkombat.classificacao SET Nome_Cla=? WHERE idClassificacao = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, _new.getNomeClassificacao());
		pstm.setInt(2, _old.getIdClassificacao());
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}
	
	public DefaultTableModel getData(){
		DefaultTableModel dm = new DefaultTableModel();
		
		dm.addColumn("ID");
		dm.addColumn("Nome");
		
		try {
			String sql = "SELECT * FROM mortalkombat.classificacao";
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				String id_cla = rs.getString(1);
				String nome_cla = rs.getString(2);
				
				dm.addRow(new String []{id_cla, nome_cla});
			}
			
			return dm;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;		
	}
}
