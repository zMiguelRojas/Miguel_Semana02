/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Calendar;
import java.util.Date;

public class Alumno {
    private String nombre;
    private int edad;
    private String codigo;
    private String telefono;
    private String carrera;
    private String semestre;

    // Constructor
    public Alumno(String nombre, Date fechaNacimiento, String codigo, String telefono, String carrera, String semestre) {
        this.nombre = nombre;
        this.edad = calcularEdad(fechaNacimiento);
        this.codigo = codigo;
        this.telefono = telefono;
        this.carrera = carrera;
        this.semestre = semestre;
    }

    public Alumno() {
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", edad=" + edad + ", codigo=" + codigo + ", telefono=" + telefono + ", carrera=" + carrera + ", semestre=" + semestre + '}';
    }

    // Método para calcular la edad a partir de la fecha de nacimiento
    private int calcularEdad(Date fechaNacimiento) {
        if (fechaNacimiento == null) return 0;

        Calendar hoy = Calendar.getInstance();
        Calendar nacimiento = Calendar.getInstance();
        nacimiento.setTime(fechaNacimiento);

        int edad = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
        if (hoy.get(Calendar.MONTH) < nacimiento.get(Calendar.MONTH) ||
            (hoy.get(Calendar.MONTH) == nacimiento.get(Calendar.MONTH) &&
             hoy.get(Calendar.DAY_OF_MONTH) < nacimiento.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }
        return edad;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }
}
