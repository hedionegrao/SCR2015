package br.com.scr.vo;

public class MesaVO {
	private int Id;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	private String Descricao;
	
	public MesaVO(int id, String descricao){
		this.Id = id;
		this.Descricao = descricao;
		
	}

}
