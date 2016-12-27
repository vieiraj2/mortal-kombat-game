package controller.listener;

import static view.EstiloLutaView.table_ViewEstilo;
import static view.ClassificacaoView.NomeCla;
import static view.EstiloLutaView.CodigoEstilo;
import static view.EstiloLutaView.NomeEstilo;
import static view.EstiloLutaView.comboBox_SelectEstilo;
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
import model.dao.EstiloLutaDAO;
import model.entity.EstiloLuta;
import view.EstiloLutaView;

public class EstiloLutaListener implements ActionListener{
	
	private static EstiloLutaDAO daoEstilo;
	private static AcessoBD dbman;

	public void actionPerformed(ActionEvent e){
		
		dbman = new AcessoBD();
		daoEstilo = new EstiloLutaDAO(dbman.getCon());
		
		if(e.getActionCommand().equals("Relatório")){
			gerarRelatorio();
		}
		
		if(e.getActionCommand().equals("CRUD")){
			EstiloLutaView ev;
			try {
				ev = new EstiloLutaView();
				ev.setVisible(true);
				ev.setResizable(false);
				ev.setLocationRelativeTo(null);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		if(e.getActionCommand().equals("Carregar estilos de luta")){
			loadData();
		}
		
		if(e.getActionCommand().equals("Cadastrar")){
			try { 
				EstiloLuta novoEstilo = new EstiloLuta(-1, NomeEstilo.getText());
				String n = NomeEstilo.getText();
				if(n.equals("")){
					JOptionPane.showMessageDialog(null, "Erro. Campo 'Nome' vazio.");
				}else{
					daoEstilo.insert(novoEstilo);
					JOptionPane.showMessageDialog(null, "Estilo de luta '" + novoEstilo.getNomeEstilo() + "' inserido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				
				try {
					comboBox_SelectEstilo.setModel(new DefaultComboBoxModel(daoEstilo.getAll().toArray()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (NullPointerException | NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Falha ao inserir o estilo de luta.\nVerifique os campos e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
			loadData();
			NomeEstilo.setText("");
			CodigoEstilo.setText("");
			NomeEstilo.requestFocus();
		}
	
		if(e.getActionCommand().equals("Remover")){	
			try {
				String nome = comboBox_SelectEstilo.getSelectedItem().toString();
				int id = 0;
				
				List<EstiloLuta> listal = daoEstilo.getAll();
				
				for (EstiloLuta estilo : listal) {
					if(estilo.getNomeEstilo().equals(nome)){
						id = estilo.getIdEstilo();		
						JOptionPane.showMessageDialog(null, "Estilo de luta '" + estilo.getNomeEstilo() + "' removido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					}	
				}
				
				daoEstilo.delete(id);
				ArrayList<EstiloLuta> arrCla = daoEstilo.getAll();
				comboBox_SelectEstilo.setModel(new DefaultComboBoxModel(arrCla.toArray()));
				
				if(comboBox_SelectEstilo.getItemCount()==0) {
					JOptionPane.showMessageDialog(null, "Não há estilos de luta a serem editados.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				}				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			loadData();
			NomeEstilo.setText("");
			CodigoEstilo.setText("");
			NomeEstilo.requestFocus();
		}
		
		if(e.getActionCommand().equals("Editar")){	
			try {			
				EstiloLuta velhoEstilo = (EstiloLuta)comboBox_SelectEstilo.getSelectedItem();
				EstiloLuta novoEstilo = new EstiloLuta(null, NomeEstilo.getText());
				String n = NomeEstilo.getText();
				if(n.equals("")){
					JOptionPane.showMessageDialog(null, "Erro. Campo 'Nome' vazio.");
				}else{
					daoEstilo.update(velhoEstilo, novoEstilo);
					JOptionPane.showMessageDialog(null, "Estilo de luta '" + velhoEstilo.getNomeEstilo() + "' editado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				int lastSelectedIndex = comboBox_SelectEstilo.getSelectedIndex();
				ArrayList<EstiloLuta> arrMundo = daoEstilo.getAll();
				comboBox_SelectEstilo.setModel(new DefaultComboBoxModel(arrMundo.toArray()));
				comboBox_SelectEstilo.setSelectedIndex(lastSelectedIndex);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			loadData();
			NomeEstilo.setText("");
			CodigoEstilo.setText("");
			NomeEstilo.requestFocus();			
		}
		
		if(e.getActionCommand().equals("Limpar")){
			comboBox_SelectEstilo.setSelectedItem(null);
			NomeEstilo.setText("");
			CodigoEstilo.setText("");
			NomeEstilo.requestFocus();
		}
	}
	
		private void gerarRelatorio() {
			try {
				ArrayList<EstiloLuta> listaEst;

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
					
					listaEst = daoEstilo.getAll();
					if (listaEst.isEmpty())
						fw.append("\t<Nenhum estilo de luta cadastrado.>\r\r");
					else {
						for (EstiloLuta est : listaEst) {
									fw.append(est.toLongString().replace("\n\n", "\r"));
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
			DefaultTableModel dm = new EstiloLutaDAO(dbman.getCon()).getData();
			table_ViewEstilo.setModel(dm);
		}
}
