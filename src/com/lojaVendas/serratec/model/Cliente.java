package com.lojaVendas.serratec.model;

import java.sql.Date;

public class Cliente extends Pessoa{
	
	private int idCliente;
	private String endereco;
	private String telefone;
	
	public Cliente(String nome, String cpf, Date dataNascimento, int idCliente, String endereco, String telefone) {
		super(nome, cpf, dataNascimento);
		this.idCliente = idCliente;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public Cliente() {
		super();
	}

	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
