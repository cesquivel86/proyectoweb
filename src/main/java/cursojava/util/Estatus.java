package cursojava.util;

import java.util.Date;

public enum Estatus {
    ACTIVO_Cliente, INACTIVO_Cliente, Activo_Empleado, Inactivo_Empleado;

    String tipoUsuario;

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
