/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab6p1_kelvinmelgar;
import java.security.SecureRandom;
import java.util.Scanner;
/**
 *
 * @author kelvi
 */
public class Lab6P1_KelvinMelgar {
    static SecureRandom n_aleat = new SecureRandom();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        
        int opcion;
        do{
            System.out.println("<-- Laboratorio 6 -->");
            System.out.println("1. Turing.");
            System.out.println("2. en proceso....");
            System.out.println("3. Salir.\n");
            System.out.print("Ingrese una opcion: ");
            opcion = leer.nextInt();
            
            switch(opcion){
                case 1:{
                    int taman;
                    
                    System.out.println("<--- Turing --->");
                    System.out.println("Intrucciones");
                    System.out.println("R: Mueve el puntero una casilla a la derecha.");
                    System.out.println("L: Mueve el puntero una casilla a la izquierda.");
                    System.out.println("X. Agregue el elemento al que esta apuntado.");
                    System.out.print("\nIngrese un tamanio: ");
                    taman = leer.nextInt();
                    
                    int[] arreglo_g = generarArreglo(taman); 
                    
                    imprimirArreglo(arreglo_g);
                    
                    System.out.print("\nCadena de instrucciones [RLX]: ");
                    String cadena_u = leer.next();
                    
                    if(verificarCadena(cadena_u)){
                        System.out.print("Cadena generada: ");
                        System.out.println(turing(cadena_u, arreglo_g));
                        
                    }else{
                        System.out.println("Cadena invalida...");
                    }
                    
                    break;
                }//fin del turing
                
                
                case 2:{
                    System.out.println("<--- Constante de Kaprekar --->");
                    System.out.print("Ingrese un numero: ");
                    int n_usuario = leer.nextInt();
                    System.out.println();
                    
                    if(verificarNumeroD(n_usuario)){
                        System.out.println("Tiene 4 digitos");
                        
                        if(NumerosIguales(n_usuario)){
                            
                            int[] numeros = numerosArreglos(4);
                            
                            System.out.println("Arreglo ascendente.");
                            imprimirArreglo(arregloAscend(numeros));
                            
                        }else{
                            System.out.println("Son iguales");
                        }
                    }else{
                        System.out.println("no tiene 4 digitos");
                    }
                    
                    break;
                }
            }
            
            
            
        }while(opcion != 3);
        
    }
    
    public static int[] generarArreglo(int tam){
        //Genera el arreglo
        int[] temp = new int[tam];
        
        for (int i = 0; i < temp.length; i++) {
            temp[i] = n_aleat.nextInt(10);
        }
        
        return temp;
    }
    
    public static void imprimirArreglo(int[] x){
        
        for (int i = 0; i < x.length; i++) {
            System.out.print("[" + x[i] + "]");
        }
        
        System.out.println();
        
    }
    
    public static boolean verificarCadena(String cadena){
        boolean paso = true;
        
        cadena = cadena.toUpperCase();
        
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            
            switch(c){
                case 'R', 'X', 'L' -> {
                }
                default -> {
                    paso = false;
                }
            }//fin del switch
            
            if(paso == false){
                break;
            }
        }
        
        return paso;
    }
    
    public static String turing(String instruc, int[] arreglo){
        instruc = instruc.toUpperCase();
        int apuntador = 0;
        String cadena_result = "";
        
        
        for (int i = 0; i < instruc.length(); i++) {
            char l = instruc.charAt(i);
            
            if (apuntador >= 0 && apuntador < arreglo.length) {
                switch (l) {
                    case 'R': {
                        apuntador += 1;
                        break;
                    }
                    case 'L': {
                        apuntador -= 1;
                        break;
                    }
                    case 'X': {
                        cadena_result = cadena_result + arreglo[apuntador];
                        break;
                    }
                }
            }else{
                System.out.println("Posicion se salio del rango.\n");
                return cadena_result;
            }
        }
        
        return cadena_result;
    }
    
    public static boolean verificarNumeroD(int n){
        boolean paso;
        int acum_d = 0;
        
        //Cuenta los digitos 
        while(n > 0){
            n = n/10;
            acum_d++;
        }
        
        paso = acum_d == 4;
        
        return paso;
    }
    
    //Verifica si son iguales
    public static boolean NumerosIguales(int n){
        boolean paso;
        int[] numeros = new int[4];
        int acum_ig = 1;
        
        numeros = numerosArreglos(n);
        
        //comprueba los numeros iguales
        for (int i = 0; i < 3; i++) {
            int a = numeros[i];
            int b = numeros[i+1];
            
            if(a == b){
                acum_ig += 1;
            }else{
                acum_ig = 0;
            }
            
        }
        
        paso = acum_ig != 4;
        return paso;
        
    }
    
    //Asigna a un arreglo el numero
    public static int[] numerosArreglos(int numero){
        int[] temp = new int[4];
        
        for (int i = 3; i >= 0; i--) {
            temp[i] = numero%10;
            numero = numero / 10;
        }
        
        return temp;
    }
    
    //Convierte un arreglo de numero a int
    public static int arregloAnumero(int[] arreglo){
        String numero_s = "";
        
        for (int i = 0; i < arreglo.length; i++) {
            numero_s = numero_s + arreglo[i];
        }
        
        int numero = Integer.parseInt(numero_s);
        
        return numero;
    }
    
    //Ordena de manera ascendente.
    public static int[] arregloAscend(int[] arreglo){
        int[] temp = new int[arreglo.length];
        
        for (int i = 0; i < arreglo.length; i++) {
            int a = arreglo[i];
            
            for (int j = i + 1; j < arreglo.length; j++) {
                
               if(a < arreglo[j] || arreglo.length-1 == j){
                   temp[j-1] = a;
                   
               }
                   
                
            }   
        }
        
        return temp;
    }
}
