package controller.listener;

import static view.ArmaView.CodigoArma;

import static view.ArmaView.NomeArma;
import static view.ArmaView.table_ViewArma;
import static view.ArmaView.AtaqueArma;
import static view.ArmaView.comboBox_SelectArma;
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
import model.dao.ArmaDAO;
import model.entity.Arma;
import view.ArmaView;

public class ArmaListener implements ActionListener {
	
	private static ArmaDAO daoArma;
	private static AcessoBD dbman;

	public void actionPerformed(ActionEvent e){
		
		dbman = new AcessoBD();
		daoArma = new ArmaDAO(dbman.getCon());
		
		if(e.getActionCommand().equals("Relatório")){
			gerarRelatorio();
		}
		if(e.getActionCommand().equals("CRUD")){
			ArmaView av;
			try {
				av = new ArmaView();
				av.setVisible(true);
				av.setResizable(false);
				av.setLocationRelativeTo(null);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand().equals("Carregar armas")){
			loadData();
		}
		
		if(e.getActionCommand().equals("Cadastrar")){
			try { 
				Arma novaArma = new Arma(-1, NomeArma.getText(), Integer.parseInt(AtaqueArma.getText()));
				daoArma.insert(novaArma);
				JOptionPane.showMessageDialog(null, "Arma '" + novaArma.getNomeArma() + "' inserida com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				try {
					comboBox_SelectArma.setModel(new DefaultComboBoxModel(daoArma.getAll().toArray()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (comboBox_SelectArma.getItemCount() > 0) {
					comboBox_SelectArma.setEnabled(true);
					comboBox_SelectArma.setSelectedIndex(0);	
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (NullPointerException | NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Falha ao inserir a arma.\nVerifique os campos e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
			loadData();
			NomeArma.setText("");
			CodigoArma.setText("");
			AtaqueArma.setText("");
			NomeArma.requestFocus();
		}
	
		if(e.getActionCommand().equals("Remover")){	
			try {
				String nome = comboBox_SelectArma.getSelectedItem().toString();
				int id = 0;
				
				List<Arma> listal = daoArma.getAll();
				
				for (Arma arma : listal) {
					if(arma.getNomeArma().equals(nome)){
						id = arma.getIdArma();		
						JOptionPane.showMessageDialog(null, "Arma '" + arma.getNomeArma() + "' removida com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					}	
				}
				
				daoArma.delete(id);
				ArrayList<Arma> arrAli = daoArma.getAll();
				comboBox_SelectArma.setModel(new DefaultComboBoxModel(arrAli.toArray()));
				
				if(comboBox_SelectArma.getItemCount()==0) {
					JOptionPane.showMessageDialog(null, "Não há armas a serem editadas.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				}				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			loadData();
			NomeArma.setText("");
			CodigoArma.setText("");
			NomeArma.requestFocus();
		}
		
		if(e.getActionCommand().equals("Editar")){	
			try {			
				Arma velhaArma = (Arma)comboBox_SelectArma.getSelectedItem();
				Arma novaArma = new Arma(null, NomeArma.getText(), Integer.parseInt(AtaqueArma.getText()));
				int lastSelectedIndex = comboBox_SelectArma.getSelectedIndex();
				daoArma.update(velhaArma, novaArma);
				JOptionPane.showMessageDialog(null, "Arma '" + velhaArma.getNomeArma() + "' editada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				ArrayList<Arma> arrArma = daoArma.getAll();
				comboBox_SelectArma.setModel(new DefaultComboBoxModel(arrArma.toArray()));
				comboBox_SelectArma.setSelectedIndex(lastSelectedIndex);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			loadData();
			NomeArma.setText("");
			CodigoArma.setText("");
			AtaqueArma.setText("");
			NomeArma.requestFocus();			
		}
		
		if(e.getActionCommand().equals("Limpar")){
			comboBox_SelectArma.setSelectedItem(null);
			NomeArma.setText("");
			CodigoArma.setText("");
			AtaqueArma.setText("");
			NomeArma.requestFocus();
		}
	}
		
		private void gerarRelatorio() {
			try {
				
				ArrayList<Arma> listaArma;

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
					
					listaArma = daoArma.getAll();
					
					if (listaArma.isEmpty())
						fw.append("\t<Nenhuma arma cadastrada.>\r\r");
					else {
						for (Arma arm : listaArma) {
									fw.append(arm.toLongString().replace("\n\n", "\r"));
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
			DefaultTableModel dm = new ArmaDAO(dbman.getCon()).getData();
			table_ViewArma.setModel(dm);
		}
}
