package com.lojaVendas.serratec.principal;

import java.sql.Date;
import java.util.Scanner;

import com.lojaVendas.serratec.conexao.Conexao;
import com.lojaVendas.serratec.conexao.DadosConexao;
import com.lojaVendas.serratec.dao.ClienteDAO;
import com.lojaVendas.serratec.dao.CreateDAO;
import com.lojaVendas.serratec.dao.PedidoDAO;
import com.lojaVendas.serratec.dao.PedidoItensDAO;
import com.lojaVendas.serratec.dao.ProdutoDAO;
import com.lojaVendas.serratec.model.Cliente;
import com.lojaVendas.serratec.model.Pedido;
import com.lojaVendas.serratec.model.PedidoItens;
import com.lojaVendas.serratec.model.Produto;


public class Principal {
	
	public static Conexao conexao;
	public static DadosConexao dadosConexao = new DadosConexao();
	
	public static ClienteDAO clienteDao;
	public static ProdutoDAO produtoDao;
	public static PedidoItensDAO pedidoItensDao;
	public static PedidoDAO pedidoDao;
	
	public static Scanner scam = new Scanner(System.in);
	
	public static final String BANCO = "grupo3";
	public static final String SCHEMA = "poo";
	public static final String LOCAL = "localhost";
	public static String USUARIO;
	public static String SENHA; 
	public static String PORTA;
	public static final String BD = "PostgreSql";
	
	public static void main(String[] args) {
		
		System.out.println("-------------SISTEMA LOJA G3------------");
		System.out.println("             Seja bem vindo!");
		System.out.println("----------------------------------------");
		System.out.println("INICIANDO CONFIGURAÇÃO DO BANCO DE DADOS");
		System.out.println("-----------------------------------------\n");
		
		  System.out.println("Digite o seu usuário: "); USUARIO = scam.next();
		  System.out.println("Digite a sua senha: "); SENHA = scam.next();
		  System.out.println("Digite a porta de acesso: "); PORTA = scam.next();
		 
		
		dadosConexao.setBanco(BANCO);
		dadosConexao.setLocal(LOCAL);
		dadosConexao.setUser(USUARIO);
		dadosConexao.setSenha(SENHA);
		dadosConexao.setPorta(PORTA);
		dadosConexao.setBd(BD);

		if (CreateDAO.createBD(BANCO, SCHEMA, dadosConexao)) {
			conexao = new Conexao(dadosConexao); 
			conexao.conect();
			clienteDao = new ClienteDAO(conexao, SCHEMA);
			produtoDao = new ProdutoDAO(conexao, SCHEMA);
			pedidoItensDao = new PedidoItensDAO(conexao, SCHEMA);
			pedidoDao = new PedidoDAO(conexao, SCHEMA);
			menuPrincipal();
		} else {
			System.out.println("Ocorreu um problema na criação do banco de dados");
		}
	}

	private static void menuPrincipal() {
		int opcao = 0; 
		
		do {
			System.out.println("\n MENU PRINCIPAL");
			System.out.println("----------------------");
			System.out.println("0: Cliente");
			System.out.println("1: Pedido");
			System.out.println("2: PedidoItens");
			System.out.println("3: Produto");
			System.out.println("4: Sair");
			
			System.out.print("Informe uma opção: ");
			
			opcao = scam.nextInt();
			 
			switch (opcao) {
			case 0:
				menuCliente();
				break;
			case 1: 
				menuProduto();
				break;
			case 2: 
				menuPedidoItens();
				break;
			case 3: 
				menuPedido();
				break;
			case 4:
				System.out.println("Programa Finalizado!");	
				conexao.disconect();
			}
			
		}while (opcao != 4);
	}
	
	private static void menuCliente() {
		int opcao = 0; 
		
		do {
			System.out.println("\n MENU ClIENTE");
			System.out.println("----------------------");
			System.out.println("0: Listar Cliente");
			System.out.println("1: Cadastro 5 Cliente Ficticios");
			System.out.println("2: Atualizar Cliente");
			System.out.println("3: Excluir Cliente");
			System.out.println("4: Voltar");
			
			System.out.print("Informe uma opção: ");
			
			opcao = scam.nextInt();
			 
			switch (opcao) {
			case 0:
				listarCliente();
				break;
			case 1: 
				cadastroClienteFicticios();
				break;
			case 2: 
				atualizarCliente();
				break;
			case 3: 
				excluirCliente();
				break;
			case 4:
				System.out.println("Voltando ao Menu Principal!");
				break;
			default:
				System.out.println("Opção inválida.");
			}
			
		}while (opcao != 4);
		
	}
	
