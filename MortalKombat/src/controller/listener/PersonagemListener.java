package controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import controller.AcessoBD;
import static view.PersonagemView.*;
import model.dao.*;
import model.entity.Alinhamento;
import model.entity.Arma;
import model.entity.Classificacao;
import model.entity.EstiloLuta;
import model.entity.Mundo;
import model.entity.Personagem;
import view.LoginView;
import view.PersonagemView;
import view.TelaInicialAdminView;

public class PersonagemListener implements ActionListener{
	
	private static PersonagemDAO daoPersonagem;
	private static MundoDAO daoMundo;
	private static ClassificacaoDAO daoCla;
	private static ArmaDAO daoArma;
	private static EstiloLutaDAO daoEstilo;
	private static AlinhamentoDAO daoAli;
	private static AcessoBD dbman;
	
	public void actionPerformed(ActionEvent e){
		
		dbman = new AcessoBD();
		daoPersonagem = new PersonagemDAO(dbman.getCon());	
		daoMundo = new MundoDAO(dbman.getCon());
		daoCla = new ClassificacaoDAO(dbman.getCon());
		daoArma = new ArmaDAO(dbman.getCon());
		daoEstilo = new EstiloLutaDAO(dbman.getCon());
		daoAli = new AlinhamentoDAO(dbman.getCon());
		
		if(e.getActionCommand().equals("Relatório")){
			gerarRelatorio();
		}
		
		if(e.getActionCommand().equals("CRUD")){
			PersonagemView pv;
			try {
				pv = new PersonagemView();
				pv.setVisible(true);
				pv.setResizable(false);
				pv.setLocationRelativeTo(null);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand().equals("Carregar personagens")){
			loadData();
		}
		
		if(e.getActionCommand().equals("Cadastrar")){
			try {
				String nomeMundo_ = comboBox_EscolhaMundo.getSelectedItem().toString();
				String nomeCla_ = comboBox_EscolhaCla.getSelectedItem().toString();
				String nomeArma_ = comboBox_EscolhaArma1.getSelectedItem().toString();
				String nomeEstilo_ = comboBox_EscolhaEstilo.getSelectedItem().toString();
				String nomeAli_ = comboBox_EscolhaAli.getSelectedItem().toString();
				int idMundo_ = 0;
				int idCla_ = 0;
				int idArma_ = 0;
				int idEstilo_ = 0;
				int idAli_ = 0;
				
				List<Mundo> listMundo = daoMundo.getAll();
				
				for(Mundo mun : listMundo){
					if(mun.getNomeMundo().equals(nomeMundo_)){
						idMundo_ = mun.getIdMundo();
					}
				}
				
				List<Classificacao> listClas = daoCla.getAll();
				
				for(Classificacao clas : listClas){
					if(clas.getNomeClassificacao().equals(nomeCla_)){
						idCla_ = clas.getIdClassificacao();
					}
				}
				
				List<Arma> listArma = daoArma.getAll();
				
				for(Arma arm : listArma){
					if(arm.getNomeArma().equals(nomeArma_)){
						idArma_ = arm.getIdArma();
					}
				}
				
				List<EstiloLuta> listEst = daoEstilo.getAll();
				
				for(EstiloLuta est : listEst){
					if(est.getNomeEstilo().equals(nomeEstilo_)){
						idEstilo_ = est.getIdEstilo();
					}
				}
				
				List<Alinhamento> listAli = daoAli.getAll();
				
				for(Alinhamento ali : listAli){
					if(ali.getNomeAlinhamento().equals(nomeAli_)){
						idAli_ = ali.getIdAlinhamento();
					}
				}
				
				Personagem novoPers = new Personagem(-1, NomePer.getText() ,idMundo_, idCla_, idArma_, idEstilo_, idAli_);				
				daoPersonagem.insert(novoPers);
				JOptionPane.showMessageDialog(null, "Personagem '" + novoPers.getNome() + "' inserido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				
				try {
					List<Personagem> arrPer = daoPersonagem.getAll();
					comboBox_SelectPersonagem.setModel(new DefaultComboBoxModel(arrPer.toArray()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			catch (NullPointerException | NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Falha ao inserir o personagem.\nVerifique os campos e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
			loadData();
			comboBox_EscolhaMundo.setSelectedItem(null);
			comboBox_EscolhaCla.setSelectedItem(null);
			comboBox_EscolhaArma1.setSelectedItem(null);
			comboBox_EscolhaEstilo.setSelectedItem(null);
			comboBox_EscolhaAli.setSelectedItem(null);
			NomePer.setText("");
			CodigoPer.setText("");
			NomePer.requestFocus();
		}
		
		if(e.getActionCommand().equals("Editar")){
			try {
				String nomeMundo = comboBox_EscolhaMundo.getSelectedItem().toString();
				String nomeCla = comboBox_EscolhaCla.getSelectedItem().toString();
				String nomeArma = comboBox_EscolhaArma1.getSelectedItem().toString();
				String nomeEstilo = comboBox_EscolhaEstilo.getSelectedItem().toString();
				String nomeAli = comboBox_EscolhaAli.getSelectedItem().toString();
				int idMundo = 0;
				int idCla = 0;
				int idArma = 0;
				int idEstilo = 0;
				int idAli = 0;
				
				List<Mundo> listMundo = daoMundo.getAll();
				
				for(Mundo mun : listMundo){
					if(mun.getNomeMundo().equals(nomeMundo)){
						idMundo = mun.getIdMundo();
					}
				}
				
				List<Classificacao> listClas = daoCla.getAll();
				
				for(Classificacao clas : listClas){
					if(clas.getNomeClassificacao().equals(nomeCla)){
						idCla = clas.getIdClassificacao();
					}
				}
				
				List<Arma> listArma = daoArma.getAll();
				
				for(Arma arm : listArma){
					if(arm.getNomeArma().equals(nomeArma)){
						idArma = arm.getIdArma();
					}
				}
				
				List<EstiloLuta> listEst = daoEstilo.getAll();
				
				for(EstiloLuta est : listEst){
					if(est.getNomeEstilo().equals(nomeEstilo)){
						idEstilo = est.getIdEstilo();
					}
				}
				
				List<Alinhamento> listAli = daoAli.getAll();
				
				for(Alinhamento ali : listAli){
					if(ali.getNomeAlinhamento().equals(nomeAli)){
						idAli = ali.getIdAlinhamento();
					}
				}
				
				Personagem velhoPersonagem = (Personagem) comboBox_SelectPersonagem.getSelectedItem();
				Personagem novoPersonagem = new Personagem(null, NomePer.getText() ,idMundo, idCla, idArma, idEstilo, idAli);
				int lastSelectedIndex = comboBox_SelectPersonagem.getSelectedIndex();
				daoPersonagem.update(velhoPersonagem, novoPersonagem);
				JOptionPane.showMessageDialog(null, "Personagem '" + velhoPersonagem.getNome() + "' editado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				ArrayList<Personagem> arrPer = daoPersonagem.getAll();
				comboBox_SelectPersonagem.setModel(new DefaultComboBoxModel(arrPer.toArray()));
				comboBox_SelectPersonagem.setSelectedIndex(lastSelectedIndex);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}catch(NullPointerException | NumberFormatException e2){
				JOptionPane.showMessageDialog(null, "Falha ao editar o personagem.\nVerifique os campos e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
			loadData();
			comboBox_EscolhaMundo.setSelectedItem(null);
			comboBox_EscolhaCla.setSelectedItem(null);
			comboBox_EscolhaArma1.setSelectedItem(null);
			comboBox_EscolhaEstilo.setSelectedItem(null);
			comboBox_EscolhaAli.setSelectedItem(null);
		}
		
		if(e.getActionCommand().equals("Remover")){	
				try {
					String nomePer = comboBox_SelectPersonagem.getSelectedItem().toString();
					int id = 0;
					
					List<Personagem> listaPer = daoPersonagem.getAll();
					
					for (Personagem per : listaPer) {
						if(per.getNome().equals(nomePer)){
							id = per.getIdPersonagem();		
							JOptionPane.showMessageDialog(null, "Personagem '" + per.getNome() + "' removido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
						}	
					}
					
					daoPersonagem.delete(id);
					ArrayList<Personagem> arrPer = daoPersonagem.getAll();
					comboBox_SelectPersonagem.setModel(new DefaultComboBoxModel(arrPer.toArray()));
					
					if(comboBox_SelectPersonagem.getItemCount()==0) {
						JOptionPane.showMessageDialog(null, "Não há personagenss a serem editados.", "Informação", JOptionPane.INFORMATION_MESSAGE);
					}				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				loadData();
				comboBox_EscolhaMundo.setSelectedItem(null);
				comboBox_EscolhaCla.setSelectedItem(null);
				comboBox_EscolhaArma1.setSelectedItem(null);
				comboBox_EscolhaEstilo.setSelectedItem(null);
				comboBox_EscolhaAli.setSelectedItem(null);
				NomePer.setText("");
				CodigoPer.setText("");
				NomePer.requestFocus();
			}
		
		if(e.getActionCommand().equals("Limpar")){
			comboBox_SelectPersonagem.setSelectedItem(null);
			comboBox_EscolhaMundo.setSelectedItem(null);
			comboBox_EscolhaCla.setSelectedItem(null);
			comboBox_EscolhaArma1.setSelectedItem(null);
			comboBox_EscolhaEstilo.setSelectedItem(null);
			comboBox_EscolhaAli.setSelectedItem(null);
			NomePer.setText("");
			CodigoPer.setText("");
			NomePer.requestFocus();
		}
	}
		
		private void gerarRelatorio() {
			try {
				ArrayList<Personagem> listaPer;

				JFrame parentFrame = new JFrame();
				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Arquivos de texto (*.txt)", "txt");
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Salvar Relatório");
				fileChooser.addChoosableFileFilter(txtFilter);
				fileChooser.setFileFilter(txtFilter);
				
				
				int userSelection = fileChooser.showSaveDialog(parentFrame);
				
				if (userSelection == JFileChooser.APPROVE_OPTION) {

					File selectedFile = fileChooser.getSelectedFile();
					String finalPath = selectedFile.getAbsolutePath().endsWith(".txt") ? selectedFile.getAbsolutePath() : selectedFile.getAbsolutePath() + ".txt";
					File fileToSave = new File(finalPath);
					System.out.println("Salvando arquivo: " + finalPath);
					FileWriter fw = new FileWriter(fileToSave);
					
					listaPer = daoPersonagem.getAll();
					if (listaPer.isEmpty())
						fw.append("\t<Nenhum alinhamento cadastrado.>\r\r");
					else {
						for (Personagem per : listaPer) {
									fw.append(per.toLongString().replace("\n\n", "\r"));
									fw.append("\r");
								}
							}
							fw.close();
						}
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		private void loadData(){
			DefaultTableModel dm = new PersonagemDAO(dbman.getCon()).getData();
			table_ViewPersonagem.setModel(dm);
		}
}
	