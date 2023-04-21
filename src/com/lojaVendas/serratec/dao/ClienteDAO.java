package com.lojaVendas.serratec.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lojaVendas.serratec.conexao.Conexao;
import com.lojaVendas.serratec.crud.ClienteRepository;
import com.lojaVendas.serratec.model.Cliente;

public class ClienteDAO implements ClienteRepository{
	
	private Conexao conexao;
	private String schema;
	
	PreparedStatement pInclusao;
	PreparedStatement pExclusao;
	PreparedStatement pUpdate;
	PreparedStatement pListar;
	
	public ClienteDAO(Conexao conexao, String schema) { 
		this.conexao = conexao;
		this.schema = schema;
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into "+ this.schema + ".cliente";	
		sql += " (nome, cpf, dataNascimento, endereco, telefone)";
		sql += " values ";
		sql += " (?, ?, ?, ?, ?)";
		try {
			this.pInclusao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	public int incluirCliente(Cliente cliente) {
		prepararSqlInclusao();
		try {
			pInclusao.setString(1, cliente.getNome());
			pInclusao.setString(2, cliente.getCpf());
			pInclusao.setDate(3, cliente.getDataNascimento());
			pInclusao.setString(4, cliente.getEndereco());
			pInclusao.setString(5, cliente.getTelefone());
			
			return pInclusao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nCliente não incluído.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}

	private void prepararSqlListarCliente() {
		String sql = "select * from "+ this.schema + ".cliente";	
		sql += " where idCliente = ?";
		try {
			this.pListar =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	@Override
	public Cliente listarCliente(int idCliente) {
		prepararSqlListarCliente();
		Cliente cliente = new Cliente();
		ResultSet resultSet = null;
		try {
			pListar.setInt(1, idCliente);
			
			resultSet = pListar.executeQuery();
			
            while (resultSet.next()) {
                cliente.setNome(resultSet.getString("nome"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setDataNascimento(resultSet.getDate("dataNascimento"));
                cliente.setEndereco(resultSet.getString("endereco"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setIdCliente(resultSet.getInt("idCliente"));
            }	
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nCliente Listado.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
		}
		return cliente;
	}
	private void prepararSqlUpdate() {
		String sql = "update "+ this.schema + ".cliente set ";	
		sql += " nome = ?, cpf = ?, dataNascimento = ?, endereco = ?, telefone = ?";
		sql += "where idCliente = ?";
		try {
			this.pUpdate =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	@Override
	public void atualizarCliente(Cliente cliente) {
		prepararSqlUpdate();
		try {
			pUpdate.setString(1, cliente.getNome());
			pUpdate.setString(2, cliente.getCpf());
			pUpdate.setDate(3, cliente.getDataNascimento());
			pUpdate.setString(4, cliente.getEndereco());
			pUpdate.setString(5, cliente.getTelefone());
			pUpdate.setInt(6, cliente.getIdCliente());
			
			pUpdate.executeUpdate();
			
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nCliente não Atualizado.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
		}		
	}

	private void prepararSqlExclusao() {
		String sql = "delete from "+ this.schema + ".cliente";	
		sql += " where idCliente = ?";
		
		try {
			this.pExclusao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	@Override
	public void excluirCliente(int idCliente) {
		prepararSqlExclusao();
		try {
			pExclusao.setInt(1, idCliente);
			
			pExclusao.executeUpdate();
			
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nCliente não Excluido.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
		}
	}
}
	
