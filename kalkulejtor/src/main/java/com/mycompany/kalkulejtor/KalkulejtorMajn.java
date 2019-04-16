/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kalkulejtor;

import java.util.Scanner;

/**
 *
 * @author student
 */
public class KalkulejtorMajn {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        double a = 0, b = 0;
        while (true) {
            try {
                System.out.println("Wybierz opcje\n"
                        + "1. dodawanie\n"
                        + "2. odejmowanie\n"
                        + "3. mnozenie\n"
                        + "4. dzielenie\n"
                        + "5. potegowanie\n"
                        + "6. pierwiastek\n"
                        + "7. odwrotnosc\n"
                        + "8. zmiana znaku\n"
                        + "9. koniec");
                String opcja = s.nextLine();
                switch (opcja) {
                    case "1":
                        System.out.println("Wybrałeś dodwanie, podaj pierwszą liczbę");
                        a = Double.parseDouble(s.nextLine());
                        System.out.println("Podaj drugą liczbę");
                        b = Double.parseDouble(s.nextLine());
                        System.out.println("Wynik: " + dodawanie(a, b));
                        break;
                    case "2":
                        System.out.println("Wybrałeś odejmowanie, podaj pierwszą liczbę");
                        a = Double.parseDouble(s.nextLine());
                        System.out.println("Podaj drugą liczbę");
                        b = Double.parseDouble(s.nextLine());
                        System.out.println("Wynik: " + odejmowanie(a, b));
                        break;
                    case "3":
                        System.out.println("Wybrałeś mnożenie, podaj pierwszą liczbę");
                        a = Double.parseDouble(s.nextLine());
                        System.out.println("Podaj drugą liczbę");
                        b = Double.parseDouble(s.nextLine());
                        System.out.println("Wynik: " + mnozenie(a, b));
                        break;
                    case "4":
                        System.out.println("Wybrałeś dzielenie, podaj pierwszą liczbę");
                        a = Double.parseDouble(s.nextLine());
                        System.out.println("Podaj drugą liczbę");
                        b = Double.parseDouble(s.nextLine());
                        System.out.println("Wynik: " + dzielenie(a, b));
                        break;
                    case "5":
                        System.out.println("Wybrałeś potęgowanie, podaj liczbę");
                        a = Double.parseDouble(s.nextLine());
                        System.out.println("Wynik: " + potega(a));
                        break;
                    case "6":
                        System.out.println("Wybrałeś pierwiastkowanie, podaj liczbę");
                        a = Double.parseDouble(s.nextLine());
                        System.out.println("Wynik: " + pierwiastek(a));
                        break;
                    case "7":
                        System.out.println("Wybrałeś odwrotność, podaj liczbę");
                        a = Double.parseDouble(s.nextLine());
                        System.out.println("Wynik: " + odwrotnosc(a));
                        break;
                    case "8":
                        System.out.println("Wybrałeś zmianę znaku, podaj liczbę");
                        a = Double.parseDouble(s.nextLine());
                        System.out.println("Wynik: " + znak(a));
                        break;
                    case "9":
                        System.out.println("Koniec programu");
                        System.exit(0);
                    default:
                        System.out.println("Nie ma takiej opcji");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nie podałeś liczby");
            }
        }
    }

    public static double dodawanie(double a, double b) {
        return a + b;
    }

    public static double odejmowanie(double a, double b) {
        return a - b;
    }

    public static double mnozenie(double a, double b) {
        return a * b;
    }

    public static double dzielenie(double a, double b) {
        return a / b;
    }

    public static double potega(double a) {
        return Math.pow(a, 2);
    }

    public static double pierwiastek(double a) {
        return Math.sqrt(a);
    }

    public static double odwrotnosc(double a) {
        return 1 / a;
    }

    public static double znak(double a) {
        return -a;
    }
}
