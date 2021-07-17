package org.example.services;

import org.example.entity.Alimento;

import java.util.List;

public interface AlimentoService {

    public List<Alimento> obtenerAlimentosFiltrados(String categoría, String nombre, String nutrientes,List<Alimento> todosLosAlimentos );

    public List<Alimento> obtenerPorCategoria(String categoría);

    public List<Alimento> obtenerTodos();

    public List<String> obtenerCategorias(List<Alimento> alimentos);
}
