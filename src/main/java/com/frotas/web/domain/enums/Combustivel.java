package com.frotas.web.domain.enums;

public enum Combustivel {
	DIESEL(1, "Óleo Diesel"),
	ETANOL(2, "Etanol"),
	GASNATURAL(3, "Gás Natural"),
	GASOLINAADTIVADA(4, "Gasolina Adtivada"),
	GASOLINACOMUM(5, "Gasolina Comum");
	
	private Integer codigo;
	private String nome;
	
	private Combustivel(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}
	
	public static Combustivel toEnum (Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for (Combustivel c : Combustivel.values()) {
			if (codigo.equals(c.getCodigo())) {
				return c;
			}
		}
		
		throw new IllegalArgumentException("Código não encontrado: " + codigo);
	}


}
