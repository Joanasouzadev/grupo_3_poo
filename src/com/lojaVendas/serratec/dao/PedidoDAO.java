package com.lojaVendas.serratec.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lojaVendas.serratec.conexao.Conexao;
import com.lojaVendas.serratec.crud.PedidoRepository;
import com.lojaVendas.serratec.model.Cliente;
import com.lojaVendas.serratec.model.Pedido;
import com.lojaVendas.serratec.model.Produto;

public class PedidoDAO implements PedidoRepository {
	
	private Conexao conexao;
	private String schema;
	
	PreparedStatement pInclusao;
	PreparedStatement pExclusao;
	PreparedStatement pUpdate;
	PreparedStatement pListar;
	
	public PedidoDAO(Conexao conexao, String schema) { 
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusao();
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into "+ this.schema + ".pedido";	
		sql += " (dtEmissao, dtEntrega, valorTotal, observacao, IdCliente)";
		sql += " values ";
		sql += " (?, ?, ?, ?, ?)";
		try {
			this.pInclusao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	@Override
	public int cadastrarPedido(Pedido pedido) {

		try { 
			pInclusao.setDate(1, pedido.getDtEmissao());
			pInclusao.setDate(2, pedido.getDtEntrega());
			pInclusao.setDouble(3, pedido.getValorTotal());
			pInclusao.setString(4, pedido.getObservacao());
			pInclusao.setInt(5, pedido.getIdCliente().getIdCliente());
					
			return pInclusao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedido não incluído.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	private void prepararSqlListarPedido() {
		String sql = "select * from "+ this.schema + ".pedido";	
		sql += " where idPedido = ?";
		try {
			this.pListar =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	@Override
	public Pedido buscarPedido(int idPedido) {
		prepararSqlListarPedido();
		Pedido pedido = new Pedido();
		ResultSet resultSet = null;
		try {
			pListar.setInt(1, idPedido);
			
			resultSet = pListar.executeQuery();
			
            while (resultSet.next()) {
                pedido.setIdPedido(resultSet.getInt("idPedido"));
                pedido.setDtEmissao(resultSet.getDate("dtEmissao"));
                pedido.setDtEntrega(resultSet.getDate("dtEntrega"));
                pedido.setValorTotal(resultSet.getDouble("valorTota"));
                pedido.setObservacao(resultSet.getString("observacao"));
                pedido.getIdCliente().setIdCliente(resultSet.getInt("idCliente"));
            }	
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedido Listado.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
		}
		return pedido;

	}
	
	private void prepararSqlUpdate() {
		String sql = "update "+ this.schema + ".pedido set ";	
		sql += "dtEmissao = ?, dtEntrega = ? , valorTotal = ?, observacao = ?, idCliente = ?";
		sql += "where idPedido = ?";
		try {
			this.pUpdate =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
		
	@Override
	public void atualizarPedido(Pedido pedido) {
		prepararSqlUpdate();
		try {
			pUpdate.setDate(1, pedido.getDtEmissao());
			pUpdate.setDate(2, pedido.getDtEntrega());
			pUpdate.setDouble(3, pedido.getValorTotal());
			pUpdate.setString(4, pedido.getObservacao());
			pUpdate.setInt(5, pedido.getIdCliente().getIdCliente());
			pUpdate.setInt(6, pedido.getIdPedido());
			
			pUpdate.executeUpdate();
			
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedido não Atualizado.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
	}
	
		
	}

	private void prepararSqlExclusao() {
		String sql = "delete from "+ this.schema + ".pedido";	
		sql += " where idPedido = ?";
		
		try {
			this.pExclusao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	@Override
	public void excluirPedido(int idPedido) {
		prepararSqlExclusao();
		try {
			pExclusao.setInt(1, idPedido);
			
			pExclusao.executeUpdate();
			
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedido não Excluido.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
		}
		
	}

	
}
