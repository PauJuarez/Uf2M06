package Tasque2;

import java.util.List;
import java.util.Scanner;

public class View {
    private static final Scanner scanner = new Scanner(System.in);

    public static int showMenu() {
        int option = -1;

        while (option == -1) {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar deporte");
            System.out.println("2. Agregar atleta");
            System.out.println("3. Buscar atleta por nombre");
            System.out.println("4. Listar atletas por deporte");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                option = scanner.nextInt();
                if (option < 1 || option > 5) {
                    System.out.println("Opción inválida. Por favor, ingrese un número entre 1 y 5.");
                    option = -1; 
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entre 1 y 5.");
                scanner.nextLine(); 
            }
        }
        return option;
    }

    public static String sportForm() {
        scanner.nextLine();
        System.out.print("Ingrese el nombre del deporte: ");
        return scanner.nextLine();
    }

    public static AthleteClass athleteForm() {
        scanner.nextLine();
        System.out.print("Ingrese el nombre del atleta: ");
        String name = scanner.nextLine();

        int sportId = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Ingrese el ID del deporte: ");
                sportId = scanner.nextInt();
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero para el ID del deporte.");
                scanner.nextLine();
            }
        }

        return new AthleteClass(name, sportId);
    }

    public static String askAthlete() {
        scanner.nextLine();
        System.out.print("Ingrese el nombre del atleta (parcial): ");
        return scanner.nextLine();
    }

    public static int askSport(List<SportClass> sports) {
        System.out.println("Deportes disponibles:");
        sports.forEach(s -> System.out.println(s.getId() + ". " + s.getName()));
        int sportId = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Seleccione el ID del deporte: ");
                sportId = scanner.nextInt();
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero para el ID del deporte.");
                scanner.nextLine();
            }
        }
        return sportId;
    }

    public static void printAthletes(List<AthleteClass> athletes) {
        if (athletes.isEmpty()) {
            System.out.println("No se encontraron atletas.");
        } else {
            athletes.forEach(View::printAthlete);
        }
    }

    public static void printAthlete(AthleteClass athlete) {
        System.out.println("ID: " + athlete.getId() + ", Nombre: " + athlete.getName());
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }
}
