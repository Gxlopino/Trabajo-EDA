/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Representa a una persona interesada que inicia un trámite.
 *
 * @author YUSTIN
 */
public class Interesado {

    private String dni, nombres, telefono, tipo, email;

    /**
     * Constructor por defecto.
     */
    public Interesado() {
    }

    /**
     * Constructor para crear un nuevo interesado.
     *
     * @param dni DNI del interesado.
     * @param nombres Nombres y apellidos del interesado.
     * @param telefono Número de teléfono.
     * @param tipo Tipo de interesado (ej. "Externo").
     * @param email Correo electrónico.
     */
    
    public Interesado(String dni, String nombres, String telefono, String tipo, String email) {
        this.dni = dni;
        this.nombres = nombres;
        this.telefono = telefono;
        this.tipo = tipo;
        this.email = email;
    }

    // --- Getters ---
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEmail() {
        return email;
    }

    public void mostrar() {
        System.out.println("Interesado{" + "dni=" + dni + ", nombres=" + nombres + ", telefono=" + telefono + ", tipo=" + tipo + ", email=" + email + '}');
    }

    @Override
    public String toString() {
        return "Interesado{" + "dni=" + dni + ", nombres=" + nombres + ", telefono=" + telefono + ", tipo=" + tipo + ", email=" + email + '}';
    }

}
