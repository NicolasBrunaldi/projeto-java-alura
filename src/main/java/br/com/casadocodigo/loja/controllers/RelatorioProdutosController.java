package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
@RequestMapping("relatorio-produtos")
public class RelatorioProdutosController {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {
		return "relatorio-produtos/form";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView geraRelatorio(@RequestParam(value = "data", required = false) @NotBlank String data) throws ParseException {
		
		ModelAndView modelAndView = new ModelAndView("/relatorio-produtos/lista");
		
		Calendar dataGeracao = Calendar.getInstance();
		
		Integer quantidade;
		if(data != null && !data.trim().isEmpty()) {
			
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFormatada = formatador.parse(data);
			Calendar dataFinal = Calendar.getInstance();
			dataFinal.setTime(dataFormatada);
			
			List<Produto> listaPorData = produtoDAO.listaPorData(dataFinal);
			quantidade = listaPorData.size();
			
			modelAndView.addObject("dataGeracao", dataGeracao);
			modelAndView.addObject("quantidade", quantidade);
			modelAndView.addObject("produtos", listaPorData);
			
			return modelAndView;	
		}
		List<Produto> produtos = produtoDAO.listar();
		quantidade = produtos.size();
		
		modelAndView.addObject("dataGeracao", dataGeracao);
		modelAndView.addObject("quantidade", quantidade);
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
		
	}
}
