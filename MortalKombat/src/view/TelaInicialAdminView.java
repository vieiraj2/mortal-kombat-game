package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AcessoBD;
import controller.listener.AlinhamentoListener;
import controller.listener.ArmaListener;
import controller.listener.BatalhaListener;
import controller.listener.ClassificacaoListener;
import controller.listener.EstiloLutaListener;
import controller.listener.MundoListener;
import controller.listener.PersonagemListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicialAdminView extends JFrame {
	
	private static AcessoBD dbman;
	

	private JPanel bg_TelaInicial;
	public static JButton btnAli;
	public static JButton btnCla;
	public static JButton btnPersonagem;
	public static JButton btnMundo;
	public static JButton btnArma;
	public static JButton btnEstilo;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicialAdminView frame = new TelaInicialAdminView();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInicialAdminView() {
		setTitle("Mortal Kombat Game - Bem Vindo - Administrador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 810);
		bg_TelaInicial = new JPanel();
		bg_TelaInicial.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bg_TelaInicial);
		bg_TelaInicial.setLayout(null);
		
		PersonagemListener pl = new PersonagemListener();
		MundoListener ml = new MundoListener();
		ClassificacaoListener cl = new ClassificacaoListener();
		AlinhamentoListener al = new AlinhamentoListener();
		EstiloLutaListener el = new EstiloLutaListener();
		ArmaListener arl = new ArmaListener();
		BatalhaListener bl = new BatalhaListener();
		
		JButton btnPersonagem = new JButton("CRUD");
		btnPersonagem.setEnabled(true);
		btnPersonagem.addActionListener(pl);
		btnPersonagem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_ti.png"))));
		btnPersonagem.setBounds(707, 112, 137, 31);
		bg_TelaInicial.add(btnPersonagem);
		
		
		JButton btnMundo = new JButton("CRUD");
		btnMundo.setEnabled(true);
		btnMundo.addActionListener(ml);
		btnMundo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_ti.png"))));
		btnMundo.setBounds(707, 208, 137, 31);
		bg_TelaInicial.add(btnMundo);
		
		JLabel lblSelecioneA = new JLabel("-- Selecione a op\u00E7\u00E3o desejada --");
		lblSelecioneA.setForeground(Color.WHITE);
		lblSelecioneA.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSelecioneA.setBounds(697, 25, 298, 31);
		bg_TelaInicial.add(lblSelecioneA);
		
		JButton btnCla = new JButton("CRUD");
		btnCla.setEnabled(true);
		btnCla.addActionListener(cl);
		btnCla.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_ti.png"))));
		btnCla.setBounds(707, 313, 137, 31);
		bg_TelaInicial.add(btnCla);
		
		JButton btnEstilo = new JButton("CRUD");
		btnEstilo.setEnabled(true);
		btnEstilo.addActionListener(el);
		btnEstilo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_ti.png"))));
		btnEstilo.setBounds(707, 507, 137, 31);
		bg_TelaInicial.add(btnEstilo);
		
		JButton btnAli = new JButton("CRUD");
		btnAli.setEnabled(true);
		btnAli.addActionListener(al);
		btnAli.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_ti.png"))));
		btnAli.setBounds(707, 608, 137, 31);
		bg_TelaInicial.add(btnAli);
		
		JButton btnArma = new JButton("CRUD");
		btnArma.setEnabled(true);
		btnArma.addActionListener(arl);
		btnArma.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_ti.png"))));
		btnArma.setBounds(707, 405, 137, 31);
		bg_TelaInicial.add(btnArma);
		
		JLabel lblCrudPersonagem = new JLabel("Personagem");
		lblCrudPersonagem.setForeground(Color.WHITE);
		lblCrudPersonagem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCrudPersonagem.setBounds(697, 83, 174, 22);
		bg_TelaInicial.add(lblCrudPersonagem);
		
		JLabel lblCrudMundo = new JLabel("Mundo");
		lblCrudMundo.setForeground(Color.WHITE);
		lblCrudMundo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCrudMundo.setBounds(697, 178, 174, 16);
		bg_TelaInicial.add(lblCrudMundo);
		
		JLabel lblCrudClassificao = new JLabel("Classifica\u00E7\u00E3o");
		lblCrudClassificao.setForeground(Color.WHITE);
		lblCrudClassificao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCrudClassificao.setBounds(697, 284, 174, 16);
		bg_TelaInicial.add(lblCrudClassificao);
		
		JLabel lblCrudArma = new JLabel("Arma");
		lblCrudArma.setForeground(Color.WHITE);
		lblCrudArma.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCrudArma.setBounds(697, 376, 174, 16);
		bg_TelaInicial.add(lblCrudArma);
		
		JLabel lblCrudEstilo = new JLabel("Estilo de Luta");
		lblCrudEstilo.setForeground(Color.WHITE);
		lblCrudEstilo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCrudEstilo.setBounds(697, 478, 174, 16);
		bg_TelaInicial.add(lblCrudEstilo);
		
		JLabel lblCrudAlinhamento = new JLabel("Alinhamento");
		lblCrudAlinhamento.setForeground(Color.WHITE);
		lblCrudAlinhamento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCrudAlinhamento.setBounds(697, 579, 174, 16);
		bg_TelaInicial.add(lblCrudAlinhamento);
		
		JButton btnRelatorioPersonagem = new JButton("Relat\u00F3rio");
		btnRelatorioPersonagem.addActionListener(pl);
		btnRelatorioPersonagem.setEnabled(true);
		btnRelatorioPersonagem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_relatorio.png"))));
		btnRelatorioPersonagem.setBounds(857, 112, 137, 31);
		bg_TelaInicial.add(btnRelatorioPersonagem);
		
		JButton btnRelatorioMundo = new JButton("Relat\u00F3rio");
		btnRelatorioMundo.addActionListener(ml);
		btnRelatorioMundo.setEnabled(true);
		btnRelatorioMundo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_relatorio.png"))));
		btnRelatorioMundo.setBounds(857, 207, 137, 31);
		bg_TelaInicial.add(btnRelatorioMundo);
		
		JButton btnRelatorioCla = new JButton("Relat\u00F3rio");
		btnRelatorioCla.addActionListener(cl);
		btnRelatorioCla.setEnabled(true);
		btnRelatorioCla.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_relatorio.png"))));
		btnRelatorioCla.setBounds(857, 313, 137, 31);
		bg_TelaInicial.add(btnRelatorioCla);
		
		JButton btnRelatorioArma = new JButton("Relat\u00F3rio");
		btnRelatorioArma.addActionListener(arl);
		btnRelatorioArma.setEnabled(true);
		btnRelatorioArma.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_relatorio.png"))));
		btnRelatorioArma.setBounds(856, 405, 137, 31);
		bg_TelaInicial.add(btnRelatorioArma);
		
		JButton btnRelatorioEstilo = new JButton("Relat\u00F3rio");
		btnRelatorioEstilo.addActionListener(el);
		btnRelatorioEstilo.setEnabled(true);
		btnRelatorioEstilo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_relatorio.png"))));
		btnRelatorioEstilo.setBounds(857, 507, 137, 31);
		bg_TelaInicial.add(btnRelatorioEstilo);
		
		JButton btnRelatorioAli = new JButton("Relat\u00F3rio");
		btnRelatorioAli.addActionListener(al);
		btnRelatorioAli.setEnabled(true);
		btnRelatorioAli.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_relatorio.png"))));
		btnRelatorioAli.setBounds(857, 608, 137, 31);
		bg_TelaInicial.add(btnRelatorioAli);
		
		JLabel logo_ti = new JLabel("");
		logo_ti.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logo.png"))));
		logo_ti.setBounds(313, 281, 248, 39);
		bg_TelaInicial.add(logo_ti);
		
		JButton btnNewButton = new JButton("Simular");
		btnNewButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/flame.png"))));
		btnNewButton.addActionListener(bl);
		btnNewButton.setBounds(763, 702, 179, 31);
		bg_TelaInicial.add(btnNewButton);
		
		JLabel lblBatalha = new JLabel("Batalha");
		lblBatalha.setForeground(Color.WHITE);
		lblBatalha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBatalha.setBounds(821, 673, 174, 16);
		bg_TelaInicial.add(lblBatalha);
		
		JButton btnLogout = new JButton("Sair");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbman = new AcessoBD();
				dispose();
				LoginView lv = new LoginView();
				lv.setVisible(true);
				lv.setResizable(false);
				lv.setLocationRelativeTo(null);
				dbman.FecharBD();
			}
		});
		btnLogout.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logout.png"))));
		btnLogout.setBounds(12, 30, 97, 25);
		bg_TelaInicial.add(btnLogout);
		
		JLabel setBgTelaInicial = new JLabel("");
		setBgTelaInicial.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/Bg_tela_inicial.png"))));
		setBgTelaInicial.setBounds(0, 0, 1051, 786);
		bg_TelaInicial.add(setBgTelaInicial);
	}
}
