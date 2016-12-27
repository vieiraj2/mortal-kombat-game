package model.entity;

public class Classificacao {

	private Integer idClassificacao;
	private String nomeClassificacao;

	public Classificacao(Integer idClassificacao, String nomeClassificacao) {
		super();
		this.idClassificacao = idClassificacao;
		this.nomeClassificacao = nomeClassificacao;
	}

	public Classificacao() {
		this.idClassificacao = null;
		this.nomeClassificacao = null;
	}

	public String getNomeClassificacao() {
		return nomeClassificacao;
	}

	public void setNomeClassificacao(String nomeClassificacao) {
		this.nomeClassificacao = nomeClassificacao;
	}

	public Integer getIdClassificacao() {
		return idClassificacao;
	}

	public void setIdClassificacao(Integer idClassificacao) {
		this.idClassificacao = idClassificacao;
	}
	
	@Override
	public String toString() {
		return this.nomeClassificacao;
	}
	
	public String toLongString() {
		String newLine = System.getProperty("line.separator");
		return "_CLASSIFICAÇÃO_ " +
				"Nome: " + this.nomeClassificacao + newLine;
	}
}
