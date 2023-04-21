package com.lojaVendas.serratec.model;

public class PedidoItens  {
	
	private int idPedidoItens;
	private double valorUnitario;
	private int quantProduto;
	private double valorDesconto;
	private Produto produto;
	private Pedido pedido;
		
	public PedidoItens() {
		super();
	}

	public PedidoItens(int idPedidoItens, double valorUnitario, int quantProduto, double valorDesconto,
			Produto produto, Pedido pedido) {
		super();
		this.idPedidoItens = idPedidoItens;
		this.valorUnitario = valorUnitario;
		this.quantProduto = quantProduto;
		this.valorDesconto = valorDesconto;
		this.produto = produto;
	}

	public int getIdPedidoItens() {
		return idPedidoItens;
	}

	public void setIdPedidoItens(int idPedidoItens) {
		this.idPedidoItens = idPedidoItens;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getQuantProduto() {
		return quantProduto;
	}

	public void setQuantProduto(int quantProduto) {
		this.quantProduto = quantProduto;
	}

	public double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
