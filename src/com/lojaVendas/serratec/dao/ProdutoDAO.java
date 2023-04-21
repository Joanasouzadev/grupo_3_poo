package com.lojaVendas.serratec.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lojaVendas.serratec.conexao.Conexao;
import com.lojaVendas.serratec.crud.ProdutoRepository;
import com.lojaVendas.serratec.model.Cliente;
import com.lojaVendas.serratec.model.Produto;

public class ProdutoDAO implements ProdutoRepository {
	
	private Conexao conexao;
	private String schema;
	
	PreparedStatement pInclusao;
	PreparedStatement pListar;
	PreparedStatement pExclusao;
	PreparedStatement pUpdate;
	
	public ProdutoDAO(Conexao conexao, String schema) { 
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusao();
	}	
	private void prepararSqlInclusao() {
		String sql = "insert into "+ this.schema + ".produto";	
		sql += " (descricao, valorCusto, valorVenda, categoria)";
		sql += " values ";
		sql += " (?, ?, ?, ?)";
		try {
			this.pInclusao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	public int incluirProduto(Produto produto) {
		try {
			pInclusao.setString(1, produto.getDescricao());
			pInclusao.setDouble(2, produto.getValorCusto());
			pInclusao.setDouble(3, produto.getValorVenda());
			pInclusao.setString(4, produto.getCategoria());
			
			return pInclusao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nProduto não incluído.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	@Override
	public int cadastrarproduto(Produto produto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void prepararSqlListarProduto() {
		String sql = "select * from "+ this.schema + ".produto";	
		sql += " where idProduto = ?";
		try {
			this.pListar =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	@Override
	public Produto listarProduto(int idProduto) {
		prepararSqlListarProduto();
		Produto produto = new Produto();
		ResultSet resultSet = null;
		try {
			pListar.setInt(1, idProduto);
			
			resultSet = pListar.executeQuery();
			
            while (resultSet.next()) {
                produto.setCategoria(resultSet.getString("categoria"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setIdProduto(resultSet.getInt("idProduto"));
                produto.setValorCusto(resultSet.getDouble("valorCusto"));
                produto.setValorVenda(resultSet.getDouble("valorVenda"));
            }	
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nProduto Listado.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
		}
		return produto;
	}

	private void prepararSqlUpdate() {
		String sql = "update "+ this.schema + ".produto set ";	
		sql += " descricao = ?, valorCusto = ?, valorVenda = ?, categoria = ?";
		sql += "where idProduto = ?";
		try {
			this.pUpdate =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	@Override
	public void atualizarProduto(Produto Produto) {
		prepararSqlUpdate();
		try {
			pUpdate.setString(1, Produto.getDescricao());
			pUpdate.setDouble(2, Produto.getValorCusto());
			pUpdate.setDouble(3, Produto.getValorVenda());
			pUpdate.setString(4, Produto.getCategoria());
			
			pUpdate.executeUpdate();
			
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nProduto não Atualizado.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
		}		
		
	}
	private void prepararSqlExclusao() {
		String sql = "delete from "+ this.schema + ".produto";	
		sql += " where idProduto = ?";
		
		try {
			this.pExclusao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	@Override
	public void excluirProduto(int idProduto) {
		prepararSqlExclusao();
		try {
			pExclusao.setInt(1, idProduto);
			
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
	
		
		
		
	}

