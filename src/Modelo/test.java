/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author YUSTIN
 */
public class test {public static void main(String[] args) {
         Interesado i1 = new Interesado("7429", "yustin","9978" ,"hola","@");
        Expediente e1 = new Expediente("alta", i1, "comite","doc");
          Interesado i2 = new Interesado("9680", "hernan","9978" ,"gaa","@");
        Expediente e2 = new Expediente("medio", i2, "comite","doc");
        Administrador.agregarFinalizado(e2);
    }
    
}
