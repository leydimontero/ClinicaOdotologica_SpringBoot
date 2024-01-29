package com.c3.ClinicaOdontologica.service;

import com.c3.ClinicaOdontologica.entity.Paciente;
import com.c3.ClinicaOdontologica.repository.PacienteReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteReposiroty pacienteReposiroty;

    public Paciente guardarPaciente (Paciente paciente){
        return pacienteReposiroty.save(paciente);
    }
    public void actualizarPaciente (Paciente paciente){
         pacienteReposiroty.save(paciente);
    }
    public void eliminarPaciente (Long id){
         pacienteReposiroty.deleteById(id);
    }
    public List<Paciente> listarTodosLosPacientes(){
        return pacienteReposiroty.findAll();
    }
    public Optional<Paciente> buscarPorIdPacientes (Long id){
        return pacienteReposiroty.findById(id);
    }
    public Optional<Paciente> buscarPorEmailPacientes (String email){
        return pacienteReposiroty.findByEmail(email);
    }
}
