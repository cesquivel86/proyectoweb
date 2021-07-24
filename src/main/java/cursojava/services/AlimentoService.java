package cursojava.services;

import cursojava.adapters.AlimentoAdapter;
import cursojava.entity.Alimento;
import cursojava.entity.Categoria;

import java.util.List;

public interface AlimentoService {

    public List<Alimento> obtenerAlimentosFiltrados(String categoría, String nombre, String nutrientes, List<Alimento> todosLosAlimentos );

    public List<Alimento> obtenerPorCategoria(Categoria categoría);

    public List<Alimento> obtenerTodos();

    public List<String> obtenerCategorias(List<Alimento> alimentos);

    public List<Categoria> obtenerCategoriasPorBaseDeDatos();

    public void eliminarAlimentoDesdeTabla(Alimento a);

    public Alimento guardarAlimento(Alimento a);

    public void actualizar(Alimento a);

    public List<AlimentoAdapter> getAdaptadores(List<Alimento> alimentos);
}