	private static void cadastroClienteFicticios() {
		try {
			Cliente cliente01 = new Cliente("Lorenzo Barreto", "10596685502", Date.valueOf("1998-02-23"), 1, "ruz z", "2188956356"); 
			clienteDao.incluirCliente(cliente01);
			Cliente cliente02 = new Cliente("Carlos Batista", "10596687704", Date.valueOf("1995-03-14"), 2, "rua A", "2199865474");
			clienteDao.incluirCliente(cliente02);
			Cliente cliente03 = new Cliente("Lucia Maria", "14455599880", Date.valueOf("1996-02-15"), 3, "rua B", "2186896545");
			clienteDao.incluirCliente(cliente03);
			Cliente cliente04 = new Cliente("Luiza Garcia", "13355599880", Date.valueOf("1997-05-18"), 4, "rua C", "2198566545");
			clienteDao.incluirCliente(cliente04);
			Cliente cliente05 = new Cliente("Maria Violeta", "18858599880", Date.valueOf("2021-05-18"), 5, "rua F", "2199566545");
			clienteDao.incluirCliente(cliente05);
			
			System.out.println("Cadastro de cliente realizado com sucesso!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void excluirCliente() {
		try {
			
			System.out.println("Iniciando Exlusão Cliente");
			System.out.println("Informe ID do cliente: ");
			
			clienteDao.excluirCliente(scam.nextInt());
			
			System.out.println("Exclusão Realizada com Sucesso!");	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void atualizarCliente() {
		
		try {
			Cliente cliente = new Cliente();
			System.out.println("---------------------------------");
			System.out.println("Iniciando Atualização do Cliente");
			System.out.println("---------------------------------");
			
			System.out.print("Informe ID do cliente: ");
			cliente.setIdCliente(scam.nextInt());
			System.out.print("Informe o Nome: ");
			String nome = scam.nextLine();
			nome = scam.nextLine();
			cliente.setNome(nome);
			System.out.print("Informe o CPF: ");
			cliente.setCpf(scam.nextLine());
			System.out.print("Informe a Data de Nascimento (EX: YYYY-MM-DD): ");
			cliente.setDataNascimento(Date.valueOf(scam.nextLine()));
			System.out.print("Informe o Endereço: ");
			cliente.setEndereco(scam.nextLine());
			System.out.print("Informe o Telefone: ");
			cliente.setTelefone(scam.nextLine());
			
			clienteDao.atualizarCliente(cliente);
			
			System.out.println("-------------------------------------");
			System.out.println("Atualização Realizada com Sucesso!");	
			System.out.println("-------------------------------------");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void listarCliente() {
		try {
			System.out.println("Listando Cliente");
			
			System.out.print("Informe ID do cliente: ");
			
			Cliente cliente = clienteDao.listarCliente(scam.nextInt());
			
			System.out.println("-------------------------------------");
			System.out.println("Busca Realizada com Sucesso!");
			System.out.println("-------------------------------------");
			System.out.println("ID Cliente: " + cliente.getIdCliente());
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("CPF: " + cliente.getCpf());
			System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
			System.out.println("Endereço: " + cliente.getEndereco());
			System.out.println("Telefone: " + cliente.getTelefone() + "/n");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void menuProduto() {
		int opcao = 0; 
		
		do {
			System.out.println("\n MENU PRODUTO");
			System.out.println("----------------------");
			System.out.println("0: Cadastrar Produto");
			System.out.println("1: Listar Produto");
			System.out.println("2: Atualizar Produto");
			System.out.println("3: Excluir Produto");
			System.out.println("4: Sair");
			
			System.out.print("Informe uma opção: ");
			
			opcao = scam.nextInt();
			 
			switch (opcao) {
			case 0:
				cadastrarProduto();
				break;
			case 1: 
				listarProduto();
				break;
			case 2: 
				atualizarProduto();
				break;
			case 3:
				excluirProduto();
				break;
			case 4:
				System.out.println("Programa Finalizado!");
				break;
			default:
				System.out.println("Opção inválida.");
			}
			
		}while (opcao != 4);
	}

	private static void excluirProduto() {
		try {
			System.out.println("Iniciando Exlusão Produto");
			System.out.println("Informe ID do Produto: ");
			
			produtoDao.excluirProduto(scam.nextInt());
			
			System.out.println("Exclusão Realizada com Sucesso!");	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void atualizarProduto() {
		try {
			Produto produto = new Produto();
			System.out.println("Iniciando Atualização do Produto");
			
			System.out.print("Informe ID do Produto: ");
			produto.setIdProduto(scam.nextInt());
			System.out.print("Informe a Categoria: ");
			String categoria = scam.nextLine();
			categoria = scam.nextLine();
			produto.setDescricao(categoria);
			System.out.print("Informe o Valor Custo: ");
			produto.setValorCusto(scam.nextDouble());
			System.out.print("Informe o Valor de Venda");
			produto.setValorVenda(scam.nextDouble());	
			
			produtoDao.atualizarProduto(produto);
			
			System.out.println("Atualização Realizada com Sucesso!");	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void listarProduto() {
		try {
			System.out.println("Listando Produto");
			
			System.out.print("Informe ID do Produto: ");
			
			Produto produto = produtoDao.listarProduto(scam.nextInt());
			
			System.out.println("-------------------------------------");
			System.out.println("Busca Realizada com Sucesso!");
			System.out.println("-------------------------------------");
			System.out.println("ID Produto: " + produto.getIdProduto());
			System.out.println("Descrição: " + produto.getDescricao());
			System.out.println("Valor de Custo: " + produto.getValorCusto());
			System.out.println("Valor de Venda: " + produto.getValorVenda());
			System.out.println("Categoria: " + produto.getCategoria());
			System.out.println("/n");
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}

	private static void cadastrarProduto() {
		try {
			Produto produto01 = new Produto(1, "Bola Basquete", 49.50, 80.00, "Acessórios Esportivos" ); 
			produtoDao.incluirProduto(produto01);
			Produto produto02 = new Produto(2, "Bola Futebol", 35.50, 85.00, "Acessórios Esportivos" );
			produtoDao.incluirProduto(produto02);
			Produto produto03 = new Produto(3, "Luva de Boxe", 40.00, 89.90, "Acessórios Esportivos" );
			produtoDao.incluirProduto(produto03);
			Produto produto04 = new Produto(4, "Saco de Pancada", 74.40, 140.00, "Acessórios Esportivos" );
			produtoDao.incluirProduto(produto04);
			Produto produto05 = new Produto(5, "Nike Air", 135.90, 850.00, "Tênis Esportivo" );
			produtoDao.incluirProduto(produto05);
			Produto produto06 = new Produto(6, "Casaco Adidas", 39.50, 99.00, "Roupa Esportiva" );
			produtoDao.incluirProduto(produto06);
			Produto produto07 = new Produto(7, "Chuteira Umbro", 145.50, 385.00, "Tênis Esportivo" );
			produtoDao.incluirProduto(produto07);
			Produto produto08 = new Produto(8, "luvas goleiro", 45.50, 85.00, "Acessórios Esportivos" );
			produtoDao.incluirProduto(produto08);
			Produto produto09 = new Produto(9, "Camisa Bulls", 55.00, 25.00, "Roupa Esportiva" );
			produtoDao.incluirProduto(produto09);
			Produto produto010 = new Produto(10, "Conjunto Bola Raquete Tênis", 125.00, 275.00, "Acessórios Esportivos" );
			produtoDao.incluirProduto(produto010);
			
			System.out.println("Cadastro de cliente realizado com sucesso!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void menuPedidoItens() {
int opcao = 0; 
		
		do {
			System.out.println("\n MENU PEDIDO ITENS");
			System.out.println("----------------------");
			System.out.println("0: Cadastrar Pedido Itens");
			System.out.println("1: Listar Pedido Itens");
			System.out.println("2: Atualizar Pedido Itens");
			System.out.println("3: Excluir Pedido Itens");
			System.out.println("4: Sair");
			
			System.out.print("Informe uma opção: ");
			
			opcao = scam.nextInt();
			 
			switch (opcao) {
			case 0:
				cadastrarPedidoItens();
				break;
			case 1: 
				listarPedidoItens();
				break;
			case 2: 
				atualizarPedidoItens();
				break;
			case 3:
				excluirPedidoItens();
				break;
			case 4:
				System.out.println("Programa Finalizado!");
				break;
			default:
				System.out.println("Opção inválida.");
			}
			
		}while (opcao != 4);
	}
	
	
		private static void excluirPedidoItens() {
			try {
				System.out.println("Iniciando Exlusão dos Pedidos Itens");
				System.out.println("Informe ID do Pedido Itens: ");
				
				pedidoItensDao.excluirPedidoItens(scam.nextInt());
				
				System.out.println("Exclusão Realizada com Sucesso!");	
			}catch(Exception e) {
				e.printStackTrace();
			}	
	}

	private static void atualizarPedidoItens() {
		try {
			PedidoItens pedidoItens = new PedidoItens();
			System.out.println("Iniciando Atualização dos Itens do Pedido ");
			
			System.out.print("Informe ID do Pedido Itens: ");
			pedidoItens.setIdPedidoItens(scam.nextInt());
			System.out.print("Informe o Valor Unitario: ");
			Double valorUnitario = scam.nextDouble();
			valorUnitario = scam.nextDouble();
			pedidoItens.setValorUnitario(valorUnitario);
			System.out.print("Informe a Quantidade de Produto: ");
			pedidoItens.setQuantProduto(scam.nextInt());
			System.out.print("Informe o Valor de Desconto");
			pedidoItens.setValorDesconto(scam.nextDouble());
			System.out.println("Informe o ID do produto: ");
			pedidoItens.getProduto().setIdProduto(scam.nextInt());
			System.out.println("Informe o ID do pedido: ");
			pedidoItens.getPedido().setIdPedido(scam.nextInt());
			
			pedidoItensDao.atualizarPedidoItens(pedidoItens);
			
			System.out.println("Atualização Realizada com Sucesso!");	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void listarPedidoItens() {
		try {
			System.out.println("Listando Pedido Itens");
			
			System.out.print("Informe ID do PedidoItens: ");
			
			PedidoItens pedidoItens = pedidoItensDao.buscarPedidoItens(scam.nextInt());
			
			System.out.println("-------------------------------------");
			System.out.println("Busca Realizada com Sucesso!");
			System.out.println("-------------------------------------");
			System.out.println("ID Pedido Itens: " + pedidoItens.getIdPedidoItens());
			System.out.println("valorUnitario: " + pedidoItens.getValorUnitario());
			System.out.println("quantProduto: " + pedidoItens.getQuantProduto());
			System.out.println("valorDesconto: " + pedidoItens.getValorDesconto());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void cadastrarPedidoItens() {
		try {
			Produto produto01 = new Produto();
			produto01.setIdProduto(3);
			Pedido pedido = new Pedido();
			pedido.setIdPedido(0);
			PedidoItens pedidoItens01 = new PedidoItens(1, 5000, 5, 6000, produto01, pedido);
			pedidoItensDao.incluirPedidoItens(pedidoItens01);
			PedidoItens pedidoItens02 = new PedidoItens(2, 6000, 2, 6000, produto01, pedido);
			pedidoItensDao.incluirPedidoItens(pedidoItens02);
			PedidoItens pedidoItens03 = new PedidoItens(3, 7000, 1, 6000, produto01, pedido);
			pedidoItensDao.incluirPedidoItens(pedidoItens03);
			PedidoItens pedidoItens04 = new PedidoItens(4, 8000, 4, 6000, produto01, pedido);
			pedidoItensDao.incluirPedidoItens(pedidoItens04);
			PedidoItens pedidoItens05 = new PedidoItens(5, 9000, 6, 6000, produto01, pedido);
			pedidoItensDao.incluirPedidoItens(pedidoItens05);
			
			System.out.println("Cadastro de Itens Pedido realizado com sucesso!");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void menuPedido() {
		int opcao = 0; 
		
		do {
			System.out.println("\n MENU PEDIDO");
			System.out.println("----------------------");
			System.out.println("0: Cadastrar Pedido");
			System.out.println("1: Alterar Pedido");
			System.out.println("2: Cancelar Pedido");
			System.out.println("3: Localizar Pedido");
			System.out.println("4: Adicionar Produto no Pedido");
			System.out.println("5: Resumo sem Produtos");
			System.out.println("6: Resumo com Produtos");
			System.out.println("7: Sair");
			
			System.out.print("Informe uma opção: ");
			
			opcao = scam.nextInt();
			 
			switch (opcao) {
			case 0:
				cadastrarPedido();
				break;
			case 1: 
				alterarPedido();
				break;
			case 2: 
				cancelarPedido();
				break;
			case 3: 
				localizarPedido();
				break;
			case 4: 
				adicionarProdutoNoPedido();
				break;
			case 5: 
				resumoSemProdutos();
				break;
			case 6: 
				resumoComProdutos();
				break;
			case 7:
				System.out.println("Programa Finalizado!");
				break;
			default:
				System.out.println("Opção inválida.");
			}
			
		}while (opcao != 4);
	}
	
	private static void cadastrarPedido() {
		
		try {
			Pedido pedido = new Pedido();
			Cliente cliente = new Cliente();
			System.out.println("---------------------------------");
			System.out.println("Iniciando Cadastro do Pedido");
			System.out.println("---------------------------------");
			
			System.out.print("Informe ID do pedido: ");
			pedido.setIdPedido(scam.nextInt());
			System.out.print("Informe a Data Emissão: ");
			String dtEmissao = scam.nextLine();
			dtEmissao = scam.nextLine();
			pedido.setDtEmissao(Date.valueOf(dtEmissao));
			System.out.print("Informe a Data de Entrega: ");
			pedido.setDtEntrega(Date.valueOf(scam.nextLine()));
			System.out.print("Informe o Valor Total): ");
			pedido.setValorTotal(scam.nextDouble());
			System.out.print("Informe a Observação: ");
			pedido.setObservacao(scam.nextLine());
			System.out.print("Informe o ID do Cliente: ");
			cliente.setIdCliente(scam.nextInt());
			pedido.setIdCliente(cliente);
			
			pedidoDao.cadastrarPedido(pedido);
			
			System.out.println("-------------------------------------");
			System.out.println("Cadastro Realizada com Sucesso!");	
			System.out.println("-------------------------------------");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	private static void alterarPedido() {
		try {
			Pedido pedido = new Pedido();
			Cliente cliente = new Cliente();
			System.out.println("---------------------------------");
			System.out.println("Iniciando Atualização do Pedido");
			System.out.println("---------------------------------");
			
			System.out.print("Informe ID do pedido: ");
			pedido.setIdPedido(scam.nextInt());
			System.out.print("Informe a Data Emissão: ");
			String dtEmissao = scam.nextLine();
			dtEmissao = scam.nextLine();
			pedido.setDtEmissao(Date.valueOf(dtEmissao));
			System.out.print("Informe a Data de Entrega: ");
			pedido.setDtEntrega(Date.valueOf(scam.nextLine()));
			System.out.print("Informe o Valor Total): ");
			pedido.setValorTotal(scam.nextDouble());
			System.out.print("Informe a Observação: ");
			pedido.setObservacao(scam.nextLine());
			System.out.print("Informe o ID do Cliente: ");
			cliente.setIdCliente(scam.nextInt());
			pedido.setIdCliente(cliente);
			
			pedidoDao.atualizarPedido(pedido);
			
			System.out.println("-------------------------------------");
			System.out.println("Atualização Realizada com Sucesso!");	
			System.out.println("-------------------------------------");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void cancelarPedido() {
		try {
			System.out.println("Iniciando Exlusão Pedido");
			System.out.println("Informe ID do Pedido: ");
			
			pedidoDao.excluirPedido(scam.nextInt());
			
			System.out.println("Exclusão Realizada com Sucesso!");	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void localizarPedido() {
		try {
			System.out.println("Listando Pedido");
			
			System.out.print("Informe ID do Pedido: ");
			
			Pedido pedido = pedidoDao.buscarPedido(scam.nextInt());
			
			System.out.println("-------------------------------------");
			System.out.println("Busca Realizada com Sucesso!");
			System.out.println("-------------------------------------");
			System.out.println("ID Pedido: " + pedido.getIdPedido());
			System.out.println("Data de Emissão: " + pedido.getDtEmissao());
			System.out.println("Data de Entrega: " + pedido.getDtEntrega());
			System.out.println("Valor Total: " + pedido.getValorTotal());
			System.out.println("Observação: " + pedido.getObservacao());
			System.out.println("ID Cliente: " + pedido.getIdCliente().getIdCliente() + "/n");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void adicionarProdutoNoPedido() {
		// TODO Auto-generated method stub
		
	}
	private static void resumoSemProdutos() {
		// TODO Auto-generated method stub
		
	}
	private static void resumoComProdutos() {
		// TODO Auto-generated method stub
		
	}	
}

