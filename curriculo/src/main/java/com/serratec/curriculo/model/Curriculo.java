package com.serratec.curriculo.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

//import java.io.File;


@Component
public class Curriculo {
    
    private long id;
    private String nomeCompleto;
    private Integer idade;
    private String telefone;
    private String email;
    private String endereco;
    

    //private File imagem;

    //#region Get e Setters
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeComleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
     public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    //#endregion

    @Value("${serratec.curriculo.raiz}")
	private String raiz;
	
	public String getRaiz() {
        return raiz;
    }

    public void setRaiz(String raiz) {
        this.raiz = raiz;
    }

    @Value("${serratec.curriculo.diretorio-fotos}")
	private String diretorioFotos;
	
	public String getDiretorioFotos() {
        return diretorioFotos;
    }

    public void setDiretorioFotos(String diretorioFotos) {
        this.diretorioFotos = diretorioFotos;
    }

    public void salvarFoto(@RequestPart("file") MultipartFile foto) {
		this.salvar(this.diretorioFotos, foto);
	}
	
	public void salvar(String diretorio, MultipartFile arquivo) {
		Path diretorioPath = Paths.get(this.raiz, diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
		
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());			
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}		
	}
    
}
