package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.AcessoBD;
import controller.listener.PersonagemListener;
import model.dao.AlinhamentoDAO;
import model.dao.ArmaDAO;
import model.dao.ClassificacaoDAO;
import model.dao.EstiloLutaDAO;
import model.dao.MundoDAO;
import model.dao.PersonagemDAO;
import model.entity.Alinhamento;
import model.entity.Arma;
import model.entity.Classificacao;
import model.entity.EstiloLuta;
import model.entity.Mundo;
import model.entity.Personagem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class PersonagemView extends JFrame {
	
	private static PersonagemDAO daoPersonagem;
	private static MundoDAO daoMundo;
	private static ArmaDAO daoArma;
	private static EstiloLutaDAO daoEstilo;
	private static AlinhamentoDAO daoAli;
	private static ClassificacaoDAO daoCla;
	private static AcessoBD dbman;

	private JPanel bg_Personagem;
	public static JTextField CodigoPer;
	public static JTextField NomePer;
	public static JTable table_ViewPersonagem;
	public static JComboBox comboBox_EscolhaMundo;
	public static JComboBox comboBox_EscolhaCla;
	public static JComboBox comboBox_EscolhaArma1;
	public static JComboBox comboBox_EscolhaArma2;
	public static JComboBox comboBox_EscolhaArma3;
	public static JComboBox comboBox_EscolhaEstilo;
	public static JComboBox comboBox_EscolhaAli;
	public static JComboBox comboBox_SelectPersonagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonagemView frame = new PersonagemView();
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
	public PersonagemView() throws SQLException {
		dbman = new AcessoBD();
		
		setTitle("Mortal Kombat Game - Cria\u00E7\u00E3o, busca, edi\u00E7\u00E3o e remo\u00E7\u00E3o de personagens");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1231, 526);
		bg_Personagem = new JPanel();
		bg_Personagem.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bg_Personagem);
		bg_Personagem.setLayout(null);
		
		PersonagemListener pl = new PersonagemListener();
		
		JLabel lblCrudPersonagem = new JLabel("CRUD - Personagem");
		lblCrudPersonagem.setForeground(Color.WHITE);
		lblCrudPersonagem.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCrudPersonagem.setBounds(194, 62, 286, 31);
		bg_Personagem.add(lblCrudPersonagem);
		
		JLabel label_2 = new JLabel("C\u00F3digo:");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(22, 260, 56, 16);
		bg_Personagem.add(label_2);
		
		CodigoPer = new JTextField();
		CodigoPer.setEnabled(false);
		CodigoPer.setColumns(10);
		CodigoPer.setBounds(115, 257, 38, 22);
		bg_Personagem.add(CodigoPer);
		
		NomePer = new JTextField();
		NomePer.setColumns(10);
		NomePer.setBounds(219, 257, 177, 22);
		bg_Personagem.add(NomePer);
		
		JLabel label_3 = new JLabel("Nome:");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(163, 260, 56, 16);
		bg_Personagem.add(label_3);
		
		JButton CadastrarPer = new JButton("Cadastrar");
		CadastrarPer.addActionListener(pl);
		CadastrarPer.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_cadastrar.png"))));
		CadastrarPer.setBounds(115, 414, 139, 25);
		bg_Personagem.add(CadastrarPer);
		
		JButton EditarPer = new JButton("Editar");
		EditarPer.addActionListener(pl);
		EditarPer.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_editar.png"))));
		EditarPer.setBounds(257, 414, 139, 25);
		bg_Personagem.add(EditarPer);
		
		JButton button_RemoverPer = new JButton("Remover");
		button_RemoverPer.addActionListener(pl);
		button_RemoverPer.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_remover.png"))));
		button_RemoverPer.setBounds(115, 441, 139, 25);
		bg_Personagem.add(button_RemoverPer);
		
		JButton button_LimparPer = new JButton("Limpar");
		button_LimparPer.addActionListener(pl);
		button_LimparPer.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_limpar.png"))));
		button_LimparPer.setBounds(257, 441, 139, 25);
		bg_Personagem.add(button_LimparPer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(459, 13, 742, 415);
		bg_Personagem.add(scrollPane);
		
		table_ViewPersonagem = new JTable();
		scrollPane.setViewportView(table_ViewPersonagem);
		
		JButton btnCarregarPersonagens = new JButton("Carregar personagens");
		btnCarregarPersonagens.addActionListener(pl);
		btnCarregarPersonagens.setBounds(459, 441, 742, 25);
		bg_Personagem.add(btnCarregarPersonagens);
		
		JLabel lblNewLabel = new JLabel("Mundo:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(22, 295, 56, 16);
		bg_Personagem.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Classifica\u00E7\u00E3o:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(22, 324, 92, 16);
		bg_Personagem.add(lblNewLabel_1);
		
		JLabel lblArma = new JLabel("Arma:");
		lblArma.setForeground(Color.WHITE);
		lblArma.setBounds(22, 353, 78, 16);
		bg_Personagem.add(lblArma);
		
		JLabel lblEstiloDeLuta = new JLabel("Estilo de luta:");
		lblEstiloDeLuta.setForeground(Color.WHITE);
		lblEstiloDeLuta.setBounds(22, 382, 78, 16);
		bg_Personagem.add(lblEstiloDeLuta);
		
		JLabel lblAlinhamento = new JLabel("Alinhamento:");
		lblAlinhamento.setForeground(Color.WHITE);
		lblAlinhamento.setBounds(219, 382, 78, 16);
		bg_Personagem.add(lblAlinhamento);
		
		
		daoMundo = new MundoDAO(dbman.getCon());
		
		List<Mundo> listal1 = daoMundo.getAll();
		
		Vector<String> vetor1 = new Vector<String>();
		
		for (Mundo mun : listal1) {
			vetor1.addElement(mun.getNomeMundo());
		}
		
		comboBox_EscolhaMundo = new JComboBox(vetor1);
		comboBox_EscolhaMundo.setBounds(115, 289, 282, 22);
		bg_Personagem.add(comboBox_EscolhaMundo);
		
		daoCla = new ClassificacaoDAO(dbman.getCon());
		
		List<Classificacao> listal2 = daoCla.getAll();
		
		Vector<String> vetor2 = new Vector<String>();
		
		for (Classificacao cla : listal2) {
			vetor2.addElement(cla.getNomeClassificacao());
		}
		
		comboBox_EscolhaCla = new JComboBox(vetor2);
		comboBox_EscolhaCla.setBounds(115, 318, 282, 22);
		bg_Personagem.add(comboBox_EscolhaCla);
		
		daoArma = new ArmaDAO(dbman.getCon());
		
		List<Arma> listal3 = daoArma.getAll();
		
		Vector<String> vetor3 = new Vector<String>();
		
		for (Arma arma : listal3) {
			vetor3.addElement(arma.getNomeArma());
		}
		
		comboBox_EscolhaArma1 = new JComboBox(vetor3);
		comboBox_EscolhaArma1.setBounds(115, 347, 281, 22);
		bg_Personagem.add(comboBox_EscolhaArma1);
		
		daoEstilo = new EstiloLutaDAO(dbman.getCon());
		
		List<EstiloLuta> listal4 = daoEstilo.getAll();
		
		Vector<String> vetor4 = new Vector<String>();
		
		for (EstiloLuta estilo : listal4) {
			vetor4.addElement(estilo.getNomeEstilo());
		}
		
		comboBox_EscolhaEstilo = new JComboBox(vetor4);
		comboBox_EscolhaEstilo.setBounds(115, 379, 100, 22);
		bg_Personagem.add(comboBox_EscolhaEstilo);
		
		daoAli = new AlinhamentoDAO(dbman.getCon());
		
		List<Alinhamento> listal5 = daoAli.getAll();
		
		Vector<String> vetor5 = new Vector<String>();
		
		for (Alinhamento ali : listal5) {
			vetor5.addElement(ali.getNomeAlinhamento());
		}
		
		comboBox_EscolhaAli = new JComboBox(vetor5);
		comboBox_EscolhaAli.setBounds(296, 376, 100, 22);
		bg_Personagem.add(comboBox_EscolhaAli);
		
		JLabel lblSelecione = new JLabel("Selecione:");
		lblSelecione.setForeground(Color.WHITE);
		lblSelecione.setBounds(22, 231, 78, 16);
		bg_Personagem.add(lblSelecione);
		
		
		daoPersonagem = new PersonagemDAO(dbman.getCon());
		
		List<Personagem> listal = daoPersonagem.getAll();
		
		Vector<String> vetor = new Vector<String>();
		
		for (Personagem per : listal) {
			vetor.addElement(per.getNome());
		}
		
		comboBox_SelectPersonagem = new JComboBox(vetor);
		comboBox_SelectPersonagem.setBounds(115, 222, 281, 22);
		bg_Personagem.add(comboBox_SelectPersonagem);
		
		JLabel logo_per = new JLabel("");
		logo_per.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logo.png"))));
		logo_per.setBounds(170, 25, 226, 39);
		bg_Personagem.add(logo_per);
		
		JLabel label_per = new JLabel("");
		label_per.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/label_per.png"))));
		label_per.setBounds(22, 0, 200, 200);
		bg_Personagem.add(label_per);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(104, 34, 56, 16);
		bg_Personagem.add(label_1);
		
		JLabel bg_per = new JLabel("");
		bg_per.setForeground(Color.BLACK);
		bg_per.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/bg_black.png"))));
		bg_per.setBounds(0, 0, 1257, 500);
		bg_Personagem.add(bg_per);
		
	}
}
