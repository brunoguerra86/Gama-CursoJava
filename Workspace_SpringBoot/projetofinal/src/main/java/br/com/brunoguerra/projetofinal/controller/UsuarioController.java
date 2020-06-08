package br.com.brunoguerra.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunoguerra.projetofinal.dao.UsuarioDAO;
import br.com.brunoguerra.projetofinal.model.Usuario;

@RestController
public class UsuarioController {

	@Autowired 
	private UsuarioDAO dao;
	
	@GetMapping("/usuarios")
	public ArrayList<Usuario> listarTodos(){
		return (ArrayList<Usuario>)dao.findAll();
	}
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> logarUsuario(@RequestBody Usuario dadosLogin) {
		Usuario res = dao.findByEmail(dadosLogin.getEmail());
		if(res == null) {	// usuário NÃO existe
			return ResponseEntity.notFound().build(); 		// HTTP 404
		}
		else {  // usuário existe
			if(res.getSenha().equals(dadosLogin.getSenha()))
				return ResponseEntity.ok(res); 				// HTTP 200
			else
				return ResponseEntity.status(403).build(); 	// HTTP 403
		}
	}
}