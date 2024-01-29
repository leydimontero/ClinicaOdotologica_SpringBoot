package com.c3.ClinicaOdontologica.service;

import com.c3.ClinicaOdontologica.entity.Odontologo;
import com.c3.ClinicaOdontologica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo (Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public void actualizarOdontologo (Odontologo odontologo){
         odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo (Long id){
        odontologoRepository.deleteById(id);
    }
    public List<Odontologo> listarTodosLosOdontologos(){
        return odontologoRepository.findAll();
    }
    public Optional<Odontologo> buscarPorIdOdontologo (Long id){
        return odontologoRepository.findById(id);
    }
    public Optional<Odontologo> buscarPorMatriculaOdontologo (String matricula){
        return odontologoRepository.findByMatricula(matricula);
    }
}
