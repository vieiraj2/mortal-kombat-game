package model.entity;

public class Usuario {
	
	private Integer idUsuario;
	private String Login;
	private String Senha;
	private String Acesso;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public String getAcesso() {
		return Acesso;
	}
	public void setAcesso(String acesso) {
		Acesso = acesso;
	}
	
	public Usuario() {
		this.idUsuario = null;
		this.Login = null;
		this.Senha = null;
		this.Acesso = null;
	}
	
	public Usuario(Integer idUsuario, String Login, String Senha, String Acesso) {
		this.idUsuario = idUsuario;
		this.Login = Login;
		this.Senha = Senha;
		this.Acesso = Acesso;
	}
	
	@Override
	public String toString() {
		return this.getLogin();
	}
}
