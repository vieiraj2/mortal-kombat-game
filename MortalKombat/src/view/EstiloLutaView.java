package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.AcessoBD;
import controller.listener.EstiloLutaListener;
import model.dao.EstiloLutaDAO;
import model.entity.EstiloLuta;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Color;

public class EstiloLutaView extends JFrame {
	
	private static EstiloLutaDAO daoEstilo;
	private static AcessoBD dbman;

	private JPanel bg_Estilo;
	public static JTextField CodigoEstilo;
	public static JTextField NomeEstilo;
	public static JComboBox comboBox_SelectEstilo;
	public static JTable table_ViewEstilo;
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstiloLutaView frame = new EstiloLutaView();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EstiloLutaView() throws SQLException {
		setTitle("Mortal Kombat Game - Cria\u00E7\u00E3o, busca, edi\u00E7\u00E3o e remo\u00E7\u00E3o de estilos de luta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 500);
		bg_Estilo = new JPanel();
		bg_Estilo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bg_Estilo);
		bg_Estilo.setLayout(null);
		
		EstiloLutaListener el = new EstiloLutaListener();
		
		JButton btnCarregarEstilo = new JButton("Carregar estilos de luta");
		btnCarregarEstilo.addActionListener(el);
		
		JLabel logo_est = new JLabel("");
		logo_est.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logo.png"))));
		logo_est.setBounds(230, 32, 226, 39);
		bg_Estilo.add(logo_est);
		
		btnCarregarEstilo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_carregar.png"))));
		btnCarregarEstilo.setBounds(329, 207, 350, 25);
		bg_Estilo.add(btnCarregarEstilo);
		
		JLabel lblCrudEstilo = new JLabel("CRUD - Estilo de Luta");
		lblCrudEstilo.setForeground(Color.WHITE);
		lblCrudEstilo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCrudEstilo.setBounds(255, 74, 286, 21);
		bg_Estilo.add(lblCrudEstilo);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 316, 56, 16);
		bg_Estilo.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("C\u00F3digo:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(12, 283, 56, 16);
		bg_Estilo.add(lblNewLabel_1);
		
		CodigoEstilo = new JTextField();
		CodigoEstilo.setEnabled(false);
		CodigoEstilo.setBounds(80, 280, 45, 22);
		bg_Estilo.add(CodigoEstilo);
		CodigoEstilo.setColumns(10);
		
		NomeEstilo = new JTextField();
		NomeEstilo.setBounds(80, 313, 237, 22);
		bg_Estilo.add(NomeEstilo);
		NomeEstilo.setColumns(10);
		
		JLabel lblEscolha = new JLabel("Selecione:");
		lblEscolha.setForeground(Color.WHITE);
		lblEscolha.setBounds(12, 251, 68, 16);
		bg_Estilo.add(lblEscolha);
		
		dbman = new AcessoBD();
		daoEstilo = new EstiloLutaDAO(dbman.getCon());
		
		List<EstiloLuta> listal = daoEstilo.getAll();
		
		Vector<String> vetor = new Vector<String>();
		
		for (EstiloLuta estilo : listal) {
			vetor.addElement(estilo.getNomeEstilo());
		}
		
		comboBox_SelectEstilo = new JComboBox(vetor);
		comboBox_SelectEstilo.setBounds(80, 245, 237, 22);
		bg_Estilo.add(comboBox_SelectEstilo);
		
		JButton btnCadastrarEstilo = new JButton("Cadastrar");
		btnCadastrarEstilo.addActionListener(el);
		btnCadastrarEstilo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_cadastrar.png"))));
		btnCadastrarEstilo.setBounds(80, 362, 125, 25);
		bg_Estilo.add(btnCadastrarEstilo);
		
		JButton btnEditarEstilo = new JButton("Editar");
		btnEditarEstilo.addActionListener(el);
		btnEditarEstilo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_editar.png"))));
		btnEditarEstilo.setBounds(208, 362, 109, 25);
		bg_Estilo.add(btnEditarEstilo);
		
		JButton btnRemoverEstilo = new JButton("Remover");
		btnRemoverEstilo.addActionListener(el);
		btnRemoverEstilo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_remover.png"))));
		btnRemoverEstilo.setBounds(80, 389, 125, 25);
		bg_Estilo.add(btnRemoverEstilo);
		
		JButton btnLimparEstilo = new JButton("Limpar");
		btnLimparEstilo.addActionListener(el);
		btnLimparEstilo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_limpar.png"))));
		btnLimparEstilo.setBounds(208, 389, 109, 25);
		bg_Estilo.add(btnLimparEstilo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 245, 350, 169);
		bg_Estilo.add(scrollPane);
		
		table_ViewEstilo = new JTable();
		scrollPane.setViewportView(table_ViewEstilo);
		
		JLabel bg_est = new JLabel("");
		bg_est.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/label_per.png"))));
		bg_est.setBounds(80, 0, 200, 200);
		bg_Estilo.add(bg_est);
		
		JLabel bg_estilo = new JLabel("");
		bg_estilo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/bg_black.png"))));
		bg_estilo.setBounds(0, 0, 731, 483);
		bg_Estilo.add(bg_estilo);
		
	}
}
