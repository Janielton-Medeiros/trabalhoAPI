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
	
    @Value("${serratec.curriculo.raiz}")
    private String raiz;
    @Value("${serratec.curriculo.diretorio-fotos}")
    private String diretorioFotos;
    
    FotoPerfil fp = new FotoPerfil();
    
    private List<Curriculo> curriculos = new ArrayList<>();
    
    private long ultimoId = 1;

    public List<Curriculo> ObterTodos(){
        return curriculos;
    }

    public Curriculo ObterCurriculo(long id){

        Curriculo curriculoEncontrado = null;

        for (Curriculo curriculo : curriculos) {

            if(curriculo.getId() == id) {
                curriculoEncontrado = curriculo;
            }
        } 
        return curriculoEncontrado;
    }

    public Curriculo Adicionar(Curriculo curriculo){
    	
        
        curriculo.setId(ultimoId);
        curriculo.setFotoPerfil(fp.getDiretorio());
        curriculo.setImagemBase64(fp.getBase64());
        curriculos.add(curriculo);
        ultimoId++;
        return curriculo;
    }

    public Curriculo Atualizar(Curriculo curriculo){

        curriculos.removeIf(c -> c.getId() == curriculo.getId());

        curriculos.add(curriculo);

        return curriculo;
    }

    public void Deletar(Long id){
        curriculos.removeIf(c -> c.getId() == id);
    }

    
    public void salvarImagem(MultipartFile arquivo){
    	
    	//String nomeFoto = Long.toString(ultimoId);
    	
    	Path diretorioPath = Paths.get(this.raiz, this.diretorioFotos);
		Path arquivoPath = diretorioPath.resolve(Long.toString(ultimoId));
		FileInputStream imageInputStream = null;

		try {      
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
			fp.setDiretorio(arquivoPath.toString());
			fp.setId(ultimoId); 
			fp.setNome(Long.toString(ultimoId));
			
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}
		
		try {
			imageInputStream = new FileInputStream(arquivoPath.toString());
			byte[] imageBytes;
			imageBytes = new byte[imageInputStream.available()];
			imageInputStream.read(imageBytes);
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			
			
			try (FileWriter writer = new FileWriter(diretorioPath+"/"+Long.toString(ultimoId)+"imgBase64.txt")) {
				writer.write(base64Image);
			}
			fp.setBase64(Long.toString(ultimoId)+"imgBase64.txt");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
}
