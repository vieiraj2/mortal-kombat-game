package model.entity;

public class Personagem {
	
	private Integer idPersonagem;
	private Integer idMundo;
	private Integer idClassificacao;
	private Integer idArma;
	private Integer idEstilo;
	private Integer idAlinhamento;
	private String Nome;
	
	public Personagem(Integer idPersonagem, String Nome, Integer idMundo, Integer idClassificacao,
			Integer idArma, Integer idEstilo, Integer idAlinhamento) {
		super();
		this.setIdPersonagem(idPersonagem);
		this.setNome(Nome);
		this.setIdMundo(idMundo);
		this.setIdClassificacao(idClassificacao);
		this.setIdEstilo(idEstilo);
		this.setIdArma(idArma);
		this.setIdAlinhamento(idAlinhamento);
	}

	public Personagem() {
		this.setIdPersonagem(null);
		this.setNome(null);
		this.setIdMundo(null);
		this.setIdClassificacao(null);
		this.setIdEstilo(null);
		this.setIdArma(null);
		this.setIdAlinhamento(null);
	}

	public Integer getIdPersonagem() {
		return idPersonagem;
	}

	public void setIdPersonagem(Integer idPersonagem) {
		this.idPersonagem = idPersonagem;
	}

	public Integer getIdMundo() {
		return idMundo;
	}

	public void setIdMundo(Integer idMundo) {
		this.idMundo = idMundo;
	}

	public Integer getIdClassificacao() {
		return idClassificacao;
	}

	public void setIdClassificacao(Integer idClassificacao) {
		this.idClassificacao = idClassificacao;
	}

	public Integer getIdEstilo() {
		return idEstilo;
	}

	public void setIdEstilo(Integer idEstilo) {
		this.idEstilo = idEstilo;
	}

	public Integer getIdArma() {
		return idArma;
	}

	public void setIdArma(Integer idArma) {
		this.idArma = idArma;
	}

	public Integer getIdAlinhamento() {
		return idAlinhamento;
	}

	public void setIdAlinhamento(Integer idAlinhamento) {
		this.idAlinhamento = idAlinhamento;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
	
	public String toLongString() {
		String newLine = System.getProperty("line.separator");
		return "_PERSONAGEM " +
				"Nome: " + this.Nome + ", Mundo: " + this.idMundo + ", Classificação: " + this.idClassificacao + 
				", Arma: " + this.idArma + ", Estilo de Luta: " + this.idEstilo + ", Alinhamento: " + this.idAlinhamento + newLine;
	}
}
