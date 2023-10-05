package com.serratec.curriculo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.serratec.curriculo.model.Curriculo;

@Repository
public class CurriculoRepository {
    private List<Curriculo> curriculos = new ArrayList<>();
    private long ultimoId = 0;

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

        ultimoId++;
        curriculo.setId(ultimoId);

        curriculos.add(curriculo);

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

    
}
