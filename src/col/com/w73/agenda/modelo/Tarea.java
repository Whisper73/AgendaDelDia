package col.com.w73.agenda.modelo;

public class Tarea {
    
    private String hora;
    private String descripcion;

    public Tarea() {
    }

    public Tarea(String hora, String descripcion) {
        this.hora = hora;
        this.descripcion = descripcion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return hora + "," + descripcion;
    }
    
    
    
}
