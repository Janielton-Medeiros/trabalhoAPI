package com.serratec.curriculo.model;

import org.springframework.stereotype.Component;

@Component
public class Curriculo {
    
    private long id;
    private String nomeCompleto;
    private Integer idade;
    private String telefone;
    private String email;
    private String endereco;
    private String fotoPerfil; 
    private String imagemBase64;

    //#region Get e Setters
       
    public String getFotoPerfil() {
		return fotoPerfil;
	}

	public String getImagemBase64() {
		return imagemBase64;
	}

	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getNomeCompleto() {
        return nomeCompleto;
    }

	public void setNomeCompleto(String nomeCompleto) {
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
}
