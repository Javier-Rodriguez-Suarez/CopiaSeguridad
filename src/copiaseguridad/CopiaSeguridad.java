
package copiaseguridad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class CopiaSeguridad {

    public static void main(String[] args){
        System.out.println("Introduzca la ruta del directorio que desee copiar");
        File ruta=new File(pedircadena());
        
        System.out.println("Introduzca el destino del directorio que desee copiar");
        File destino=new File(pedircadena());
        
        String[] nombre_archivos=ruta.list();
        
        recorrido(nombre_archivos, ruta);
        System.out.println("-------------------------------------------------------------");
        copiar(ruta, destino);
        
        System.out.println("Directorios y archivos copiados");
        System.out.println("Directorios y archivos copiados");
    }
    
    private static void copiar(File inicio, File fin){                                      //Función que copia los archivos y directorios recursivamente

        if (inicio.isDirectory()){
            if (!fin.exists()){
                fin.mkdir();
            }
            String archivos[]=inicio.list();
             
            for (String file:archivos){                                                     //Copia los archivos del directorio desde la ruta inicial hasta la de destino
                File inicio_archivo=new File(inicio, file);
                File fin_archivo=new File(fin, file);
                copiar(inicio_archivo, fin_archivo);
            }
        }
        else{
            try {
                Files.copy(inicio.toPath(), fin.toPath(), StandardCopyOption.REPLACE_EXISTING);         //Si no se trata de un directorio, copia los archivos a la dirección de destino y los reemplaza en caso de que existan
            } catch (IOException ex) {
                System.out.println("Error: "+ex.getMessage());
            }
        }
    }
    
    
    public static void recorrido(String[] archivo, File ruta){                    //Función que recorre los archivos del directorio
        
        ArrayList<String> txt = new ArrayList<>();
        ArrayList<String> java = new ArrayList<>();
        ArrayList<String> clas = new ArrayList<>();
        ArrayList<String> dir = new ArrayList<>();
        int i=0;
        
        if(ruta.isDirectory()){
            dir.add(ruta.toString());
            String[] archivos=ruta.list();
            condicion(archivo, i, txt, java, clas);
            for (String file : archivos){
                File inicio_archivo=new File(ruta, file);
                
                recorrido(archivo, inicio_archivo);
                condicion(archivos, i, txt, java, clas);
                i++;
            }
        }
        mostrarstats(txt, java, clas, dir);
    }
    
    
    public static void condicion(String[] archivo, int num, ArrayList<String> txt, ArrayList<String> java, ArrayList<String> clas){              //Función que comprueba el tipo de archivo
        if(archivo[num].endsWith(".txt")){
            txt.add(archivo[num]);
        }
        else if(archivo[num].endsWith(".java")){
            java.add(archivo[num]);
        }
        else if(archivo[num].endsWith(".class")){
            clas.add(archivo[num]);
        }
    }
    
    public static void mostrarstats(ArrayList<String> txt, ArrayList<String> java, ArrayList<String> clas, ArrayList<String> dir){                     //Muestra el número de cada tipo de archivo
        ListIterator<String> itr;
        itr=dir.listIterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        
        itr=txt.listIterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        
        itr=java.listIterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        
        itr=clas.listIterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
    
    
    public static String pedircadena(){
        String str;
        Scanner teclado=new Scanner(System.in);
        str=teclado.nextLine();
        return str;
    }
}