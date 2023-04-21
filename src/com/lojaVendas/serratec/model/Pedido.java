package com.lojaVendas.serratec.model;

import java.sql.Date;
import java.util.List;

public class Pedido {
	
	private int idPedido;
	private Date dtEmissao;
	private Date dtEntrega;
	private double valorTotal;
	private String observacao;
	private Cliente idCliente;
		
	public Pedido() {
		super();
	}

	public Pedido(int idPedido, Date dtEmissao, Date dtEntrega, double valorTotal, String observacao,
			Cliente idCliente) {
		super();
		this.idPedido = idPedido;
		this.dtEmissao = dtEmissao;
		this.dtEntrega = dtEntrega;
		this.valorTotal = valorTotal;
		this.observacao = observacao;
		this.idCliente = idCliente;
	
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDtEmissao() {
		return dtEmissao;
	}

	public void setDtEmissao(Date dtEmissao) {
		this.dtEmissao = dtEmissao;
	}

	public Date getDtEntrega() {
		return dtEntrega;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public void setDtEntrega(Date dtEntrega) {
		this.dtEntrega = dtEntrega;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}	
}