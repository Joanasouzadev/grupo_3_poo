package com.lojaVendas.serratec.crud;

import com.lojaVendas.serratec.model.Cliente;

public interface ClienteRepository {
	
	public Cliente listarCliente(int idCliente );
	
	public void atualizarCliente(Cliente cliente);
	
	public void excluirCliente(int idCliente);
	
}
