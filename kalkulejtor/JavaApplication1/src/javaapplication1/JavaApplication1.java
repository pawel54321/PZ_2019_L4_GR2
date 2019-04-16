package javaapplication1;

import java.util.Scanner;

public class JavaApplication1 {
    public static double suma(double a, double b) {
        return a+b;
    }
    
    public static double roznica(double a, double b) {
        return a-b;
    }
    
    public static double iloczyn(double a, double b) {
        return a*b;
    }
    
    public static double iloraz(double a, double b) {
        return a/b;
    }
    
    public static double kwadrat(double a) {
        return a*a;
    }
    
    public static double pierwiastek(double a) {
        return Math.sqrt(a);
    }
    
    public static double odwr(double a) {
        return 1/a;
    }
    
    public static double przec(double a) {
        return -a;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        double a;
        double b;
        int d;
        
        while(true) {
            System.out.print("a = ");
            a = s.nextDouble();
            System.out.print("b = ");
            b = s.nextDouble();
            System.out.println("Działanie: ");
            System.out.println("1. a + b");
            System.out.println("2. a - b");
            System.out.println("3. a * b");
            System.out.println("4. a / b");
            System.out.println("5. a ^ 2");
            System.out.println("6. sqrt(a)");
            System.out.println("7. 1 / a");
            System.out.println("8. -a");
            System.out.println();
            d = s.nextInt();
            System.out.println();
            
            switch(d) {
                case 1:
                    System.out.println("a + b = "+suma(a,b));
                    break;
                case 2:
                    System.out.println("a - b = "+roznica(a,b));
                    break;
                case 3:
                    System.out.println("a * b = "+iloczyn(a,b));
                    break;
                case 4:
                    if(b != 0) {
                        System.out.println("a / b = "+iloraz(a,b));
                    } else {
                        System.out.println("Błąd: dzielenie przez 0");
                    }
                    break;
                case 5:
                    System.out.println("a ^ 2 = "+kwadrat(a));
                    break;
                case 6:
                    System.out.println("sqrt(a) = "+pierwiastek(a));
                    break;
                case 7:
                    if(a != 0) {
                        System.out.println("1 / a = "+odwr(a));
                    } else {
                        System.out.println("Błąd: dzielenie przez 0");
                    }
                    break;
                case 8:
                    System.out.println("-a = "+przec(a));
                    break;
                default:
                    System.out.println("Niepoprawne działanie");
            }
            
            System.out.println();
            System.out.println("0.Wyjście");
            d = s.nextInt();
            if(d == 0) {
                break;
            }
            System.out.println("/----------/");
        }
    }
}