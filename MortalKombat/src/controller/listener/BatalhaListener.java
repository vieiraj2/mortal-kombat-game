package controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import controller.AcessoBD;
import javazoom.jl.player.Player;
import model.dao.ArmaDAO;
import model.dao.PersonagemDAO;
import model.entity.Arma;
import model.entity.Personagem;
import view.BatalhaView;
import static view.BatalhaView.*;

public class BatalhaListener implements ActionListener {
	
	private static AcessoBD db;
	private static PersonagemDAO daoPer;
	private static ArmaDAO daoArma;
	
	public void actionPerformed(ActionEvent e){
		
		db = new AcessoBD();
		daoPer = new PersonagemDAO(db.getCon());
		daoArma = new ArmaDAO(db.getCon());
		
		
		if(e.getActionCommand().equals("Simular")){
			BatalhaView bv;
			try {
				bv = new BatalhaView();
				bv.setVisible(true);
				bv.setResizable(false);
				bv.setLocationRelativeTo(null);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand().equals("Carregar personagens")){
			loadData();		
		}
		
		if(e.getActionCommand().equals("Fight")){
			try {
				String nome_per1 = comboBox_Per1.getSelectedItem().toString();
				String nome_per2 = comboBox_Per2.getSelectedItem().toString();
				int idArma1 = 0;
				int ataque1 = 0;
				int idArma2 = 0;
				int ataque2 = 0;
				
				List<Personagem> lista_per = daoPer.getAll();
				List<Arma> lista_arma = daoArma.getAll();
				
				for(Personagem per1 : lista_per){
					if(per1.getNome().equals(nome_per1)){
						idArma1 = per1.getIdArma();
					}
				}
				
				for(Arma arma1 : lista_arma){
					if(arma1.getIdArma().equals(idArma1)){
						ataque1 = arma1.getAtaqueArma();
					}
				}
				
				for(Personagem per2 : lista_per){
					if(per2.getNome().equals(nome_per2)){
						idArma2 = per2.getIdArma();
					}
				}
				
				for(Arma arma2 : lista_arma){
					if(arma2.getIdArma().equals(idArma2)){
						ataque2 = arma2.getAtaqueArma();
					}
				}
				
				if(nome_per1 == nome_per2){
					JOptionPane.showMessageDialog(null, "Selecione personagens diferentes.", "Erro.", JOptionPane.INFORMATION_MESSAGE);
				}else if(nome_per1 == null && nome_per2 == null){
					JOptionPane.showMessageDialog(null, "Selecione personagens para batalhar.", "Erro.", JOptionPane.INFORMATION_MESSAGE);
				}else if(ataque1>ataque2){
					music();
					JOptionPane.showMessageDialog(null, "Personagem '"+nome_per1+"' / Ataque '"+ataque1+"' venceu.\nPersonagem '"+nome_per2+"' / Ataque '"+ataque2+"' perdeu.", "Resultado de batalha", JOptionPane.INFORMATION_MESSAGE);
				}else if(ataque2>ataque1){
					music();
					JOptionPane.showMessageDialog(null, "Personagem '"+nome_per2+"' / Ataque '"+ataque2+"' venceu.\nPersonagem '"+nome_per1+"' / Ataque '"+ataque1+"' perdeu.", "Resultado de batalha", JOptionPane.INFORMATION_MESSAGE);
				}else if(ataque1 == ataque2){
					JOptionPane.showMessageDialog(null, "O ataque total dos personagens possuim mesmo valor.\nSelecione personagens diferentes para simular novamente.", "Resultado de batalha", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void music(){
		try{
			FileInputStream fis = new FileInputStream("C:\\Users\\José Vieira\\Documents\\workspace\\MortalKombat\\fight.mp3");
			Player playMp3 = new Player(fis);
			playMp3.play();
		}catch(Exception e1){
			System.out.println(e1);
		}
	}
	
	private void loadData(){
		db = new AcessoBD();
		DefaultTableModel dm = new PersonagemDAO(db.getCon()).getData();
		table_SelectPers.setModel(dm);
	}

}
