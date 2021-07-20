package cursojava.services;

import cursojava.entity.Categoria;
import cursojava.dao.AlimentoDAO;
import cursojava.dao.CategoriaDAO;
import cursojava.entity.Alimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("alimentoService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AlimentoServiceImpl implements AlimentoService{

    @Autowired
    AlimentoDAO alimentoDao;

    @Autowired
    CategoriaDAO categoriaDao;

    @Override
    public List<Alimento> obtenerAlimentosFiltrados(String categoría, String nombre, String nutrientes,List<Alimento> todosLosAlimentos ) {
        List<Alimento> alimentosFiltrados = new ArrayList<Alimento>();
        for (Iterator<Alimento> i = todosLosAlimentos.iterator(); i.hasNext();) {
            Alimento tmp = i.next();
            if (tmp.getCategoria().getNombre().toLowerCase().contains(categoría.toLowerCase()) &&
                    tmp.getNombre().toLowerCase().contains(nombre.toLowerCase())  &&
                    tmp.getNutrientes().toLowerCase().contains(nutrientes.toLowerCase())) {
                alimentosFiltrados.add(tmp);
            }
        }
        return alimentosFiltrados;
    }

    @Override
    public List<Alimento> obtenerPorCategoria(Categoria categoría) {
        return alimentoDao.getByCategory(categoría);
    }

    @Override
    public List<Alimento> obtenerTodos() {
        return alimentoDao.queryAll();
    }

    @Override
    public List<String> obtenerCategorias(List<Alimento> alimentos) {
        ArrayList<String> categorias = new ArrayList<String>();

        for(Alimento a: alimentos){
            if(!categorias.contains(a.getCategoria().getNombre())) categorias.add(a.getCategoria().getNombre());
        }
        return categorias;
    }

    @Override
    public List<Categoria> obtenerCategoriasPorBaseDeDatos() {
        return categoriaDao.queryAll();
    }

    @Override
    public void eliminarAlimentoDesdeTabla(Alimento a) {
        alimentoDao.delete(a);
    }
}
