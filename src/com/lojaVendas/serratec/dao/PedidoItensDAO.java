package com.lojaVendas.serratec.dao;

import java.sql.PreparedStatement;

import com.lojaVendas.serratec.conexao.Conexao;
import com.lojaVendas.serratec.crud.PedidoItensRepository;
import com.lojaVendas.serratec.model.PedidoItens;

public class PedidoItensDAO implements PedidoItensRepository {
	
	private Conexao conexao;
	private String schema;
	
	PreparedStatement pInclusao;
	PreparedStatement pExclusao;
	PreparedStatement pUpdate;
	
	
	public PedidoItensDAO(Conexao conexao, String schema) { 
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusao();
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into "+ this.schema + ".pedidoItens";	
		sql += " (valorUnitario, quantProduto, valorDesconto, idProduto, idPedido)";
		sql += " values ";
		sql += " (?, ?, ?, ?, ?)";
		try {
			this.pInclusao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int incluirPedidoItens(PedidoItens pedidoItens) {
		try {
			pInclusao.setDouble(1, pedidoItens.getValorUnitario());
			pInclusao.setInt(2, pedidoItens.getQuantProduto());
			pInclusao.setDouble(3, pedidoItens.getValorDesconto());
			pInclusao.setInt(4, pedidoItens.getProduto().getIdProduto());
			pInclusao.setInt(5, pedidoItens.getPedido().getIdPedido());
		
			return pInclusao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedidoItens não incluído.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}

	@Override
	public int cadastrarPedidoItens(PedidoItensRepository pedidoItens) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PedidoItens buscarPedidoItens(int idPedidoItens) {
		// TODO Auto-generated method stub
		return null;
	}
	private void prepararSqlUpdate() {
		String sql = "update "+ this.schema + ".pedidoItens set ";	
		sql += " valorUnitario = ?, quantProduto = ?, valorDesconto = ?, idProduto = ?, idPedido = ?";
		sql += "where idPedidoItens = ?";
		try {
			this.pUpdate =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	@Override
	public void atualizarPedidoItens(PedidoItens pedidoItens) {
		prepararSqlUpdate();
		try {
			pUpdate.setDouble(1, pedidoItens.getValorUnitario());
			pUpdate.setDouble(2, pedidoItens.getQuantProduto());
			pUpdate.setDouble(3, pedidoItens.getValorDesconto());
			pUpdate.setInt(5, pedidoItens.getProduto().getIdProduto());
			pUpdate.setInt(5, pedidoItens.getPedido().getIdPedido());
			
			pUpdate.executeUpdate();
			
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedidoItens não Atualizado.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
		}		
		
	}
	private void prepararSqlExclusao() {
	String sql = "delete from "+ this.schema + ".produtoItens";	
	sql += " where idProdutoItens = ?";
	
	try {
		this.pExclusao =  conexao.getC().prepareStatement(sql);
	} catch (Exception e) {
		System.err.println(e);
		e.printStackTrace();
	}
}
	@Override
	public void excluirPedidoItens(int idPedidoItens) {
			prepararSqlExclusao();
			try {
				pExclusao.setInt(1, idPedidoItens);
				
				pExclusao.executeUpdate();
				
			} catch (Exception e) {
				if (e.getLocalizedMessage().contains("is null")) {
					System.err.println("\nPedidoItens não Excluido.\nVerifique se foi chamado o conect:\n" + e);				
				} else {				
					System.err.println(e);
					e.printStackTrace();
				}
			}
		}

	@Override
	public void atualizarPedidoItens(PedidoItensRepository pedidoItens) {
		// TODO Auto-generated method stub
		
	}	
	}