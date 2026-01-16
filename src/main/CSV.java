package main;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CSV {
    private BufferedReader lector; //Lee el archivo
    private String linea; // recibe la linea de cada fila
    private String partes[]; // almacena cada linea leida
    public static final String RUTA = "src/main/ListaDeAlumnos.csv";



    public void read() throws FileNotFoundException {
      try{
          lector = new BufferedReader(new FileReader(RUTA));
          while((linea = lector.readLine()) != null){
              partes = linea.split(",");
              print();
              System.out.println();
          }

          lector.close();
      } catch (Exception e){
          JOptionPane.showMessageDialog(null, e);
      }
    }

    public void print(){
        for(int i = 0; i < partes.length; i++) System.out.println(partes[i] + "  |  ");
    }



}
