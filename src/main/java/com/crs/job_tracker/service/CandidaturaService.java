package com.crs.job_tracker.service;

import com.crs.job_tracker.model.Candidatura;
import com.crs.job_tracker.repository.CandidaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service // gestiono la  lógica con este @Service para que otras clases puedan utilizar
public class CandidaturaService {
    
    @Autowired // inyecto el repositorio, dejando que Spring nos lo proporcione.
    private CandidaturaRepository repository;

    // devuelve todas las candidaturas guardadas
    public List<Candidatura> listarTodas(){
        return repository.findAll();
    }

    // busco candidatura por su id
    // y utilizo el Optional porque si no existe esa id
    // obligamos a manejar el caso "no encontrado"
    public Optional<Candidatura> buscarPorId(Long id){
        return repository.findById(id);
    }

    // guarda una candidatura nueva o actualiza una existente
    public Candidatura guardar(Candidatura candidatura){
        return repository.save(candidatura);
    }

    // elimina candidatura por id
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}
