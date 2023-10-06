package com.serratec.curriculo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.serratec.curriculo.model.Curriculo;
import com.serratec.curriculo.service.CurriculoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/api/curriculos")
@Api(tags = "TESTE")
public class CurriculoController {


	@Autowired
	private CurriculoService curriculoService;
	

    @GetMapping
    @ApiOperation("Testando")
    public ResponseEntity<List<Curriculo>> ObterTodos(){
    	
        return ResponseEntity.ok(curriculoService.ObterTodos());
    }

    @GetMapping("/{id}")
    public Curriculo ObterCurriculo(@PathVariable long id){
        return curriculoService.ObterCurriculo(id);
    }
    
    @PostMapping
    public Curriculo Adicionar(@RequestBody Curriculo curriculo){
        return curriculoService.Adicionar(curriculo);
    }

    @PutMapping("/{id}")
    public Curriculo Atualizar(@PathVariable long id, @RequestBody Curriculo curriculo){
        return curriculoService.Atualizar(id, curriculo);
    }

    @DeleteMapping("/{id}")
    public void Deletar(@PathVariable long id){
        curriculoService.Deletar(id);
    }

    @PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestPart("file") MultipartFile foto) {
    	curriculoService.salvarImagem(foto);
    	
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
