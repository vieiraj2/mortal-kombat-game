package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.entity.Personagem;

public class PersonagemDAO {
	
	private Connection con;
	private PreparedStatement pstm;

	public PersonagemDAO() {
		this.con = null;
	}
	
	public PersonagemDAO(Connection con) {
		this.con = con;
	}

	public int insert (Personagem pers) throws SQLException {
		String sql = "INSERT INTO mortalkombat.personagem (Nome_Per, idMundoP, idClassificacaoP, idArmaP, idEstiloP, idAlinhamentoP) VALUES (?,?,?,?,?,?);";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, pers.getNome());
		pstm.setInt(2, pers.getIdMundo());
		pstm.setInt(3, pers.getIdClassificacao());
		pstm.setInt(4, pers.getIdArma());
		pstm.setInt(5, pers.getIdEstilo());
		pstm.setInt(6, pers.getIdAlinhamento());
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}
	
	public ArrayList<Personagem> getAll() throws SQLException {
		String sql = "SELECT * FROM mortalkombat.personagem;";
		pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		ArrayList<Personagem> lista = new ArrayList<Personagem>();
		while (rs.next())
			lista.add(new Personagem(Integer.parseInt(rs.getString("idPersonagem")),rs.getString("Nome_Per"), Integer.parseInt(rs.getString("idMundoP")), Integer.parseInt(rs.getString("idClassificacaoP")), Integer.parseInt(rs.getString("idArmaP")), Integer.parseInt(rs.getString("idEstiloP")), Integer.parseInt(rs.getString("idAlinhamentoP"))));
		return lista;
	}
	
	public ArrayList<Personagem> getAllByFk(int idM, int idC, int idArm, int idE, int idA) throws SQLException {
		ArrayList<Personagem> lista = new ArrayList<Personagem>();
		String sql = "SELECT * FROM mortalkombat.personagem WHERE idMundoP = ?, idClassificacaoP = ?, idArmaP = ?, idEstiloP = ?, idAlinhamentoP = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, idM);
		pstm.setInt(2, idC);
		pstm.setInt(3, idArm);
		pstm.setInt(4, idE);
		pstm.setInt(5, idA);
		ResultSet rs = pstm.executeQuery();
		while (rs.next())
			lista.add(new Personagem(Integer.parseInt(rs.getString("idPersonagem")),rs.getString("Nome_Per"), Integer.parseInt(rs.getString("idMundoP")), Integer.parseInt(rs.getString("idClassificacaoP")), Integer.parseInt(rs.getString("idArmaP")), Integer.parseInt(rs.getString("idEstiloP")), Integer.parseInt(rs.getString("idAlinhamentoP"))));
		return lista;
	}
	
	public int delete(int id) throws SQLException {
		String sql = "DELETE FROM mortalkombat.personagem WHERE idPersonagem = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, id);
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}

	public int update(Personagem _old, Personagem _new) throws SQLException {
		String sql = "UPDATE mortalkombat.personagem SET Nome_Per = ?, idMundoP = ?, idClassificacaoP = ?, idArmaP = ?, idEstiloP = ?, idAlinhamentoP = ? WHERE idPersonagem = ?;";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, _new.getNome());
		pstm.setInt(2, _new.getIdMundo());
		pstm.setInt(3, _new.getIdClassificacao());
		pstm.setInt(4, _new.getIdArma());
		pstm.setInt(5, _new.getIdEstilo());
		pstm.setInt(6, _new.getIdAlinhamento());
		pstm.setInt(7, _old.getIdPersonagem());
		int res = pstm.executeUpdate();
		pstm.close();
		return res;
	}
	
	public DefaultTableModel getData(){
		
		DefaultTableModel dm = new DefaultTableModel();
		
		dm.addColumn("ID");
		dm.addColumn("Nome");
		dm.addColumn("Mundo");
		dm.addColumn("Classificação");
		dm.addColumn("Arma");
		dm.addColumn("Estilo de Luta");
		dm.addColumn("Alinhamento");
		
		try {
			String sql = "SELECT idPersonagem, Nome_Per, Nome_Mundo, Nome_Cla, Nome_Arma, Nome_Estilo, Nome_Ali FROM  personagem, mundo, classificacao, arma, estiloluta, alinhamento WHERE idMundo = idMundoP AND idClassificacao = idClassificacaoP AND idArma = idArmaP AND idEstilo = idEstiloP AND idAlinhamento = idAlinhamentoP ORDER BY idPersonagem";
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				String id_pers = rs.getString("idPersonagem");
				String nome_pers = rs.getString("Nome_Per");
				String nome_mundo = rs.getString("Nome_Mundo");
				String nome_cla = rs.getString("Nome_Cla");
				String nome_arma = rs.getString("Nome_Arma");
				String nome_estilo = rs.getString("Nome_Estilo");
				String nome_ali = rs.getString("Nome_Ali");
			
				dm.addRow(new String []{id_pers, nome_pers, nome_mundo, nome_cla, nome_arma, nome_estilo, nome_ali});
			}
			
			return dm;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;		
	}
}
