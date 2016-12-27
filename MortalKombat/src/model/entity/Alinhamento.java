package model.entity;

public class Alinhamento {
	
	private Integer idAlinhamento;
	private String nomeAlinhamento;

	public Alinhamento(Integer idAlinhamento, String nomeAlinhamento) {
		super();
		this.idAlinhamento = idAlinhamento;
		this.nomeAlinhamento = nomeAlinhamento;
	}

	public Alinhamento() {
		this.idAlinhamento = null;
		this.nomeAlinhamento = null;
	}
	
	public String getNomeAlinhamento() {
		return nomeAlinhamento;
	}

	public void setNomeAlinhamento(String nomeAlinhamento) {
		this.nomeAlinhamento = nomeAlinhamento;
	}

	public Integer getIdAlinhamento() {
		return idAlinhamento;
	}

	public void setIdAlinhamento(Integer idAlinhamento) {
		this.idAlinhamento = idAlinhamento;
	}
	
	@Override
	public String toString() {
		return this.nomeAlinhamento;
	}
	
	public String toLongString() {
		String newLine = System.getProperty("line.separator");
		return "_ALINHAMENTO_ " +
				"Nome: " + this.nomeAlinhamento + newLine;
	}
}
