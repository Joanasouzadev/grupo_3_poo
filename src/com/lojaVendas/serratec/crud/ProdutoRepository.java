package com.lojaVendas.serratec.crud;

import com.lojaVendas.serratec.model.Produto;

public interface ProdutoRepository {
	
	public int cadastrarproduto(Produto produto);
	
	public Produto listarProduto(int idProduto);
	
	public void atualizarProduto(Produto Produto);
	
	public void excluirProduto(int idProduto);
}
