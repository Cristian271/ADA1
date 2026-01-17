package main;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSV {
    private BufferedReader lector; //Lee el archivo
    private String linea; // recibe la linea de cada fila
    private String partes[]; // almacena cada linea leida
    public static final String RUTA = "src/main/ListaDeAlumnos.csv";
    public static final String RUTA_DEST = "src/main/Reporte_Califaciones.csv";



    public List<Estudiante> read() throws FileNotFoundException {
        List<Estudiante> lista = new ArrayList<>();
      try{
          lector = new BufferedReader(new FileReader(RUTA));
          while((linea = lector.readLine()) != null){
              partes = linea.split(",");
              if (partes.length >= 4) {
                  // Creamos el objeto de estudiante con los datos del cdv
                  Estudiante e = new Estudiante(partes[0], partes[1], partes[2], partes[3]); lista.add(e);
              }

          }

          lector.close();
      } catch (Exception e){
          JOptionPane.showMessageDialog(null, e);
      }
      return lista;
    }

    public void print(){
        for(int i = 0; i < partes.length; i++) System.out.println(partes[i] + "  |  ");
    }

    public void generarReporte(List<Estudiante> lista) {
        try (PrintWriter archivoSalida = new PrintWriter(new FileWriter(RUTA_DEST))) {
            for (Estudiante e : lista) {
                archivoSalida.println(e.getMatricula() + ",Diseño de Software," + e.getGrade());
            }
            JOptionPane.showMessageDialog(null, "Archivo generado con éxito.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
        }
    }


}
