package com.lojaVendas.serratec.model;

public class Produto {
	
	private int idProduto;
	private String descricao;
	private double valorCusto;
	private double valorVenda;
	private String categoria;
	

	public Produto() {
		super();
	}

	public Produto(int idProduto, String descricao, double valorCusto, double valorVenda, String categoria) {
		super();
		this.idProduto = idProduto;
		this.descricao = descricao;
		this.valorCusto = valorCusto;
		this.valorVenda = valorVenda;
		this.categoria = categoria;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(double valorCusto) {
		this.valorCusto = valorCusto;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	
}
