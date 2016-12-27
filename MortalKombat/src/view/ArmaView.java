package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.AcessoBD;
import controller.listener.ArmaListener;
import model.dao.ArmaDAO;
import model.entity.Arma;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JScrollPane;
import java.awt.Color;

public class ArmaView extends JFrame {
	
	private static ArmaDAO daoArma;
	private static AcessoBD dbman;

	private JPanel bg_Arma;
	public static JTextField CodigoArma;
	public static JTextField NomeArma;
	public static JTextField AtaqueArma;
	public static JComboBox comboBox_SelectArma;
	public static JTable table_ViewArma;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArmaView frame = new ArmaView();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ArmaView() throws SQLException {
		setTitle("Mortal Kombat Game - Cria\u00E7\u00E3o, busca, edi\u00E7\u00E3o e remo\u00E7\u00E3o de armas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 500);
		bg_Arma = new JPanel();
		bg_Arma.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bg_Arma);
		bg_Arma.setLayout(null);
		
		ArmaListener al = new ArmaListener();
		
		JLabel logo_arma = new JLabel("");
		logo_arma.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logo.png"))));
		logo_arma.setBounds(215, 26, 226, 39);
		bg_Arma.add(logo_arma);
		
		JLabel lblCrudArma = new JLabel("CRUD - Arma");
		lblCrudArma.setForeground(Color.WHITE);
		lblCrudArma.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCrudArma.setBounds(263, 69, 286, 21);
		bg_Arma.add(lblCrudArma);
		
		JButton btnCarregarArma = new JButton("Carregar armas");
		btnCarregarArma.addActionListener(al);
		btnCarregarArma.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_carregar.png"))));
		btnCarregarArma.setBounds(319, 195, 353, 25);
		bg_Arma.add(btnCarregarArma);
		
		dbman = new AcessoBD();
		daoArma = new ArmaDAO(dbman.getCon());
		
		List<Arma> listal = daoArma.getAll();
		
		Vector<String> vetor = new Vector<String>();
		
		for (Arma arma : listal) {
			vetor.addElement(arma.getNomeArma());
		}
		
		comboBox_SelectArma = new JComboBox(vetor);
		comboBox_SelectArma.setBounds(80, 230, 227, 22);
		bg_Arma.add(comboBox_SelectArma);
		
		CodigoArma = new JTextField();
		CodigoArma.setEnabled(false);
		CodigoArma.setColumns(10);
		CodigoArma.setBounds(80, 265, 45, 22);
		bg_Arma.add(CodigoArma);
		
		NomeArma = new JTextField();
		NomeArma.setColumns(10);
		NomeArma.setBounds(80, 298, 227, 22);
		bg_Arma.add(NomeArma);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(12, 301, 56, 16);
		bg_Arma.add(label_1);
		
		JLabel label_2 = new JLabel("C\u00F3digo:");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(12, 268, 56, 16);
		bg_Arma.add(label_2);
		
		JLabel label_3 = new JLabel("Selecione:");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(12, 236, 68, 16);
		bg_Arma.add(label_3);
		
		JButton btnCadastrarArma = new JButton("Cadastrar");
		btnCadastrarArma.addActionListener(al);
		btnCadastrarArma.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_cadastrar.png"))));
		btnCadastrarArma.setBounds(80, 368, 117, 25);
		bg_Arma.add(btnCadastrarArma);
		
		JButton btnEditarArma = new JButton("Editar");
		btnEditarArma.addActionListener(al);
		btnEditarArma.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_editar.png"))));
		btnEditarArma.setBounds(199, 368, 108, 25);
		bg_Arma.add(btnEditarArma);
		
		JButton btnRemoverArma = new JButton("Remover");
		btnRemoverArma.addActionListener(al);
		btnRemoverArma.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_remover.png"))));
		btnRemoverArma.setBounds(80, 396, 117, 25);
		bg_Arma.add(btnRemoverArma);
		
		JButton btnLimparArma = new JButton("Limpar");
		btnLimparArma.addActionListener(al);
		btnLimparArma.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_limpar.png"))));
		btnLimparArma.setBounds(199, 396, 108, 25);
		bg_Arma.add(btnLimparArma);
		
		JLabel lblAtaque = new JLabel("Ataque:");
		lblAtaque.setForeground(Color.WHITE);
		lblAtaque.setBounds(12, 330, 56, 16);
		bg_Arma.add(lblAtaque);
		
		AtaqueArma = new JTextField();
		AtaqueArma.setColumns(10);
		AtaqueArma.setBounds(80, 327, 45, 22);
		bg_Arma.add(AtaqueArma);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(319, 233, 353, 191);
		bg_Arma.add(scrollPane);
		
		table_ViewArma = new JTable();
		scrollPane.setViewportView(table_ViewArma);
		
		JLabel label_ar = new JLabel("");
		label_ar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/label_per.png"))));
		label_ar.setBounds(85, 0, 200, 200);
		bg_Arma.add(label_ar);
		
		JLabel bg_arma = new JLabel("");
		bg_arma.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/bg_black.png"))));
		bg_arma.setBounds(0, 0, 720, 475);
		bg_Arma.add(bg_arma);
	}

}
