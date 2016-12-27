package view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AcessoBD;
import controller.listener.BatalhaListener;
import controller.listener.PersonagemListener;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicialGamerView extends JFrame {
	
	private static AcessoBD dbman;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicialGamerView frame = new TelaInicialGamerView();
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
	 */
	public TelaInicialGamerView() {
		setTitle("Mortal Kombat Game - Bem Vindo - Gamer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PersonagemListener pl = new PersonagemListener();
		BatalhaListener bl = new BatalhaListener();
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logo.png"))));
		label.setBounds(123, 416, 222, 42);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("-- Selecione a op\u00E7\u00E3o desejada --");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_1.setBounds(12, 13, 298, 31);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Personagem");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(47, 77, 119, 22);
		contentPane.add(label_2);
		
		JButton btn_CrudPersonagem = new JButton("CRUD");
		btn_CrudPersonagem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/icon_ti.png"))));
		btn_CrudPersonagem.addActionListener(pl);
		btn_CrudPersonagem.setEnabled(true);
		btn_CrudPersonagem.setBounds(32, 109, 137, 31);
		contentPane.add(btn_CrudPersonagem);
		
		JLabel label_3 = new JLabel("Batalha");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_3.setBounds(246, 80, 89, 16);
		contentPane.add(label_3);
		
		JButton btn_SimularBatalha = new JButton("Simular");
		btn_SimularBatalha.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/flame.png"))));
		btn_SimularBatalha.addActionListener(bl);
		btn_SimularBatalha.setBounds(208, 109, 137, 31);
		contentPane.add(btn_SimularBatalha);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
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
		btnSair.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logout.png"))));
		btnSair.setBounds(12, 615, 97, 25);
		contentPane.add(btnSair);
		
		JLabel bg_tiGamer = new JLabel("");
		bg_tiGamer.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/bg_2.png"))));
		bg_tiGamer.setBounds(0, 0, 1041, 685);
		contentPane.add(bg_tiGamer);
	}
}
