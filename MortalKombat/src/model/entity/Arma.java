package model.entity;

public class Arma {
	
	private Integer idArma;
	private String nomeArma;
	private Integer ataqueArma;

	public Arma(Integer idArma, String nomeArma, Integer ataqueArma) {
		super();
		this.idArma = idArma;
		this.nomeArma = nomeArma;
		this.ataqueArma = ataqueArma;
	}

	public void setAtaque(int ataque) {
		ataqueArma = ataque;
	}

	public Arma() {
		this.idArma = null;
		this.nomeArma = null;
		this.ataqueArma = null;
	}

	public String getNomeArma() {
		return nomeArma;
	}

	public void setNomeArma(String nomeArma) {
		this.nomeArma = nomeArma;
	}

	public Integer getIdArma() {
		return idArma;
	}

	public void setIdArma(Integer idArma) {
		this.idArma = idArma;
	}

	public Integer getAtaqueArma() {
		return ataqueArma;
	}

	public void setAtaqueArma(Integer ataqueArma) {
		this.ataqueArma = ataqueArma;
	}
	
	@Override
	public String toString() {
		return this.nomeArma;
	}
	
	public String toLongString() {
		String newLine = System.getProperty("line.separator");
		return "_ARMA_ " +
				"Nome: " + this.nomeArma + ", Ataque: " + this.ataqueArma + newLine;
	}
}
