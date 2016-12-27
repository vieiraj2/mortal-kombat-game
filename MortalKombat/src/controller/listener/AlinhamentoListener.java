package controller.listener;

import static view.AlinhamentoView.CodigoAli;
import static view.AlinhamentoView.table_ViewAli;
import static view.AlinhamentoView.NomeAli;
import static view.AlinhamentoView.comboBox_SelectAli;
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
import model.dao.AlinhamentoDAO;
import model.entity.Alinhamento;
import view.AlinhamentoView;

public class AlinhamentoListener implements ActionListener {
	
	private static AlinhamentoDAO daoAli;
	private static AcessoBD dbman;

	public void actionPerformed(ActionEvent e){
		
		dbman = new AcessoBD();
		daoAli = new AlinhamentoDAO(dbman.getCon());
		
		if(e.getActionCommand().equals("Relatório")){
			gerarRelatorio();
		}
		
		if(e.getActionCommand().equals("CRUD")){
			AlinhamentoView av;
			try {
				av = new AlinhamentoView();
				av.setVisible(true);
				av.setResizable(false);
				av.setLocationRelativeTo(null);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand().equals("Carregar alinhamentos")){
			loadData();
		}
		
		if(e.getActionCommand().equals("Cadastrar")){
			try { 
				Alinhamento novoAlinhamento = new Alinhamento(-1, NomeAli.getText());
				String n = NomeAli.getText();
				if(n.equals("")){
					JOptionPane.showMessageDialog(null, "Erro. Campo 'Nome' vazio.");
				}else{
					daoAli.insert(novoAlinhamento);
					JOptionPane.showMessageDialog(null, "Alinhamento '" + novoAlinhamento.getNomeAlinhamento() + "' inserido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				
				try {
					comboBox_SelectAli.setModel(new DefaultComboBoxModel(daoAli.getAll().toArray()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if (comboBox_SelectAli.getItemCount() > 0) {
					comboBox_SelectAli.setEnabled(true);
					comboBox_SelectAli.setSelectedIndex(0);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (NullPointerException | NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Falha ao inserir o alinhamento.\nVerifique os campos e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
			loadData();
			NomeAli.setText("");
			CodigoAli.setText("");
			NomeAli.requestFocus();
		}
	
		if(e.getActionCommand().equals("Remover")){	
			try {
				
				String nome = comboBox_SelectAli.getSelectedItem().toString();
				int id = 0;
				
				List<Alinhamento> listal = daoAli.getAll();
				
				for (Alinhamento ali : listal) {
					if(ali.getNomeAlinhamento().equals(nome)){
						id = ali.getIdAlinhamento();		
						JOptionPane.showMessageDialog(null, "Alinhamento '" + ali.getNomeAlinhamento() + "' removido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					}	
				}
				
				daoAli.delete(id);
				ArrayList<Alinhamento> arrAli = daoAli.getAll();
				comboBox_SelectAli.setModel(new DefaultComboBoxModel(arrAli.toArray()));
				
				if(comboBox_SelectAli.getItemCount()==0) {
					JOptionPane.showMessageDialog(null, "Não há alinhamentos a serem editados.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				}				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			loadData();
			NomeAli.setText("");
			CodigoAli.setText("");
			NomeAli.requestFocus();
		}
		
		if(e.getActionCommand().equals("Editar")){			
			try {			
				Alinhamento velhoAli = (Alinhamento)comboBox_SelectAli.getSelectedItem();
				Alinhamento novoAli = new Alinhamento(null, NomeAli.getText());
				String n = NomeAli.getText();
				if(n.equals("")){
					JOptionPane.showMessageDialog(null, "Erro. Campo 'Nome' vazio.");
				}else{
					daoAli.update(velhoAli, novoAli);
					JOptionPane.showMessageDialog(null, "Alinhamento '" + velhoAli.getNomeAlinhamento() + "' editado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				int lastSelectedIndex = comboBox_SelectAli.getSelectedIndex();
				ArrayList<Alinhamento> arrAli = daoAli.getAll();
				comboBox_SelectAli.setModel(new DefaultComboBoxModel(arrAli.toArray()));
				comboBox_SelectAli.setSelectedIndex(lastSelectedIndex);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			loadData();
			NomeAli.setText("");
			CodigoAli.setText("");
			NomeAli.requestFocus();			
		}
		
		if(e.getActionCommand().equals("Limpar")){
			comboBox_SelectAli.setSelectedItem(null);
			NomeAli.setText("");
			CodigoAli.setText("");
			NomeAli.requestFocus();
		}
	}
		
		private void gerarRelatorio() {
			try {
				ArrayList<Alinhamento> listaAli;

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
					
					listaAli = daoAli.getAll();
					if (listaAli.isEmpty())
						fw.append("\t<Nenhum alinhamento cadastrado.>\r\r");
					else {
						for (Alinhamento ali : listaAli) {
									fw.append(ali.toLongString().replace("\n\n", "\r"));
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
			DefaultTableModel dm = new AlinhamentoDAO(dbman.getCon()).getData();
			table_ViewAli.setModel(dm);
		}
}
