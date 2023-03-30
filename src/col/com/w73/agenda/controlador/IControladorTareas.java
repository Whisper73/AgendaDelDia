package col.com.w73.agenda.controlador;

import col.com.w73.agenda.modelo.Tarea;
import java.util.List;


public interface IControladorTareas {
    
    String RUTA_ARCHIVO = "agenda.txt";
    
    void IngresarTarea(Tarea tarea);
    
    List<Tarea> MostarTareas();
    
    void ActualizarTarea(Tarea nuevaTarea, int indexViejaTarea);
    
    void BorrarTarea(int indexTarea);
    
    void BorrarAgenda();
    
    public boolean TieneContenido();
    
    public int BuscarTarea(Tarea tarea);
}
