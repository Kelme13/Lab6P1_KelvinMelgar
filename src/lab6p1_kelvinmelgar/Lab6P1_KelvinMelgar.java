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
            System.out.println("2. Constante de Kaprekar.");
            System.out.println("3. Salir.\n");
            System.out.print("Ingrese una opcion: ");
            opcion = leer.nextInt();
            
            switch(opcion){
                case 1 -> {
                    int taman;
                    
                    System.out.println("<--- Turing --->");
                    System.out.println("Intrucciones");
                    System.out.println("R: Mueve el puntero una casilla a la derecha.");
                    System.out.println("L: Mueve el puntero una casilla a la izquierda.");
                    System.out.println("X. Agregue el elemento al que esta apuntado.");
                    System.out.print("\nIngrese un tamanio: ");
                    taman = leer.nextInt();
                    
                    int[] arreglo_g = generarArreglo(taman); 
                    
                    System.out.print("Arreglo generado: ");
                    imprimirArreglo(arreglo_g);
                    
                    System.out.print("\nCadena de instrucciones [RLX]: ");
                    String cadena_u = leer.next();
                    
                    if(verificarCadena(cadena_u)){
                        System.out.print("Cadena generada: ");
                        System.out.println(turing(cadena_u, arreglo_g));
                        
                    }else{
                        System.out.println("Cadena invalida...");
                    }
                    
                    System.out.println();
                }//fin del turing
                
                case 2 -> {
                    System.out.println("<--- Constante de Kaprekar --->");
                    System.out.print("Ingrese un numero: ");
                    int n_usuario = leer.nextInt();
                    System.out.println();
                    
                    if(verificarNumeroD(n_usuario)){
                        if(NumerosIguales(n_usuario)){
                            
                            kaprekar(n_usuario);
                            
                            System.out.println();
                            
                        }else{
                            System.out.println("Son iguales");
                        }
                    }else{
                        System.out.println("no tiene 4 digitos");
                    }
                    
                    break;
                }
                
                case 3 -> {
                    System.out.println("Saliendo...");
                    break;
                }
                
                default ->{
                    System.out.println("Opcion invalida...\n");
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
                    paso = true;
                    break;
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
            
            //evalua si el apuntador esta en el rango del arreglo
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
        
        numeros = asignarArreglo(n);
        
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
    public static int[] asignarArreglo(int numero){
        //para sacar el tamaño (no es necesario pero para saber xD)
        int n = numero;
        int acum_d = 0;
        while(n > 0){
            n = n/10;
            acum_d++;
        }
        int[] temp = new int[acum_d];
        int posc = acum_d - 1; //seria 3
        
        while(numero > 0) {
            temp[posc] = numero%10;
            numero = numero / 10;
            posc--;
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
        
        //Itera hasta antes de la ultima posicion del arreglo
        for (int i = 0; i < arreglo.length-1; i++) {
            
            //inicia una unidad adelante del elemento para iterar en todo lo que resta del arreglo
            for (int j = i + 1; j < arreglo.length; j++) {
                
                //Verifica si el primer elemento(i) es mayor que los que estan adelante(j)
                if(arreglo[i] > arreglo[j]){
                    /*
                    Quiere decir que el que esta adelante de 'i'(j) debe ir antes, entonces con 'n' guarda el numero de 'i' y a la posicion
                    de 'i' le asigno lo que esta en j, y a la posicion de j lo que antes tenia i (n) 
                    */
                    int n = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = n;
                    
                    /*
                    con esto consigo posicionar el numero menor de todo el arreglo primero,
                    despues paso a la siguiente posicion, que podria ser un numero 
                    diferente al arreglo original y vuelve a evaluar ese numero con 
                    lo que resta del arreglo y asi posiciono el que le sigue al mas menor 
                    y termina por comprobar todo el arreglo.
                    */
                }
            }
        }
        return arreglo;
    }
    
    //Ordena de manera descendente
    public static int[] arregloDescend(int[] arreglo){
        for (int i = 0; i < arreglo.length-1; i++) {
            for (int j = i + 1; j < arreglo.length; j++) {
                
                if(arreglo[i] < arreglo[j]){
                    int n = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = n;
                }
            }
        }
        return arreglo;
    }
    
    //metodo completito de kaprekar
    public static void kaprekar(int numero){
        
        for (int i = 0; i < 7; i++) {
            if(numero == 6174){
                break;
            }
            
            int[] numeros_a = arregloDescend(asignarArreglo(numero));
            int[] numeros_b = arregloAscend(asignarArreglo(numero));
            int nums_a = arregloAnumero(numeros_a);
            int nums_b = arregloAnumero(numeros_b);
            
            if(nums_a < nums_b){
                numero = nums_b - nums_a;
                System.out.printf("%d - %d = %d", nums_b, nums_a, numero);
            }else{
                numero = nums_a - nums_b;
                System.out.printf("%d - %d = %d", nums_a, nums_b, numero);

            }
            System.out.println();
            
        }//fin del for
        
    }
}
