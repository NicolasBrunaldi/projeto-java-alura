package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}

	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario , RedirectAttributes redirectAttributes) {
		return new ModelAndView("/usuarios/form");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listaUsuarios() {

		ModelAndView modelAndView = new ModelAndView("/usuarios/lista");

		List<Usuario> usuarios = usuarioDAO.listar();

		modelAndView.addObject("usuarios", usuarios);

		return modelAndView;
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView cadastraUsuario(@Valid Usuario usuario,  BindingResult result,
			RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return form(usuario, redirectAttributes);
		}
		
		if(usuarioDAO.emailJaCadastrado(usuario.getEmail())){
			redirectAttributes.addFlashAttribute("message", "Email ja cadastrado");
			return form(usuario, redirectAttributes);
		}
		
		usuarioDAO.gravar(usuario);

		redirectAttributes.addFlashAttribute("message", "Usuario cadastrado com sucesso");
		return new ModelAndView("redirect:/usuarios");

	}
}
