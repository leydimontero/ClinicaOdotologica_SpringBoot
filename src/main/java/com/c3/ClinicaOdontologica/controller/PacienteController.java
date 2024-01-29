package com.c3.ClinicaOdontologica.controller;

import com.c3.ClinicaOdontologica.entity.Odontologo;
import com.c3.ClinicaOdontologica.entity.Paciente;
import com.c3.ClinicaOdontologica.exception.BadRequestException;
import com.c3.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.c3.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController  //ahora trabajo con vista, no va RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService= new PacienteService();


    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorIdPacientes(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente Actualizado con Exito:" +paciente.getNombre());
        }else{
            throw new ResourceNotFoundException("No se pudo actualizar el paciente solicitado:" +paciente.getNombre());

        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorIdPacientes(id);
        if (pacienteBuscado.isPresent()){
            pacienteService.buscarPorIdPacientes(id);
            return ResponseEntity.ok(pacienteBuscado);
        }else{
            throw new ResourceNotFoundException("No existe el paciente Buscado por ID");
        }

    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarPacientePorEmail(@PathVariable String email) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmailPacientes(email);
        if (pacienteBuscado.isPresent()){
            pacienteService.buscarPorEmailPacientes(email);
            return ResponseEntity.ok("Paciente encontrado con Existo");
        }else{
            throw new ResourceNotFoundException("No existe el paciente Buscado por Email");
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarTodosLosPacientes());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorIdPacientes(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente Elimindo con Exito");
        }else {
            throw new ResourceNotFoundException("No existe el paciente a Eliminar");

        }
    }

}
