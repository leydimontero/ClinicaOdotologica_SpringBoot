package com.c3.ClinicaOdontologica.controller;

import com.c3.ClinicaOdontologica.dto.TurnoDTO;
import com.c3.ClinicaOdontologica.entity.Odontologo;
import com.c3.ClinicaOdontologica.entity.Paciente;
import com.c3.ClinicaOdontologica.entity.Turno;
import com.c3.ClinicaOdontologica.exception.BadRequestException;
import com.c3.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.c3.ClinicaOdontologica.service.OdontologoService;
import com.c3.ClinicaOdontologica.service.PacienteService;
import com.c3.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontoloService;
    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontoloService = odontologoService;
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }
    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody Turno turno)  throws BadRequestException {

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorIdPacientes(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontoloService.buscarPorIdOdontologo(turno.getOdontologo().getId());
        if(pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else{
             throw new BadRequestException("El Paciente o el Odontologo no se encuentran al registrar un turno no es posible");
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoDTO> buscarPorIdTurno (@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoDTO = turnoService.bucarTurnoPorId(id);
        if (turnoDTO.isPresent()){
            return ResponseEntity.ok(turnoDTO.get());
        }else{
           throw new ResourceNotFoundException("-no existe el Turno buscado por ID");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> borrarTurno(@RequestBody Long id) throws ResourceNotFoundException{
        Optional<TurnoDTO> turnoAEliminar = turnoService.bucarTurnoPorId(id);
        if (turnoAEliminar.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno Eliminado con exito");
        }else{
            throw new ResourceNotFoundException("no existe el Turno buscado a Eliminar");
        }

    }
    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTruno (@PathVariable Turno turno) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorIdPacientes(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontoloService.buscarPorIdOdontologo(turno.getOdontologo().getId());
        if(pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else{
            throw new ResourceNotFoundException("El Paciente o el Odontologo no se encuentran al registrar un turno no es posible");
        }


    }


}
