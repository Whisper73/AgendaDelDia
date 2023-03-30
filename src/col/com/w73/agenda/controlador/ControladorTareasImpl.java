package col.com.w73.agenda.controlador;

import col.com.w73.agenda.excepciones.*;
import col.com.w73.agenda.manejoDatos.*;
import col.com.w73.agenda.modelo.Tarea;
import java.util.*;

public class ControladorTareasImpl implements IControladorTareas {

    private final IManejoDatos dato;

    public ControladorTareasImpl() {
        this.dato = new ManejoDatosImpl();
    }
    
    @Override
    public void IngresarTarea(Tarea tarea) {
        if(dato.Existe(RUTA_ARCHIVO)){
            try {
                dato.Escribir(RUTA_ARCHIVO, tarea);
                System.out.println("Tarea ingresada con exito");
            } catch (EscribirDatosEx ex) {
                System.out.println("imposible Ingresar la Tarea al Archivo "+ex.getMessage());
            }  
        }else{ 
            try {
                dato.Crear(RUTA_ARCHIVO);
                dato.Escribir(RUTA_ARCHIVO, tarea);
                System.out.println("Tarea ingresada con exito");
            } catch (AccesoDatosEx ex) {
                System.out.println("imposible Ingresar la Tarea al Archivo "+ex.getMessage());
            }
            
        }
        
    }

    @Override
    public List<Tarea> MostarTareas() {
        List<Tarea> tareas = new ArrayList<>();
        try {
           tareas = dato.Listar(RUTA_ARCHIVO);
        } catch (LeerDatosEx ex) {
            System.out.println("imposible Listar las tareas "+ex.getMessage());
        }
       return tareas; 
    }

    @Override
    public void ActualizarTarea(Tarea nuevaTarea, int indexViejaTarea) {
        
        try {
            dato.Actualizar(RUTA_ARCHIVO, nuevaTarea, indexViejaTarea);
            System.out.println("Tarea actualizada con Exito");
        } catch (EscribirDatosEx ex) {
            System.out.println("imposible Actualizar la tarea "+ex.getMessage());
        }
    }

    @Override
    public void BorrarTarea(int indexTarea) {
        
        try {
            dato.BorrarDato(RUTA_ARCHIVO, indexTarea);
        } catch (EscribirDatosEx ex) {
            System.out.println("imposible Borrar la tarea "+ex.getMessage());
        }
    }

    @Override
    public void BorrarAgenda() {
        
        try {
            dato.Borrar(RUTA_ARCHIVO);
        } catch (AccesoDatosEx ex) {
            System.out.println("imposible borrar la agenda "+ex.getMessage());
        }
    }
    
    @Override
    public boolean TieneContenido(){
       
       return dato.verificarSiTieneContenido(RUTA_ARCHIVO);
    }
    
    @Override
    public int BuscarTarea(Tarea tarea){
        
        return dato.Buscar(RUTA_ARCHIVO, tarea);
    }
}
