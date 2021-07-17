package org.example.services;

import org.example.dao.AlimentoDAO;
import org.example.dao.PersonaDAO;
import org.example.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personaService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaDAO dao;

    @Override
    public Persona guadarPersona(Persona p) {
        return dao.guardar(p);
    }

    @Override
    public Persona eliminaPersona(Persona p) {
        return null;
    }

    @Override
    public List<Persona> traerTodasLasPersonas() {
        return null;
    }

    @Override
    public Persona buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Persona> buscarPorNombreyApellido(String nombre, String apellido) {
        return null;
    }
}
