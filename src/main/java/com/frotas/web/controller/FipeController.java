package com.frotas.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frotas.web.domain.Fipe;
import com.frotas.web.service.FipeService;

@Controller
@RequestMapping("/veiculo/fipe")
public class FipeController {

	@Autowired
	private FipeService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Fipe fipe) {
		return "/veiculo/cadastro_fipe";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("fipes", service.findAll());
		return "/veiculo/lista_fipe";
	}

	@PostMapping("/salvar")
	public String salvar(Fipe fipe, RedirectAttributes attr) {
		service.save(fipe);
		attr.addFlashAttribute("success", "Fipe adicionada com sucesso!");
		return "redirect:/veiculo/fipe/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("categoria", service.findById(id));
		return "/veiculo/fipe/cadastro_fipe";
	}

	@PostMapping("/editar")
	public String editar(Fipe fipe, RedirectAttributes attr) {
		service.update(fipe);
		attr.addFlashAttribute("success", "Fipe alterada com sucesso!");
		return "redirect:/veiculo/fipe/cadastrar";
	}

	@GetMapping("excluir/{id}")
	public String excluir (@PathVariable("id") Long id, ModelMap model){
		if(service.fipeTemVeiculo(id)){
			model.addAttribute("fail", "A fipe não pode ser removida, pois a veículo relacionada");
		} else {
			service.delete(id);
			model.addAttribute("success", "Fipe removida com sucesso!");
		}
		return listar(model);
	}
}
