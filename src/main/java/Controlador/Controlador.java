/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Registro;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Modelo.Alumno; 
import java.util.Calendar;
import java.util.List;

public class Controlador {
    private Registro vista;
    private List<Alumno> listaAlumnos;

    public Controlador(Registro vista) {
        this.vista = vista;
        this.listaAlumnos = new ArrayList<>();
        
        // Agregar listener para el botón de agregar
        this.vista.getBtnAgregar().addActionListener(e -> agregarAlumno());
        
        // Agregar listener para el botón de borrar
        this.vista.getBtnBorrar().addActionListener(e -> {
            int selectedRow = vista.getjTabla().getSelectedRow();
            borrarAlumno(selectedRow);
        });
        
        // Agregar listener para el botón de actualizar
        this.vista.getBtnActualizar().addActionListener(e -> {
            int selectedRow = vista.getjTabla().getSelectedRow();
            actualizarAlumno(selectedRow);
        });
        
        // Agregar listener para el botón de buscar
        this.vista.getBtnBuscar().addActionListener(e -> buscarAlumno(vista.getTxtBuscar().getText()));
    }


public void agregarAlumno() {
    // Obtener los valores de los campos de texto y combo box
    String nombre = vista.getTxtNombre().getText();
    String codigoEstudiante = vista.getTxtCodigo().getText();
    String telefono = vista.getTxtTelefono().getText();
    Date fechaSeleccionada = vista.getjEdad().getDate();
    String carrera = (String) vista.getjComboBoxCarrera().getSelectedItem();
    String semestre = (String) vista.getjComboBoxSemestre().getSelectedItem();

    boolean hayError = false;

    // Verificar si hay campos vacíos
    if (nombre.isEmpty() || codigoEstudiante.isEmpty() || telefono.isEmpty() || fechaSeleccionada == null || carrera == null || semestre == null) {
        // Solo mostrar el mensaje una vez
        if (!hayError) {
            // Mensaje de error, solo una vez
            System.out.println("Por favor, completa todos los campos obligatorios.");
            hayError = true; // Cambiar el estado para evitar mostrarlo de nuevo
        }
    } else {
        // Crear nuevo Alumno, la edad se calcula dentro del constructor
        Alumno nuevoRegistro = new Alumno(nombre, fechaSeleccionada, codigoEstudiante, telefono, carrera, semestre);
        listaAlumnos.add(nuevoRegistro);
        
        // Agregar a la tabla
        DefaultTableModel model = (DefaultTableModel) vista.getjTabla().getModel();
        model.addRow(new Object[]{nombre, codigoEstudiante, nuevoRegistro.getEdad(), telefono, carrera, semestre}); // Usar getEdad()

        // Mensaje de éxito
        System.out.println("Registro agregado con éxito.");
        
        // Limpiar los campos
        limpiarCampos();
    }
}


private void limpiarCampos() {
    vista.getTxtNombre().setText("");
    vista.getTxtCodigo().setText("");
    vista.getTxtTelefono().setText("");
    vista.getjEdad().setDate(null);
    vista.getjComboBoxCarrera().setSelectedIndex(0);
    vista.getjComboBoxSemestre().setSelectedIndex(0);
}
private int calcularEdad(Date fechaNacimiento) {
    if (fechaNacimiento == null) return 0;
    Calendar fechaNac = Calendar.getInstance();
    fechaNac.setTime(fechaNacimiento);
    Calendar hoy = Calendar.getInstance();
    
    int edad = hoy.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
    if (hoy.get(Calendar.MONTH) < fechaNac.get(Calendar.MONTH) ||
        (hoy.get(Calendar.MONTH) == fechaNac.get(Calendar.MONTH) && hoy.get(Calendar.DAY_OF_MONTH) < fechaNac.get(Calendar.DAY_OF_MONTH))) {
        edad--;
    }
    return edad;
}
    private void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.getjTabla().getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de actualizar
        for (Alumno alumno : listaAlumnos) {
            modelo.addRow(new Object[]{
                alumno.getNombre(), 
                alumno.getCodigo(), 
                alumno.getEdad(), 
                alumno.getTelefono(), 
                alumno.getCarrera(), 
                alumno.getSemestre()
            });
        }
    }
    
public void borrarAlumno(int selectedRow) {
    // Obtener la fila seleccionada
    selectedRow = vista.getjTabla().getSelectedRow(); // Asignar la fila seleccionada

    if (selectedRow != -1) {
        // Eliminar el registro de la lista
        listaAlumnos.remove(selectedRow);
        
        // Eliminar la fila seleccionada del modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) vista.getjTabla().getModel();
        model.removeRow(selectedRow);

        // Mostrar mensaje de confirmación
        JOptionPane.showMessageDialog(vista, "Registro borrado con éxito");
    } else {
        // Si no hay fila seleccionada, mostrar mensaje de error
        JOptionPane.showMessageDialog(vista, "Por favor selecciona un registro para borrar.");
    }
}

    public void actualizarAlumno(int selectedRow) {
        if (selectedRow >= 0 && selectedRow < listaAlumnos.size()) {
            // Obtener datos actualizados de la vista
            String nombre = vista.getTxtNombre().getText();
            String codigo = vista.getTxtCodigo().getText();
            String telefono = vista.getTxtTelefono().getText();
            Date fechaNacimiento = vista.getjEdad().getDate();
            String carrera = (String) vista.getjComboBoxCarrera().getSelectedItem();
            String semestre = (String) vista.getjComboBoxSemestre().getSelectedItem();

            // Actualizar el Alumno en la lista
            Alumno alumnoActualizado = listaAlumnos.get(selectedRow);
            alumnoActualizado.setNombre(nombre);
            alumnoActualizado.setCodigo(codigo);
            alumnoActualizado.setTelefono(telefono);
            alumnoActualizado.setEdad(calcularEdad(fechaNacimiento));
            alumnoActualizado.setCarrera(carrera);
            alumnoActualizado.setSemestre(semestre);

            actualizarTabla();
            JOptionPane.showMessageDialog(vista, "Registro actualizado con éxito");
        } else {
            JOptionPane.showMessageDialog(vista, "Selecciona un registro para actualizar");
        }
    }

    public void buscarAlumno(String text) {
        // Filtrar la lista de alumnos y actualizar la tabla
        DefaultTableModel modelo = (DefaultTableModel) vista.getjTabla().getModel();
        modelo.setRowCount(0); // Limpiar tabla antes de buscar
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getNombre().contains(text) || alumno.getCodigo().contains(text)) {
                modelo.addRow(new Object[]{
                    alumno.getNombre(), 
                    alumno.getCodigo(), 
                    alumno.getEdad(), 
                    alumno.getTelefono(), 
                    alumno.getCarrera(), 
                    alumno.getSemestre()
                });
            }
        }
    }


}
