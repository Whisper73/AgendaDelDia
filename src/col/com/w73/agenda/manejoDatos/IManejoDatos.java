package col.com.w73.agenda.manejoDatos;

import col.com.w73.agenda.excepciones.*;
import col.com.w73.agenda.modelo.Tarea;
import java.util.List;

public interface IManejoDatos {
    
    //CRUD basico
    
    void Crear(String ruta) throws AccesoDatosEx;
    
    List<Tarea> Listar(String ruta) throws LeerDatosEx;
    
    void Actualizar(String ruta, Tarea nuevaTarea, int indexViejaTarea) throws EscribirDatosEx;
    
    void Borrar(String ruta) throws AccesoDatosEx;
    
    
    //compostamientos adicionales para el manejo de los datos
    boolean Existe (String ruta);
    
    void BorrarDato (String ruta, int indexTarea)throws EscribirDatosEx;
    
    void Escribir (String ruta, Tarea tarea)throws EscribirDatosEx ;
    
    public boolean verificarSiTieneContenido(String ruta);
    
    public int Buscar(String ruta, Tarea tarea);
}
