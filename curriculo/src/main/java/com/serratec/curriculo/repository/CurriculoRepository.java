package com.serratec.curriculo.repository;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.serratec.curriculo.model.Curriculo;
import com.serratec.curriculo.model.FotoPerfil;

@Repository
public class CurriculoRepository {

	//Especificar o caminho para pasta
    //Raiz é usado para definir o caminho para a raiz da pasta de curriculos.
    @Value("${serratec.curriculo.raiz}")
    private String raiz;
    //O valor da propriedade diretorioFotos é usado para definir o caminho para a pasta de fotos de perfil dos curriculos.
    @Value("${serratec.curriculo.diretorio-fotos}")
    private String diretorioFotos;
    
    
    FotoPerfil fp = new FotoPerfil();
    
    private List<Curriculo> curriculos = new ArrayList<>();//Armazena o curriculo como se fosse um banco de dados
    
    private long ultimoId = 1;

    // Obter todos os curriculos
    public List<Curriculo> ObterTodos(){
        return curriculos;
    }

    // Obter um curriculo pelo ID
    public Curriculo ObterCurriculo(long id){

        Curriculo curriculoEncontrado = null;

        for (Curriculo curriculo : curriculos) {

            if(curriculo.getId() == id) {
                curriculoEncontrado = curriculo;
            }
        } 
        return curriculoEncontrado;
    }
    
    // Atualizar um curriculo
    public Curriculo Adicionar(Curriculo curriculo){
    	
        // Define o ID do currículo para o valor da propriedade `ultimoId`.
        curriculo.setId(ultimoId);
        // Salva a foto de perfil do currículo e a imagem base64 da foto de perfil.
        curriculo.setFotoPerfil(fp.getDiretorio());
        curriculo.setImagemBase64(fp.getBase64());
        // Adiciona o currículo à lista de currículos.
        curriculos.add(curriculo);
        ultimoId++;
        return curriculo;
    }

    public Curriculo Atualizar(Curriculo curriculo){

         // Remover o curriculo da lista de curriculos
        curriculos.removeIf(c -> c.getId() == curriculo.getId());

        // Adicionar o curriculo atualizado à lista de curriculos
        curriculos.add(curriculo);

        return curriculo;
    }

    public void Deletar(Long id){
        // Deletar um curriculo pelo ID
        curriculos.removeIf(c -> c.getId() == id);
    }

    
    public void salvarImagem(MultipartFile arquivo){
    	
    	//String nomeFoto = Long.toString(ultimoId);
    	
         // Criar um caminho para a imagem de perfil
    	Path diretorioPath = Paths.get(this.raiz, this.diretorioFotos);
		Path arquivoPath = diretorioPath.resolve(Long.toString(ultimoId).concat(".jpg"));
		FileInputStream imageInputStream = null;//pega a umagem e transforma de byte

		try {   
            // Criar a pasta para a imagem de perfil, se ela não existir   
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile()); // Salvar a imagem de perfil
			fp.setDiretorio(arquivoPath.toString());
			fp.setId(ultimoId); 
			fp.setNome(Long.toString(ultimoId));
			
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}
		
		try {//Salva a imagem de perfil do currículo em um arquivo de texto com o nome do ID do currículo seguido de `imgBase64.txt`
			// Lê a imagem de perfil como uma matriz de bytes.
            imageInputStream = new FileInputStream(arquivoPath.toString());
			byte[] imageBytes; 
			imageBytes = new byte[imageInputStream.available()];
			imageInputStream.read(imageBytes);

             // Converte a matriz de bytes para uma string base64.
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			
			// Salva a string base64 em um arquivo de texto.
			try (FileWriter writer = new FileWriter(diretorioPath+"/"+Long.toString(ultimoId)+"imgBase64.txt")) {
				writer.write(base64Image);
			}
            // Define o nome do arquivo base64 na propriedade `base64` do objeto `FotoPerfil`.
			fp.setBase64(Long.toString(ultimoId)+"imgBase64.txt");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
}
