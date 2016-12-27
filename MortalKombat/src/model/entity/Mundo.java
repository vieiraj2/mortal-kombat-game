package model.entity;

public class Mundo {

	private Integer idMundo;
	private String nomeMundo;

	public Mundo(Integer idMundo, String nomeMundo) {
		super();
		this.setIdMundo(idMundo);
		this.nomeMundo = nomeMundo;
	}

	public Mundo() {
		this.setIdMundo(null);
		this.nomeMundo = null;
	}

	public String getNomeMundo() {
		return nomeMundo;
	}

	public void setNomeMundo(String nomeMundo) {
		this.nomeMundo = nomeMundo;
	}

	public Integer getIdMundo() {
		return idMundo;
	}

	public void setIdMundo(Integer idMundo) {
		this.idMundo = idMundo;
	}
	
	@Override
	public String toString() {
		return this.nomeMundo;
	}
	
	public String toLongString() {
		String newLine = System.getProperty("line.separator");
		return "_MUNDO_ " +
				"Nome: " + this.nomeMundo + newLine;
	}
}
