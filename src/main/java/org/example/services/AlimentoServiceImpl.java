package org.example.services;

import org.example.dao.AlimentoDAO;
import org.example.entity.Alimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Service("alimentoService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AlimentoServiceImpl implements AlimentoService{

    @Autowired
    AlimentoDAO dao;

    @Override
    public List<Alimento> obtenerAlimentosFiltrados(String categoría, String nombre, String nutrientes,List<Alimento> todosLosAlimentos ) {
        List<Alimento> alimentosFiltrados = new ArrayList<Alimento>();
        for (Iterator<Alimento> i = todosLosAlimentos.iterator(); i.hasNext();) {
            Alimento tmp = i.next();
            if (tmp.getCategoria().toLowerCase().contains(categoría.toLowerCase()) &&
                    tmp.getNombre().toLowerCase().contains(nombre.toLowerCase())  &&
                    tmp.getNutrientes().toLowerCase().contains(nutrientes.toLowerCase())) {
                alimentosFiltrados.add(tmp);
            }
        }
        return alimentosFiltrados;
    }

    @Override
    public List<Alimento> obtenerPorCategoria(String categoría) {
        return dao.getByCategory(categoría);
    }

    @Override
    public List<Alimento> obtenerTodos() {
        return dao.queryAll();
    }

    @Override
    public List<String> obtenerCategorias(List<Alimento> alimentos) {
        ArrayList<String> categorias = new ArrayList<String>();

        for(Alimento a: alimentos){
            if(!categorias.contains(a.getCategoria())) categorias.add(a.getCategoria());
        }
        return categorias;
    }
}
