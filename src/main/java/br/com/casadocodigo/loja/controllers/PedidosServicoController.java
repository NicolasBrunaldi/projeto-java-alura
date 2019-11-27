package br.com.casadocodigo.loja.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import br.com.casadocodigo.loja.models.Pedido;

@Controller
@RequestMapping("/pedidos")
public class PedidosServicoController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listaPedidos() throws URISyntaxException, JsonParseException, JsonMappingException, IOException {
		
		ModelAndView modelAndView = new ModelAndView("/produtos/pedidos");
		
		URI uri = new URI("https://book-payment.herokuapp.com/orders");
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		TypeFactory typeFactory = mapper.getTypeFactory();
		CollectionType collectionType = typeFactory.constructCollectionType(List.class, Pedido.class);
		
		List<Pedido> pedidos = mapper.readValue(response.getBody(), collectionType);

		modelAndView.addObject("pedidos", pedidos);
		
		return modelAndView;
	}

}
