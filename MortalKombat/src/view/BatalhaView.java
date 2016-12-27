package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.AcessoBD;
import controller.listener.BatalhaListener;
import model.dao.PersonagemDAO;
import model.entity.Personagem;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class BatalhaView extends JFrame {
	
	private static AcessoBD db;
	private static PersonagemDAO daoPer;

	private JPanel contentPane;
	public static JTable table_SelectPers;
	public static JComboBox comboBox_Per1;
	public static JComboBox comboBox_Per2;
	public static JButton btn_batalha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BatalhaView frame = new BatalhaView();
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
	public BatalhaView() throws SQLException {
		setTitle("Mortal Kombat Game - Simula\u00E7\u00E3o de Batalha");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BatalhaListener bl = new BatalhaListener();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 370, 1049, 371);
		contentPane.add(scrollPane);
		
		table_SelectPers = new JTable();
		scrollPane.setViewportView(table_SelectPers);
		
		JLabel lblSimulaoDeBatalhas = new JLabel("Simula\u00E7\u00E3o de Batalhas");
		lblSimulaoDeBatalhas.setForeground(Color.WHITE);
		lblSimulaoDeBatalhas.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblSimulaoDeBatalhas.setBounds(453, 69, 297, 31);
		contentPane.add(lblSimulaoDeBatalhas);
		
		db = new AcessoBD();
		daoPer = new PersonagemDAO(db.getCon());
		
		List<Personagem> listaper = daoPer.getAll();
		
		Vector<String> vetor_per = new Vector<String>();
		
		for (Personagem per : listaper) {
			vetor_per.addElement(per.getNome());
		}
		
		comboBox_Per1 = new JComboBox(vetor_per);
		comboBox_Per1.setBounds(255, 259, 211, 31);
		contentPane.add(comboBox_Per1);
		
		comboBox_Per2 = new JComboBox(vetor_per);
		comboBox_Per2.setBounds(616, 259, 211, 31);
		contentPane.add(comboBox_Per2);
		
		JLabel label_per1 = new JLabel("Selecione o personagem:");
		label_per1.setForeground(Color.WHITE);
		label_per1.setBounds(255, 225, 157, 21);
		contentPane.add(label_per1);
		
		JLabel label_per2 = new JLabel("Selecione o personagem:");
		label_per2.setForeground(Color.WHITE);
		label_per2.setBounds(616, 225, 157, 21);
		contentPane.add(label_per2);
		
		JButton btn_batalha = new JButton("Fight");
		btn_batalha.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/flame.png"))));
		btn_batalha.addActionListener(bl);
		btn_batalha.setBounds(540, 320, 195, 25);
		contentPane.add(btn_batalha);
		
		JButton btnCarregarPer = new JButton("Carregar personagens");
		btnCarregarPer.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_carregar.png"))));
		btnCarregarPer.addActionListener(bl);
		btnCarregarPer.setBounds(333, 320, 195, 25);
		contentPane.add(btnCarregarPer);
		
		JLabel lblVs = new JLabel("VS");
		lblVs.setForeground(Color.WHITE);
		lblVs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVs.setBounds(525, 263, 56, 16);
		contentPane.add(lblVs);
		
		JLabel label_logo = new JLabel("");
		label_logo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logo.png"))));
		label_logo.setBounds(414, 36, 248, 39);
		contentPane.add(label_logo);
		
		JLabel bg_batalha = new JLabel("");
		bg_batalha.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/bg_1.png"))));
		bg_batalha.setBounds(0, -46, 1049, 787);
		contentPane.add(bg_batalha);
	}
}
