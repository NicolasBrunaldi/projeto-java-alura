package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.Relatorio;

@Controller
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {

	@Autowired
	private ProdutoDAO produtoDAO;

	@RequestMapping( method = RequestMethod.GET)
	@ResponseBody
	public Relatorio geraRelatorio(@RequestParam(value = "data", required = false) String data) throws ParseException {

		Relatorio relatorio = new Relatorio();
		relatorio.setDataGeracao(Calendar.getInstance());
		
		if(data != null && !data.trim().isEmpty()) {
			
			SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
			Date dataFormatada = formatador.parse(data);
			Calendar dataFinal = Calendar.getInstance();
			dataFinal.setTime(dataFormatada);
			
			List<Produto> listaPorData = produtoDAO.listaPorData(dataFinal);
			relatorio.setQuantidade(listaPorData.size());
			relatorio.setProdutos(listaPorData);

			
			return relatorio;	
		}
		List<Produto> produtos = produtoDAO.listar();
		relatorio.setQuantidade(produtos.size());
		relatorio.setProdutos(produtos);
		
		
		return relatorio;
		
	}
}
