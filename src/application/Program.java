package application;

import entities.Contract;
import services.ContractService;
import services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter contract data");
        System.out.print("Number: ");
        int number = sc.nextInt();
        System.out.print("Date (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Contract value: ");
        double totalValue = sc.nextDouble();

        Contract obj = new Contract(number, date, totalValue);

        System.out.print("Enter number of installments: ");
        int n = sc.nextInt();

        ContractService contractService = new ContractService(new PaypalService());

        contractService.processContract(obj, n);

        System.out.println("Installments:");

        for (int i = 0; i < obj.getInstallments().size(); i++) {
            System.out.println(obj.getInstallments().get(i));
        }
        sc.close();
    }
}