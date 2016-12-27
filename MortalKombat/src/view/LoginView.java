package view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.listener.LoginListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class LoginView extends JFrame {

	private JPanel bgLogin;
	public static JTextField User;
	private JButton btn_Entrar;
	private JButton btn_Cancelar;
	public static JPasswordField Senha;
	private JLabel setBgLogin;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginView() {
		setTitle("Mortal Kombat Game - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		bgLogin = new JPanel();
		bgLogin.setBackground(Color.WHITE);
		bgLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bgLogin);
		bgLogin.setLayout(null);
		
		LoginListener ll = new LoginListener();
		
		User = new JTextField();
		User.setBounds(260, 188, 210, 22);
		bgLogin.add(User);
		User.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(260, 168, 56, 16);
		bgLogin.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(260, 223, 56, 16);
		bgLogin.add(lblNewLabel_1);
		
		
		btn_Entrar = new JButton("Entrar");
		btn_Entrar.addActionListener(ll);
		btn_Entrar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/login_ok.png"))));
		btn_Entrar.setBounds(260, 276, 93, 33);
		bgLogin.add(btn_Entrar);
		
		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.addActionListener(ll);
		btn_Cancelar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/login_cancel.png"))));
		btn_Cancelar.setBounds(356, 276, 114, 33);
		bgLogin.add(btn_Cancelar);
		
		Senha = new JPasswordField();
		Senha.setBounds(260, 241, 210, 22);
		bgLogin.add(Senha);
		
		JLabel Logo = new JLabel("");
		Logo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logo.png"))));
		Logo.setBounds(222, 13, 248, 110);
		bgLogin.add(Logo);
		
		setBgLogin = new JLabel("");
		setBgLogin.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/login_sub.png"))));
		setBgLogin.setBounds(0, 0, 500, 467);
		bgLogin.add(setBgLogin);
	}
}
