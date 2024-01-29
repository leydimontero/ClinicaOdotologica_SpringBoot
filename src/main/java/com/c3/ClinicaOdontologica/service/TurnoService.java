package com.c3.ClinicaOdontologica.service;

import com.c3.ClinicaOdontologica.dto.TurnoDTO;
import com.c3.ClinicaOdontologica.entity.Odontologo;
import com.c3.ClinicaOdontologica.entity.Paciente;
import com.c3.ClinicaOdontologica.entity.Turno;
import com.c3.ClinicaOdontologica.repository.TurnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private TurnoRepository turnoRepository;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;
    @Autowired
    public TurnoService(TurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    public TurnoDTO guardarTurno(Turno turno){
        Turno turnoAGuardar = turnoRepository.save(turno);
        return turnoATurnoDTO(turnoAGuardar);
    }
    public void actualizarTurno(Turno turno){
         turnoRepository.save(turno);
    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
    public Optional<TurnoDTO> bucarTurnoPorId(Long id){
        Optional<Turno> turnoABuscar = turnoRepository.findById(id);
        if (turnoABuscar.isPresent()){
            return Optional.of(turnoATurnoDTO(turnoABuscar.get()));
        }else {
            return Optional.empty();
        }
    }
    public List<TurnoDTO> listarTurnos(){
        List<Turno> listaDeTurno = turnoRepository.findAll();
        List<TurnoDTO> listaTurnoDTO = new ArrayList<>();
        for (Turno turno : listaDeTurno) {
            listaTurnoDTO.add(turnoATurnoDTO(turno));
        }
        return listaTurnoDTO;
    }
    private TurnoDTO turnoATurnoDTO(Turno turno){
       TurnoDTO turnoDTO = new TurnoDTO();
       turnoDTO.setId(turno.getId());
       turnoDTO.setFecha(turno.getFecha());
       turnoDTO.setOdontologoId(turno.getOdontologo().getId());
       turnoDTO.setPacienteId(turno.getPaciente().getId());
       return turnoDTO;
    }
    private Turno turnoDTOATurno(TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();
        odontologo.setId(turnoDTO.getOdontologoId());
        paciente.setId(turnoDTO.getPacienteId());
        turno.setFecha(turnoDTO.getFecha());
        turno.setId(turnoDTO.getId());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        return turno;
    }
}
