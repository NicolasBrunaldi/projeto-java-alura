package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private RoleDAO roleDAO;

	private Usuario usuarioBD;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}

	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {
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
			return form(usuario);
		}
		
		if(usuarioDAO.emailJaCadastrado(usuario.getEmail())){
			return form(usuario);
		}
		
		usuarioDAO.gravar(usuario);

		redirectAttributes.addFlashAttribute("message", "Usuario cadastrado com sucesso");
		return new ModelAndView("redirect:/usuarios");

	}
	
	@RequestMapping("/roles")
	public ModelAndView roleForm(String email) {
		
		ModelAndView modelAndView = new ModelAndView("/usuarios/roleForm");
		
		System.out.println(email);
		
		usuarioBD = usuarioDAO.loadUserByUsername(email);
		List<Role> roles = roleDAO.listar();
		
		modelAndView.addObject("roles", roles);
		modelAndView.addObject("usuario", usuarioBD);
		
		return modelAndView;
	}
	
	@Transactional
	@RequestMapping(value = "/cadastra-role", method = RequestMethod.POST)
	public ModelAndView cadastraRoleUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
		
		usuarioBD.setRoles(usuario.getRoles());
		
		usuarioDAO.atualiza(usuarioBD);
		
		redirectAttributes.addFlashAttribute("message", "Permissões alteradas com sucesso!");
		return new ModelAndView("redirect:/usuarios");
	}
}
