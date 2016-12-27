package controller.listener;

import static view.ClassificacaoView.table_ViewCla;
import static view.AlinhamentoView.NomeAli;
import static view.ClassificacaoView.CodigoCla;
import static view.ClassificacaoView.NomeCla;
import static view.ClassificacaoView.comboBox_SelectCla;
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
import model.dao.ClassificacaoDAO;
import model.entity.Classificacao;
import view.ClassificacaoView;

public class ClassificacaoListener implements ActionListener {
	
	private static ClassificacaoDAO daoCla;
	private static AcessoBD dbman;

	public void actionPerformed(ActionEvent e){
		
		dbman = new AcessoBD();
		daoCla = new ClassificacaoDAO(dbman.getCon());
		
		
		if(e.getActionCommand().equals("Relatório")){
			gerarRelatorio();
		}
		
		if(e.getActionCommand().equals("CRUD")){
			ClassificacaoView cv;
			try {
				cv = new ClassificacaoView();
				cv.setVisible(true);
				cv.setResizable(false);
				cv.setLocationRelativeTo(null);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand().equals("Carregar classificações")){
			loadData();
		}
		
		if(e.getActionCommand().equals("Cadastrar")){
			try { 
				Classificacao novaCla = new Classificacao(-1, NomeCla.getText());
				String n = NomeCla.getText();
				if(n.equals("")){
					JOptionPane.showMessageDialog(null, "Erro. Campo 'Nome' vazio.");
				}else{
					daoCla.insert(novaCla);
					JOptionPane.showMessageDialog(null, "Classificação '" + novaCla.getNomeClassificacao() + "' inserida com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				
				try {
					comboBox_SelectCla.setModel(new DefaultComboBoxModel(daoCla.getAll().toArray()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (NullPointerException | NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Falha ao inserir a classificação.\nVerifique os campos e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
			loadData();
			NomeCla.setText("");
			CodigoCla.setText("");
			NomeCla.requestFocus();
		}
	
		if(e.getActionCommand().equals("Remover")){	
			try {
				String nome = comboBox_SelectCla.getSelectedItem().toString();
				int id = 0;
				
				List<Classificacao> listal = daoCla.getAll();
				
				for (Classificacao cla : listal) {
					if(cla.getNomeClassificacao().equals(nome)){
						id = cla.getIdClassificacao();		
						JOptionPane.showMessageDialog(null, "Classificação '" + cla.getNomeClassificacao() + "' removida com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					}	
				}
				
				daoCla.delete(id);
				ArrayList<Classificacao> arrCla = daoCla.getAll();
				comboBox_SelectCla.setModel(new DefaultComboBoxModel(arrCla.toArray()));
				
				if(comboBox_SelectCla.getItemCount()==0) {
					JOptionPane.showMessageDialog(null, "Não há alinhamentos a serem editados.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				}				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			loadData();
			NomeCla.setText("");
			CodigoCla.setText("");
			NomeCla.requestFocus();
		}
		
		if(e.getActionCommand().equals("Editar")){	
			try {			
				Classificacao velhaCla = (Classificacao)comboBox_SelectCla.getSelectedItem();
				Classificacao novaCla = new Classificacao(null, NomeCla.getText());
				String n = NomeCla.getText();
				if(n.equals("")){
					JOptionPane.showMessageDialog(null, "Erro. Campo 'Nome' vazio.");
				}else{
					
					daoCla.update(velhaCla, novaCla);
					JOptionPane.showMessageDialog(null, "Classificacão '" + velhaCla.getNomeClassificacao() + "' editada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				int lastSelectedIndex = comboBox_SelectCla.getSelectedIndex();
				ArrayList<Classificacao> arrMundo = daoCla.getAll();
				comboBox_SelectCla.setModel(new DefaultComboBoxModel(arrMundo.toArray()));
				comboBox_SelectCla.setSelectedIndex(lastSelectedIndex);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			loadData();
			NomeCla.setText("");
			CodigoCla.setText("");
			NomeCla.requestFocus();			
		}
		
		if(e.getActionCommand().equals("Limpar")){
			comboBox_SelectCla.setSelectedItem(null);
			NomeCla.setText("");
			CodigoCla.setText("");
			NomeCla.requestFocus();
		}
	}
		
		private void gerarRelatorio() {
			try {
				ArrayList<Classificacao> listaCla;

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
					
					listaCla = daoCla.getAll();
					if (listaCla.isEmpty())
						fw.append("\t<Nenhuma classificação cadastrada.>\r\r");
					else {
						for (Classificacao ali : listaCla) {
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
			DefaultTableModel dm = new ClassificacaoDAO(dbman.getCon()).getData();
			table_ViewCla.setModel(dm);
		}

}
