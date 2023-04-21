package com.lojaVendas.serratec.crud;

import com.lojaVendas.serratec.model.Pedido;

public interface PedidoRepository {

	public int cadastrarPedido(Pedido pedido);
	
	public Pedido buscarPedido(int idPedido);
	
	public void atualizarPedido(Pedido pedido);
	
	public void excluirPedido(int idPedido);
	
}
