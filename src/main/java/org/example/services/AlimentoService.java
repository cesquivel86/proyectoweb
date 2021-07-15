package org.example.services;

import org.example.entity.Alimento;

import java.util.List;

public interface AlimentoService {

    public List<Alimento> obtenerAlimentosFiltrados(String categoría, String nombre, String nutrientes);

    public List<Alimento> obtenerPorCategoria(String categoría);
}
