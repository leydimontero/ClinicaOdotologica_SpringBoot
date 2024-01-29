package com.c3.ClinicaOdontologica.security;

import com.c3.ClinicaOdontologica.entity.Usuario;
import com.c3.ClinicaOdontologica.entity.UsuarioRole;
import com.c3.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadorInicial implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //crear un usuario
        //guardarlo en bdd
        //necesito la password
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String clave= cifrador.encode("san");
        System.out.println("CLAVE CIFRADA: "+cifrador);
        Usuario usuario1= new Usuario("Santiago","Santi","santiago@gmail.com",clave, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario1);

        BCryptPasswordEncoder cifrador2 = new BCryptPasswordEncoder();
        String claveAdmi = cifrador2.encode("dh");
        System.out.println("clave cifrada: "+cifrador2);
        Usuario usuario2 = new Usuario("Leydi","leydi","leidy_940@hotmail.es",claveAdmi,UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuario2);
    }
}
