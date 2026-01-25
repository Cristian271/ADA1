package main;

import java.io.FileNotFoundException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        CSV csv = new CSV();
        String usuario, contrasena;
        System.out.println("----------------");
        System.out.println("INICIO DE SESIÓN");
        System.out.println("----------------");

        System.out.println("INGRESE EL USUARIO: ");
        usuario = scanner.nextLine();
        System.out.println("INGRESE LA CONTRASEÑA: ");
        contrasena = scanner.nextLine();
        if(!csv.validar(usuario, contrasena)){
            System.exit(0);
        }

        System.out.println("----------------------------------");
        System.out.println("REGISTRO DE CALIFICACIONES FINALES");
        System.out.println("----------------------------------");

        boolean escaneable = true; // validaciones, para saber si ya seleccionó una opción antes
        List<Estudiante> alumnos = csv.read();
        if(escaneable){
            System.out.println("1) INGRESAR CALIFACIONES");
        }
        System.out.println("4) SALIR");
        int op = scanner.nextInt();


        while (op != 4) {

            switch (op) {
                case 1:

                    escaneable = false;
                    for (Estudiante alumno : alumnos) {
                        int grade = 0;
                        while (grade < 1 || grade > 100) {
                            System.out.print("Calificación de " + alumno.getFullName() + ": ");
                            try {
                                //grade = Integer.parseInt(scanner.nextLine());
                                grade = scanner.nextInt();
                                if (grade < 1 || grade > 100) {
                                    System.out.println("La calificacion debe ser valida (1-100).");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("El dato debe ser un número entero.");
                            }
                        }
                        alumno.setGrade(grade);
                    }
                    break;
                case 2:
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
                    break;
                case 3:
                    PDF.generar(alumnos, "out/Reporte_Calificaciones.pdf");

                    break;
            }
            if(!escaneable){
                System.out.println("2) GENERAR REPORTE DE CALIFICACIONES");
            }
            if(!escaneable) {
                System.out.println("3) GENERAR PDF");
            }
            System.out.println("4) SALIR");
            while(true) {
                op = scanner.nextInt();
                if (op == 1) {
                    if (!escaneable) {
                        System.out.println("No valido");
                        continue;
                    } else{
                        break;
                    }
                }

                if (op == 2) {
                    if (escaneable) {
                        System.out.println("No valido");
                        continue;
                    } else{
                        break;
                    }

                }

                if(op == 3) break;

                if (op >= 4) {
                    System.exit(0);
                }
            }




        }
    }
}
