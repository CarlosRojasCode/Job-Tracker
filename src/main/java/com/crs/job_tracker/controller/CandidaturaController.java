package com.crs.job_tracker.controller;

import com.crs.job_tracker.model.Candidatura;
import com.crs.job_tracker.service.CandidaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // todo lo que devuelvan los métodos se convierte a JSON
@RequestMapping("/api/candidaturas") 
public class CandidaturaController {
 
    @Autowired
    private CandidaturaService service;

    // GET /api/candidaturas
    // devuelve la lista completa de candidaturas
    @GetMapping
    public ResponseEntity<List<Candidatura>> listarTodas(){
        List<Candidatura> candidaturas = service.listarTodas();
        return ResponseEntity.ok(candidaturas);
    }

    // GET /api/candidaturas/3
    // @PathVariable extrae el valor 3 de la url y lo mete en la variable id
    @GetMapping("/{id}")
    public ResponseEntity<Candidatura> buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id)
        // si el Optional TIENE valor : 200 OK
        .map(candidatura -> ResponseEntity.ok(candidatura))
        // si el Optional está VACÍO : 404 Not Found
        .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/candidaturas
    // @RequestBody le dice a Spring que coja el JSON y lo convierta
    // en objeto Candidatura
    @PostMapping
    public ResponseEntity<Candidatura> crear(@RequestBody Candidatura nueva){
        Candidatura guardada = service.guardar(nueva);
        // código 201 cuando es CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    // PUT /api/candidaturas/3
    // actualiza una candidatura existente
    @PutMapping("/{id}")
    public ResponseEntity<Candidatura> actualizar(@PathVariable Long id, @RequestBody Candidatura datos){
        return service.buscarPorId(id)
        .map(candidaturaExistente -> {
            // actualiza los campos uno a uno con los datos recibidos
            candidaturaExistente.setEmpresa(datos.getEmpresa());
            candidaturaExistente.setPuesto(datos.getPuesto());
            candidaturaExistente.setFechaEnvio(datos.getFechaEnvio());
            candidaturaExistente.setEstado(datos.getEstado());
            candidaturaExistente.setEnlace(datos.getEnlace());
            candidaturaExistente.setComentarios(datos.getComentarios());

            Candidatura actualizada = service.guardar(candidaturaExistente);
            return ResponseEntity.ok(actualizada);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/candidaturas/3
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        // comprueba siexiste, y sino devuelve 404
        if(service.buscarPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        // 204 NOT CONTENT, dice que la operación fue bien, pero no hay nada que devolver
        return ResponseEntity.noContent().build();
    }
    


}
