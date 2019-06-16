package util;

public enum TipoGrafico {

	AREA ("Area"), BARRA ("Barra"), 
	LINHA ("Linha"), PIZZA("Pizza");
	
	private String descricao;
	
	/**
	 * 
	 */
	private TipoGrafico(String descricao) {
	
		this.descricao = descricao;
	}
	
	public String getValor() {
		return this.descricao;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getValor();
	}
	
}
