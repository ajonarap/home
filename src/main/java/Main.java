import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Małgorzata Paciorkowska on 15.03.2017.
 */

public class Main {

    private BigDecimal calculate(String path){
        BigDecimal sum = new BigDecimal(0);
        ArrayList<String> listLine = new ArrayList<String>();
        Scanner s = null;
        try {
            s = new Scanner(new File(path));
            while (s.hasNext()) {
                listLine.add(s.next());
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }

        for (String line : listLine){
            sum = sum.add(getValue(line));
        }

        return sum;
    }

    private BigDecimal getValue(String line){
        BigDecimal amount = new BigDecimal(0);
        if(line.contains("@amount:")) {
            String amountString = line.split("@amount:")[1];
            amountString = amountString.replace("PLN", "");
            amountString = amountString.replace(",", ".");
            amount = new BigDecimal(amountString);
        }
        return amount;
    }

    public static void main(String[] args) {
        String path;
        if(args.length >0) {
            path = args[0];
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("Wprowadź ścieżkę do pliku:");
            path = in.nextLine();
        }
        Main main = new Main();
        System.out.println("Suma: " + main.calculate(path));
    }

}
