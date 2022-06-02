package com.springboot.clienteapp.controller;

import java.util.List;

import com.springboot.clienteapp.models.entity.Ciudad;
import com.springboot.clienteapp.models.service.ICiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.clienteapp.models.entity.Cliente;
import com.springboot.clienteapp.models.service.IClienteService;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;

	@Autowired
	private ICiudadService ciudadService;
	
	@GetMapping("/")
	public String listarClientes(Model model) {
		List<Cliente> listadoClientes = clienteService.listarTodso();
		
		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", listadoClientes);
		
		return "/views/clientes/listar";
	}
	@GetMapping("/create")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		List<Ciudad> listCiudades = ciudadService.listaCiudades();

		model.addAttribute("titulo", "Formulario:Nuevo Cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);

		return "/views/clientes/frmCrear";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Cliente cliente){
		clienteService.guardar(cliente);
		System.out.println("Cliente Guardado con exito");
		return "redirect:/views/clientes/";
	}
	
}
