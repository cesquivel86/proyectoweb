package cursojava.util;

import java.util.Date;

public enum EquipoDeFutbol {
    AMERICA("1929","Azteca", "Azcarraga"),
    CHIVAS("1907","Omnilife","Vergara");

    private EquipoDeFutbol(String fechaDeCreacion, String estadio, String dueno){
        this.fechaDeCreacion=fechaDeCreacion;
        this.estadio=estadio;
        this.dueno=dueno;
    }

    public String fechaDeCreacion;

    public String estadio;

    public String dueno;

    public String getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(String fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }
}
