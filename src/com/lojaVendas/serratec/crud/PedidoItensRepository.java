package com.lojaVendas.serratec.crud;

import com.lojaVendas.serratec.model.Pedido;
import com.lojaVendas.serratec.model.PedidoItens;

public interface PedidoItensRepository {
	
public int cadastrarPedidoItens(PedidoItensRepository pedidoItens);
	
	public PedidoItens buscarPedidoItens(int idPedidoItens);
	
	public void atualizarPedidoItens(PedidoItensRepository pedidoItens);
	
	public void excluirPedidoItens(int idPedidoItens);

	void atualizarPedidoItens(PedidoItens pedidoItens);

	
}
