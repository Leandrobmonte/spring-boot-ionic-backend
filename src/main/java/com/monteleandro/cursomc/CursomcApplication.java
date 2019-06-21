package com.monteleandro.cursomc;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.monteleandro.cursomc.domain.Categoria;
import com.monteleandro.cursomc.domain.Cidade;
import com.monteleandro.cursomc.domain.Cliente;
import com.monteleandro.cursomc.domain.Endereco;
import com.monteleandro.cursomc.domain.Estado;
import com.monteleandro.cursomc.domain.Pagamento;
import com.monteleandro.cursomc.domain.PagamentoComBoleto;
import com.monteleandro.cursomc.domain.PagamentoComCartao;
import com.monteleandro.cursomc.domain.Pedido;
import com.monteleandro.cursomc.domain.Produto;
import com.monteleandro.cursomc.domain.enums.EstadoPagamento;
import com.monteleandro.cursomc.domain.enums.TipoCliente;
import com.monteleandro.cursomc.repositories.CategoriaRepository;
import com.monteleandro.cursomc.repositories.CidadeRepository;
import com.monteleandro.cursomc.repositories.ClienteRepository;
import com.monteleandro.cursomc.repositories.EnderecoRepository;
import com.monteleandro.cursomc.repositories.EstadoRepository;
import com.monteleandro.cursomc.repositories.PagamentoRepository;
import com.monteleandro.cursomc.repositories.PedidoRepository;
import com.monteleandro.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 =  new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "mouse", 20.00);
		Produto p2 = new Produto (null, "Impressora", 800.00);
		Produto p3 = new Produto (null, "Computador", 2000.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 =  new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente (null, "Maria Silva ","maria@gmail.com", "363562146", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27368546", "94981344029"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "apto 300", "Jardim", "38264582", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Cemtro", "9365845", cli1, c2);
		
		cli1.getEndereco().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido (null, sdf.parse("30/09/2017 10:32"), cli1, e1 );
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:02"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("10/10/2017 10:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}
