package controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import controller.AcessoBD;
import static view.LoginView.*;
import model.dao.UsuarioDAO;
import model.entity.Usuario;
import view.TelaInicialAdminView;
import view.TelaInicialGamerView;

public class LoginListener implements ActionListener {
	
	private static AcessoBD dbman;
	private static UsuarioDAO daoUsuario;
	
	public void actionPerformed(ActionEvent e){
		
		dbman = new AcessoBD();
		daoUsuario = new UsuarioDAO(dbman.getCon());
		
		TelaInicialAdminView ti = new TelaInicialAdminView();
		TelaInicialGamerView tg = new TelaInicialGamerView();
		
		if(e.getActionCommand().equals("Entrar")){
			String u = User.getText();
			char[] p = Senha.getPassword();
			
			Usuario loggedUser = tentaLogin(u, p);
			
			if (loggedUser != null) {
				System.out.println("Dados válidos. Login efetuado.");
				
				switch (loggedUser.getAcesso()){
				
				case "administrador":
					JOptionPane.showMessageDialog(null, "Dados válidos. Login efetuado.\nBem vindo, Administrador.");
					ti.setVisible(true);
					ti.setResizable(false);
					ti.setLocationRelativeTo(null);
					break;
					
				case "usuario":
					JOptionPane.showMessageDialog(null, "Dados válidos. Login efetuado.\nBem vindo, Gamer.");
					tg.setVisible(true);
					tg.setResizable(false);
					tg.setLocationRelativeTo(null);
					
				}
			}else{
				JOptionPane.showMessageDialog(null, "Login falhou.\nPor favor, digite credenciais válidas e tente novamente.", "Login", JOptionPane.ERROR_MESSAGE);
				User.setText("");
				Senha.setText("");
				User.requestFocus();
			}
		}
		
		if(e.getActionCommand().equals("Cancelar")){
			System.exit(0);
		}	
	}
	
	private static Usuario tentaLogin(String usr, char[] pwd) {
		try {
			ArrayList<Usuario> users = daoUsuario.getAll();
			for (Usuario u : users) {
				if (u.getLogin().equals(usr) && Arrays.equals(u.getSenha().toCharArray(), pwd))
					return u;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;		
	}
}
