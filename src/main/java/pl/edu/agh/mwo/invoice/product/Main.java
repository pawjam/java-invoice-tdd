package pl.edu.agh.mwo.invoice.product;

import pl.edu.agh.mwo.invoice.DefaultPrinter;
import pl.edu.agh.mwo.invoice.Invoice;

import java.math.BigDecimal;

public class Main {
    
    public static void main(String[] args) {
        Invoice invoice = new Invoice(new DefaultPrinter());
        invoice.addProduct(new DairyProduct("Ser", new BigDecimal(8)), 6);
        System.out.println(invoice.printInvoice());
    }
}
