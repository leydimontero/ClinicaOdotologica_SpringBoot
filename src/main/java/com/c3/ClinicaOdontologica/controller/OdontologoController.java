package com.c3.ClinicaOdontologica.controller;

import com.c3.ClinicaOdontologica.entity.Odontologo;
import com.c3.ClinicaOdontologica.entity.Paciente;
import com.c3.ClinicaOdontologica.exception.BadRequestException;
import com.c3.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.c3.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    //asociamos la vista con el DAO
    @Autowired
    private OdontologoService odontologoService= new OdontologoService();

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorIdOdontologo(odontologo.getId());
        if(odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo Actualizado con Exito:" +odontologo.getNombre());
        }else{
            throw new ResourceNotFoundException("No se pudo actualizar el odontologo solicitado: "+ odontologo.getNombre());

        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarTodosLosOdontologos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id)throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorIdOdontologo(id);
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontologo Elimindo con Exito");
        }else {
            throw new ResourceNotFoundException("No se pudo Eliminar el Odontologo solicitado");

        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarOdontologoPorId(@PathVariable Long id)throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorIdOdontologo(id);
        if (odontologoBuscado.isPresent()){
            odontologoService.buscarPorIdOdontologo(id);
            return ResponseEntity.ok(odontologoBuscado);
        }else{
            throw new ResourceNotFoundException("No existe el odontologo Buscado por ID");
        }
    }
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<?> buscarOdontologoPorMatricula(@PathVariable String matricula) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatriculaOdontologo(matricula);
        if (odontologoBuscado.isPresent()){
            odontologoService.buscarPorMatriculaOdontologo(matricula);
            return ResponseEntity.ok("Paciente encontrado con Existo");
        }else{
            throw new ResourceNotFoundException("No existe el odontologo Buscado por Email");
        }
    }

}
