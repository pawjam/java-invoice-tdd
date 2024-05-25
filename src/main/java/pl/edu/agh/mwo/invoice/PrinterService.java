package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.util.Map;

public interface PrinterService {
    void printProduct(Product product, Integer quantity, StringBuilder stringBuilder);

    void printHeader(Integer invoiceNumber, StringBuilder stringBuilder);

    void printFooter(StringBuilder stringBuilder, Map<Product, Integer> products);
}
