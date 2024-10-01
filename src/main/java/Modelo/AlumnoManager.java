/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author miguel
 */
import java.util.ArrayList;

public class AlumnoManager {
    private ArrayList<Alumno> alumnos;

    public AlumnoManager() {
        alumnos = new ArrayList<>();
    }

    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    public void borrarAlumno(String codigo) {
        alumnos.removeIf(alumno -> alumno.getCodigo().equals(codigo));
    }

    public Alumno buscarAlumno(String codigo) {
        for (Alumno alumno : alumnos) {
            if (alumno.getCodigo().equals(codigo)) {
                return alumno;
            }
        }
        return null; // Si no se encuentra
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }
}


