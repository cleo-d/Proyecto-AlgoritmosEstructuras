package dominio;

import tads.ListaNodos;

public class Bicicleta implements Comparable<Bicicleta> {

    private String codigo;
    private String tipo;
    private String estado;

    public Bicicleta(String unCodigo, String unTipo) {
        this.setCodigo(unCodigo);
        this.setTipo(unTipo);
        this.setEstado("Disponible");
    }
    
        public Bicicleta(String unCodigo, String unTipo, String unEstado) {
        this.setCodigo(unCodigo);
        this.setTipo(unTipo);
        this.setEstado(unEstado);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.getCodigo() + "#" + this.getTipo() + "#" + this.getEstado();
    }

    @Override
    public boolean equals(Object o) {
        //Reviso si es el mismo objecto
        if (this == o) {
            return true;
        }
        //Aseguro que el objeto sea de tipo Estacion
        if (!(o instanceof Bicicleta)) {
            return false;
        }
        //Reviso si contienen la misma cedula
        Bicicleta otro = (Bicicleta) o;
        return this.codigo.equals(otro.codigo);  // Codigo unico define igualdad
    }

    @Override
    public int compareTo(Bicicleta o) {
        return this.getCodigo().compareToIgnoreCase(o.getCodigo());
    }

    public boolean validarCodigo(String codigo) {
        return codigo != null && codigo.length() == 6;
    }

    public boolean validarTipo(String tipo) {
        if (tipo == null) {
            return false;
        }
        tipo = tipo.toUpperCase().trim();
        return tipo.equals("URBANA") || tipo.equals("MOUNTAIN") || tipo.equals("ELECTRICA");
    }

    public static boolean estaAlquilada(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
