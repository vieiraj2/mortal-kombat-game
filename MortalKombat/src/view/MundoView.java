package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.AcessoBD;
import controller.listener.MundoListener;
import model.dao.MundoDAO;
import model.entity.Mundo;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JScrollPane;
import java.awt.Color;

public class MundoView extends JFrame {
	
	private static MundoDAO daoMundo;
	private static AcessoBD dbman;

	private JPanel bg_Mundo;
	public static JButton btnCadastrarMundo;
	public static JButton btnEditarMundo;
	public static JButton btnRemoverMundo;
	public static JButton btnLimparMundo;
	public static JTextField CodigoMundo;
	public static JTextField NomeMundo;
	public static JComboBox comboBox_SelectMundo;
	public static JTable table_ViewMundo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MundoView frame = new MundoView();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MundoView() throws SQLException {
		setTitle("Mortal Kombat Game - Cria\u00E7\u00E3o, busca, edi\u00E7\u00E3o e remo\u00E7\u00E3o de mundos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 500);
		bg_Mundo = new JPanel();
		bg_Mundo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bg_Mundo);
		bg_Mundo.setLayout(null);
		
		MundoListener ml = new MundoListener();
		
		JLabel logo_mundo = new JLabel("");
		logo_mundo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logo.png"))));
		logo_mundo.setBounds(183, 28, 226, 39);
		bg_Mundo.add(logo_mundo);
		
		JLabel lblCrudMundo = new JLabel("CRUD - Mundo");
		lblCrudMundo.setForeground(Color.WHITE);
		lblCrudMundo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCrudMundo.setBounds(217, 67, 286, 21);
		bg_Mundo.add(lblCrudMundo);
		
		JButton btnCarregarMundo = new JButton("Carregar mundos");
		btnCarregarMundo.addActionListener(ml);
		btnCarregarMundo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_carregar.png"))));
		btnCarregarMundo.setBounds(330, 219, 350, 25);
		bg_Mundo.add(btnCarregarMundo);
		
		dbman = new AcessoBD();
		daoMundo = new MundoDAO(dbman.getCon());
		
		List<Mundo> listal = daoMundo.getAll();
		
		Vector<String> vetor = new Vector<String>();
		
		for (Mundo mundo : listal) {
			vetor.addElement(mundo.getNomeMundo());
		}
		
		comboBox_SelectMundo = new JComboBox(vetor);
		comboBox_SelectMundo.setBounds(80, 257, 238, 22);
		bg_Mundo.add(comboBox_SelectMundo);
		
		CodigoMundo = new JTextField();
		CodigoMundo.setEnabled(false);
		CodigoMundo.setColumns(10);
		CodigoMundo.setBounds(80, 292, 45, 22);
		bg_Mundo.add(CodigoMundo);
		
		NomeMundo = new JTextField();
		NomeMundo.setColumns(10);
		NomeMundo.setBounds(80, 325, 238, 22);
		bg_Mundo.add(NomeMundo);
		
		JButton btnCadastrarMundo = new JButton("Cadastrar");
		btnCadastrarMundo.addActionListener(ml);
		btnCadastrarMundo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_cadastrar.png"))));
		btnCadastrarMundo.setBounds(80, 360, 117, 25);
		bg_Mundo.add(btnCadastrarMundo);
		
		JButton btnEditarMundo = new JButton("Editar");
		btnEditarMundo.addActionListener(ml);
		btnEditarMundo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_editar.png"))));
		btnEditarMundo.setBounds(201, 360, 117, 25);
		bg_Mundo.add(btnEditarMundo);
		
		JButton btnRemoverMundo = new JButton("Remover");
		btnRemoverMundo.addActionListener(ml);
		btnRemoverMundo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_remover.png"))));
		btnRemoverMundo.setBounds(80, 390, 117, 25);
		bg_Mundo.add(btnRemoverMundo);
		
		JButton btnLimparMundo = new JButton("Limpar");
		btnLimparMundo.addActionListener(ml);
		btnLimparMundo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_limpar.png"))));
		btnLimparMundo.setBounds(201, 390, 117, 25);
		bg_Mundo.add(btnLimparMundo);
		
		JLabel label_1 = new JLabel("Selecione:");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(12, 263, 68, 16);
		bg_Mundo.add(label_1);
		
		JLabel label_2 = new JLabel("C\u00F3digo:");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(12, 295, 56, 16);
		bg_Mundo.add(label_2);
		
		JLabel label_3 = new JLabel("Nome:");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(12, 328, 56, 16);
		bg_Mundo.add(label_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(330, 257, 350, 158);
		bg_Mundo.add(scrollPane);
		
		table_ViewMundo = new JTable();
		scrollPane.setViewportView(table_ViewMundo);
		
		JLabel label_mundo = new JLabel("");
		label_mundo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/label_per.png"))));
		label_mundo.setBounds(46, 0, 200, 200);
		bg_Mundo.add(label_mundo);
		
		JLabel bgPersonagem = new JLabel("");
		bgPersonagem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/bg_black.png"))));
		bgPersonagem.setBounds(0, 0, 720, 553);
		bg_Mundo.add(bgPersonagem);
	}
}
