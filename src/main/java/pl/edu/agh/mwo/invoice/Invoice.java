package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.CustomException;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();
    private static int lastInvoiceNumber;
    private final int invoiceNumber;
    private PrinterService printer;

    public Invoice(PrinterService printer) {
        this.invoiceNumber = ++lastInvoiceNumber;
        this.printer = printer;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice()
                    .multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax()
                    .multiply(quantity));
        }
        return totalGross;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public String printInvoice() {
        StringBuilder stringBuilder = new StringBuilder();

        if (products.isEmpty()) {
            throw new CustomException("Empty invoice!");
        }

        printer.printHeader(invoiceNumber, stringBuilder);
        products.forEach((product, quantity) -> printer.printProduct(product,
                quantity,
                stringBuilder));
        printer.printFooter(stringBuilder, products);

        return stringBuilder.toString();
    }

    public int getProductQuantity(Product product) {
        return products.get(product);
    }
}
