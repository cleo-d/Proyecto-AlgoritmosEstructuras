package dominio;

public class Usuario implements Comparable<Usuario> {

    private String cedula;
    private String nombre;
    private Bicicleta biciAlquilada;

    public Usuario(String unaCedula, String unNombre) {
        this.setCedula(unaCedula);
        this.setNombre(unNombre);
        this.biciAlquilada = null;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.getNombre() + "#" + this.getCedula();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario otro = (Usuario) o;
        if (this.cedula == null || otro.cedula == null) {
            return false;
        }
        return this.cedula.equals(otro.cedula);
    }

    //VERIFICAR SI ESTA BIEN ESTE COMPARE TO
    @Override
    public int compareTo(Usuario o) {
        return this.nombre.compareToIgnoreCase(o.nombre);
    }

    
    public boolean validarCedula(String cedula) {
    if (cedula == null) return false;

    cedula = cedula.trim();

    if (cedula.length() != 8) return false;

    // usamos para que sea numerica la cedula
    for (char c : cedula.toCharArray()) {
        if (!Character.isDigit(c)) {
            return false;
        }
    }

    return true;
    
}

    /**
     * @return the biciAlquilada
     */
    public Bicicleta getBiciAlquilada() {
        return biciAlquilada;
    }

    /**
     * @param biciAlquilada the biciAlquilada to set
     */
    public void setBiciAlquilada(Bicicleta biciAlquilada) {
        this.biciAlquilada = biciAlquilada;
    }
}
