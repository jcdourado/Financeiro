package model;

public class Conta {
	private int id;
	private int frequencia;
	private String nome;
	private String descricao;
	private float valor;
	private String usuario;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
