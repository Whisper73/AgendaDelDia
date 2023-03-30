package col.com.w73.agenda.manejoDatos;

import col.com.w73.agenda.excepciones.*;
import col.com.w73.agenda.modelo.Tarea;
import java.io.*;
import java.util.*;



public class ManejoDatosImpl implements IManejoDatos{

    @Override
    public void Crear(String ruta) throws AccesoDatosEx {
        File archivo = new File(ruta);
        if(!this.Existe(ruta)){
            
            try {
                PrintWriter salida = new PrintWriter(new FileWriter(archivo));
                salida.close(); 
                System.out.println("Recurso Creado con exito");
            } catch (IOException ex) {
                throw new AccesoDatosEx("Un error a ocurrido al tratar de crear el recurso "+ Arrays.toString(ex.getStackTrace()));
            }
        }else{
            
            System.out.println("El recurso ya existe !");
        }
        
    }

    @Override
    public List<Tarea> Listar(String ruta) throws LeerDatosEx {
        File archivo = new File(ruta);
        List<Tarea>tareas = new ArrayList<>();
        try {
            try(BufferedReader entrada = new BufferedReader(new FileReader(archivo))){
                String linea = entrada.readLine();
                while (linea != null){
                    String [] atributo = linea.split(",");
                    Tarea tarea = new Tarea(atributo[0],atributo[1]);
                    tareas.add(tarea); 
                    linea = entrada.readLine();
                }
            }
            
        } catch (FileNotFoundException ex) {
            throw new LeerDatosEx("Error al intentar leer el recurso "+ex.getMessage());
        } catch (IOException ex) {
           throw new LeerDatosEx("Error al intentar leer el recurso "+ex.getMessage());
        }
        
        return tareas;
    }

    @Override
    public void Actualizar(String ruta, Tarea nuevaTarea, int indexViejaTarea) throws EscribirDatosEx {

        try {
            List<Tarea> tareas = this.Listar(ruta);
            this.Borrar(ruta);
            this.Crear(ruta);
            tareas.set(indexViejaTarea, nuevaTarea);
            for(Tarea t : tareas){
                this.Escribir(ruta, t);
            }
        } catch (LeerDatosEx ex) {
            throw new EscribirDatosEx("Error al actualizar datos en el recurso");
        } catch (AccesoDatosEx ex) {
            System.out.println("no se pudo eliminar el recurso "+ ex.getMessage());
        }
       
    }

    @Override
    public void Borrar(String ruta) throws AccesoDatosEx {
        
        if(this.Existe(ruta)){
            File archivo = new File(ruta);
            archivo.delete();
            System.out.println("Recurso borrado con exito");
        }else{
            
            System.out.println("Error no se ha podido borrar el recurso");
        }
    }

    @Override
    public boolean Existe(String ruta) {      
         File archivo = new File(ruta);
         return archivo.exists();
    }

    @Override
    public void BorrarDato(String ruta, int indexTarea)throws EscribirDatosEx {
        try {
            List<Tarea> tareas = this.Listar(ruta);
            this.Borrar(ruta);
            this.Crear(ruta);
            tareas.remove(indexTarea);
            for(Tarea t : tareas){
                this.Escribir(ruta, t);
            }
        } catch (LeerDatosEx ex) {
            throw new EscribirDatosEx("Error al actualizar datos en el recurso");
        } catch (AccesoDatosEx ex) {
            System.out.println("no se pudo eliminar el recurso "+ ex.getMessage());
        }
       
    }

    @Override
    public void Escribir(String ruta, Tarea tarea)throws EscribirDatosEx {
        
        if(this.Existe(ruta)){
            File archivo = new File(ruta);
            try {
                    try(PrintWriter lapiz = new PrintWriter(new FileWriter(archivo, true))){
                    lapiz.println(tarea.toString()); 
                    System.out.println("SE ha escrito la siguiente informacion "+ tarea.toString());
                }
            } catch (IOException ex) {
                throw new EscribirDatosEx("Error al escribir el dato en el recurso "+ex.getMessage());
            }
        }
        
    }
    
    @Override
    public boolean verificarSiTieneContenido(String ruta){
        File archivo = new File(ruta);
        if(this.Existe(ruta)){ 
            long tamano = archivo.length();
            if(tamano>0){
                return true;
            }  
        }
        
        return false;
    }
    
    @Override
    public int Buscar(String ruta, Tarea tarea){
        
       List<Tarea> tareas;
       int index = 0;
        try {
            tareas = this.Listar(ruta);
                for (Tarea t : tareas){

                    String hora = t.getHora();
                    String descripcion = t.getDescripcion();

                    if(hora.equals(tarea.getHora()) && descripcion.equals(tarea.getDescripcion())){

                        return index;
                    }

                   index ++; 
                }
        } catch (LeerDatosEx ex) {
             System.out.println("Error al intentar leer el recurso "+ex.getMessage());
        }
       return index;
    }
    
}
