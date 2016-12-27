package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.AcessoBD;
import controller.listener.ClassificacaoListener;
import model.dao.ClassificacaoDAO;
import model.entity.Classificacao;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Color;

public class ClassificacaoView extends JFrame {
	
	private static ClassificacaoDAO daoCla;
	private static AcessoBD dbman;

	private JPanel bg_Cla;
	public static JTextField CodigoCla;
	public static JTextField NomeCla;
	public static JComboBox comboBox_SelectCla;
	public static JTable table_ViewCla;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassificacaoView frame = new ClassificacaoView();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ClassificacaoView() throws SQLException {
		setTitle("Mortal Kombat Game - Cria\u00E7\u00E3o, busca, edi\u00E7\u00E3o e remo\u00E7\u00E3o de classifica\u00E7\u00F5es");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 500);
		bg_Cla = new JPanel();
		bg_Cla.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bg_Cla);
		bg_Cla.setLayout(null);
		
		ClassificacaoListener cl = new ClassificacaoListener();
		
		JLabel logo_cla = new JLabel("");
		logo_cla.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logo.png"))));
		logo_cla.setBounds(193, 30, 226, 39);
		bg_Cla.add(logo_cla);
		
		JLabel lblCrudClassificai = new JLabel("CRUD - Classifica\u00E7\u00E3o");
		lblCrudClassificai.setForeground(Color.WHITE);
		lblCrudClassificai.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCrudClassificai.setBounds(228, 76, 286, 25);
		bg_Cla.add(lblCrudClassificai);
		
		JButton btnCarregarCla = new JButton("Carregar classifica\u00E7\u00F5es");
		btnCarregarCla.addActionListener(cl);
		btnCarregarCla.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_carregar.png"))));
		btnCarregarCla.setBounds(326, 199, 355, 25);
		bg_Cla.add(btnCarregarCla);
		
		dbman = new AcessoBD();
		daoCla = new ClassificacaoDAO(dbman.getCon());
		
		List<Classificacao> listal = daoCla.getAll();
		
		Vector<String> vetor = new Vector<String>();
		
		for (Classificacao cla : listal) {
			vetor.addElement(cla.getNomeClassificacao());
		}
		
		comboBox_SelectCla = new JComboBox(vetor);
		comboBox_SelectCla.setBounds(80, 237, 226, 22);
		bg_Cla.add(comboBox_SelectCla);
		
		CodigoCla = new JTextField();
		CodigoCla.setEnabled(false);
		CodigoCla.setColumns(10);
		CodigoCla.setBounds(80, 272, 45, 22);
		bg_Cla.add(CodigoCla);
		
		JLabel label_1 = new JLabel("Selecione:");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(12, 243, 68, 16);
		bg_Cla.add(label_1);
		
		JLabel label_2 = new JLabel("C\u00F3digo:");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(12, 275, 56, 16);
		bg_Cla.add(label_2);
		
		JLabel label_3 = new JLabel("Nome:");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(12, 308, 56, 16);
		bg_Cla.add(label_3);
		
		NomeCla = new JTextField();
		NomeCla.setColumns(10);
		NomeCla.setBounds(80, 305, 226, 22);
		bg_Cla.add(NomeCla);
		
		JButton btnCadastrarCla = new JButton("Cadastrar");
		btnCadastrarCla.addActionListener(cl);
		btnCadastrarCla.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_cadastrar.png"))));
		btnCadastrarCla.setBounds(80, 354, 118, 25);
		bg_Cla.add(btnCadastrarCla);
		
		JButton btnEditarCla = new JButton("Editar");
		btnEditarCla.addActionListener(cl);
		btnEditarCla.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_editar.png"))));
		btnEditarCla.setBounds(202, 354, 104, 25);
		bg_Cla.add(btnEditarCla);
		
		JButton btnRemoverCla = new JButton("Remover");
		btnRemoverCla.addActionListener(cl);
		btnRemoverCla.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_remover.png"))));
		btnRemoverCla.setBounds(80, 381, 118, 25);
		bg_Cla.add(btnRemoverCla);
		
		JButton btnLimparCla = new JButton("Limpar");
		btnLimparCla.addActionListener(cl);
		btnLimparCla.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_limpar.png"))));
		btnLimparCla.setBounds(202, 381, 104, 25);
		bg_Cla.add(btnLimparCla);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(326, 237, 355, 169);
		bg_Cla.add(scrollPane);
		
		table_ViewCla = new JTable();
		scrollPane.setViewportView(table_ViewCla);
		
		JLabel label_cla = new JLabel("");
		label_cla.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/label_per.png"))));
		label_cla.setBounds(45, 0, 200, 200);
		bg_Cla.add(label_cla);
		
		JLabel bg_clas = new JLabel("");
		bg_clas.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/bg_black.png"))));
		bg_clas.setBounds(0, 0, 720, 553);
		bg_Cla.add(bg_clas);
		
	}
}