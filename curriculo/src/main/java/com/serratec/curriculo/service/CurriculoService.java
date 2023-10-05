package com.serratec.curriculo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.curriculo.model.Curriculo;
import com.serratec.curriculo.repository.CurriculoRepository;

@Service
public class CurriculoService {
    
    @Autowired
    private CurriculoRepository curriculoRepository;

    public List<Curriculo> ObterTodos(){
        return curriculoRepository.ObterTodos();
    }

    public Curriculo ObterCurriculo(long id){

        Curriculo curriculo = curriculoRepository.ObterCurriculo(id);

        return curriculo;
    }

    public Curriculo Adicionar(Curriculo curriculo){

        return curriculoRepository.Adicionar(curriculo);
    }

    public Curriculo Atualizar(Long id, Curriculo curriculo){

        curriculo.setId(id);
        return curriculoRepository.Atualizar(curriculo);

    }

    public void Deletar(Long id){
        curriculoRepository.Deletar(id);
    }



}
