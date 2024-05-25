package pl.edu.agh.mwo.invoice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.mwo.invoice.product.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DefaultPrinterTest {
    private DefaultPrinter defaultPrinter;

    @Before
    public void createDefaultPrinter() {
        defaultPrinter = new DefaultPrinter();
    }

    @Test
    public void testPrintHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        defaultPrinter.printHeader(12345, stringBuilder);
        Assert.assertTrue(stringBuilder.toString().contentEquals("Invoice no.: 12345" + System.lineSeparator()));
    }

    @Test
    public void testPrintTaxFreeProduct() {
        StringBuilder stringBuilder = new StringBuilder();
        defaultPrinter.printProduct(new TaxFreeProduct("Roślina", new BigDecimal("25")), 1, stringBuilder);
        Assert.assertTrue(stringBuilder.toString().contentEquals("Roślina 1 25" + System.lineSeparator()));
    }

    @Test
    public void testPrintDairyProduct() {
        StringBuilder stringBuilder = new StringBuilder();
        defaultPrinter.printProduct(new DairyProduct("Jogurt Skyr", new BigDecimal("4.99")), 10, stringBuilder);
        Assert.assertTrue(stringBuilder.toString().contentEquals("Jogurt Skyr 10 4.99" + System.lineSeparator()));
    }

    @Test
    public void testPrintOtherProduct() {
        StringBuilder stringBuilder = new StringBuilder();
        defaultPrinter.printProduct(new OtherProduct("Doniczka", new BigDecimal("20")), 2, stringBuilder);
        Assert.assertTrue(stringBuilder.toString().contentEquals("Doniczka 2 20" + System.lineSeparator()));
    }

    @Test
    public void testPrintFooter() {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Product, Integer> products = new HashMap<Product, Integer>();

        products.put(new TaxFreeProduct("Doniczka", new BigDecimal("20")), 1);
        products.put(new TaxFreeProduct("Roślina", new BigDecimal("25")), 2);

        defaultPrinter.printFooter(stringBuilder, products);
        Assert.assertTrue(stringBuilder.toString().contentEquals("Lista pozycji: 2" + System.lineSeparator()));
    }
}
