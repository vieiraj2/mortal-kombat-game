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
import static view.MundoView.table_ViewMundo;
import static view.MundoView.*;
import view.MundoView;
import model.dao.MundoDAO;
import model.entity.Mundo;

public class MundoListener implements ActionListener{

	private static MundoDAO daoMundo;
	private static AcessoBD dbman;

	public void actionPerformed(ActionEvent e){
		
		dbman = new AcessoBD();
		daoMundo = new MundoDAO(dbman.getCon());
		
		if(e.getActionCommand().equals("Relatório")){
			gerarRelatorio();
		}
		
		if(e.getActionCommand().equals("CRUD")){
			MundoView mv;
			try {
				mv = new MundoView();
				mv.setVisible(true);
				mv.setResizable(false);
				mv.setLocationRelativeTo(null);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		if(e.getActionCommand().equals("Carregar mundos")){
			loadData();
		}
		
		if(e.getActionCommand().equals("Cadastrar")){
			try { 
				Mundo novoMundo = new Mundo(-1, NomeMundo.getText());
				String n = NomeMundo.getText();
				if(n.equals("")){
					JOptionPane.showMessageDialog(null, "Erro. Campo 'Nome' vazio.");
				}else{
					daoMundo.insert(novoMundo);
					JOptionPane.showMessageDialog(null, "Mundo '" + novoMundo.getNomeMundo() + "' inserido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				
				try {
					comboBox_SelectMundo.setModel(new DefaultComboBoxModel(daoMundo.getAll().toArray()));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (NullPointerException | NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Falha ao inserir o mundo.\nVerifique os campos e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
			loadData();
			NomeMundo.setText("");
			CodigoMundo.setText("");
			NomeMundo.requestFocus();
		}
	
		if(e.getActionCommand().equals("Remover")){	
			try {
				String nome = comboBox_SelectMundo.getSelectedItem().toString();
				int id = 0;
				
				List<Mundo> listal = daoMundo.getAll();
				
				for (Mundo mundo : listal) {
					if(mundo.getNomeMundo().equals(nome)){
						id = mundo.getIdMundo();
						JOptionPane.showMessageDialog(null, "Mundo '" + mundo.getNomeMundo() + "' removido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					}	
				}
				
				daoMundo.delete(id);
				ArrayList<Mundo> arrMundo = daoMundo.getAll();
				comboBox_SelectMundo.setModel(new DefaultComboBoxModel(arrMundo.toArray()));
				
				if(comboBox_SelectMundo.getItemCount()==0) {
					JOptionPane.showMessageDialog(null, "Não há mundos a serem editados.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				}				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			loadData();
			NomeMundo.setText("");
			CodigoMundo.setText("");
			NomeMundo.requestFocus();
		}
		
		if(e.getActionCommand().equals("Editar")){	
			try {			
				Mundo velhoMundo = (Mundo)comboBox_SelectMundo.getSelectedItem();
				Mundo novoMundo = new Mundo(null, NomeMundo.getText());
				String n = NomeMundo.getText();
				if(n.equals("")){
					JOptionPane.showMessageDialog(null, "Erro. Campo 'Nome' vazio.");
				}else{
					daoMundo.update(velhoMundo, novoMundo);
					JOptionPane.showMessageDialog(null, "Mundo '" + velhoMundo.getNomeMundo() + "' editado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				int lastSelectedIndex = comboBox_SelectMundo.getSelectedIndex();
				ArrayList<Mundo> arrMundo = daoMundo.getAll();
				comboBox_SelectMundo.setModel(new DefaultComboBoxModel(arrMundo.toArray()));
				comboBox_SelectMundo.setSelectedIndex(lastSelectedIndex);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			loadData();
			NomeMundo.setText("");
			CodigoMundo.setText("");
			NomeMundo.requestFocus();			
		}
		
		if(e.getActionCommand().equals("Limpar")){
			comboBox_SelectMundo.setSelectedItem(null);
			NomeMundo.setText("");
			CodigoMundo.setText("");
			NomeMundo.requestFocus();
		}
	}
		
		private void gerarRelatorio() {
			try {
				ArrayList<Mundo> listaMundo;

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
					
					listaMundo = daoMundo.getAll();
					if (listaMundo.isEmpty())
						fw.append("\t<Nenhum alinhamento cadastrado.>\r\r");
					else {
						for (Mundo mun : listaMundo) {
									fw.append(mun.toLongString().replace("\n\n", "\r"));
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
			DefaultTableModel dm = new MundoDAO(dbman.getCon()).getData();
			table_ViewMundo.setModel(dm);
		}
}

