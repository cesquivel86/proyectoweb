package org.example.services;

import org.example.entity.Persona;

import java.util.List;

public interface PersonaService {

    Persona guadarPersona(Persona p);

    Persona eliminaPersona(Persona p);

    List<Persona> traerTodasLasPersonas();

    Persona buscarPorId(Integer id);

    List<Persona> buscarPorNombreyApellido(String nombre, String apellido);

}
