package main;

import java.io.FileNotFoundException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CSV csv = new CSV();
        List<Estudiante> alumnos = csv.read();
        Scanner scanner = new Scanner(System.in);
        System.out.println("REGISTRO DE CALIFICACIONES FINALES");

        for (Estudiante alumno : alumnos) {
            int grade = 0;
            while (grade < 1 || grade > 100) {
                System.out.print("Calificación de " + alumno.getFullName() + ": ");
                try {
                    grade = Integer.parseInt(scanner.nextLine());
                    if (grade < 1 || grade > 100) {
                        System.out.println("La calificacion debe ser valida (1-100).");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("El dato debe ser un número entero.");
                }
            }
            alumno.setGrade(grade);
        }
        // Esta verificacion segun yo no es necesaria hasta ahora pero veo que esta como un punto de la tarea entonces no se
        boolean allGraded = true;
        for (Estudiante alumno : alumnos) {
            if (alumno.getGrade() == 0) {
                allGraded = false;
                break;
            }
        }
        if (allGraded) {
            csv.generarReporte(alumnos);
        } else {
            System.out.println("Faltan alumnos por calificar");
        }
    }


}
