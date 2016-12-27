package model.entity;

public class EstiloLuta {
	
	private Integer idEstilo;
	private String nomeEstilo;

	public EstiloLuta(Integer idEstilo, String nomeEstilo) {
		super();
		this.idEstilo = idEstilo;
		this.nomeEstilo = nomeEstilo;
	}

	public EstiloLuta() {
		this.idEstilo = null;
		this.nomeEstilo = null;
	}

	public String getNomeEstilo() {
		return nomeEstilo;
	}

	public void setNomeEstilo(String nomeEstilo) {
		this.nomeEstilo = nomeEstilo;
	}

	public Integer getIdEstilo() {
		return idEstilo;
	}

	public void setIdEstilo(Integer idEstilo) {
		this.idEstilo = idEstilo;
	}
	
	@Override
	public String toString() {
		return this.nomeEstilo;
	}
	
	public String toLongString() {
		String newLine = System.getProperty("line.separator");
		return "_ESTILO_LUTA_ " +
				"Nome: " + this.nomeEstilo + newLine;
	}
}
