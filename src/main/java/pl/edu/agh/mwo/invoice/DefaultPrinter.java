package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.util.Map;

public class DefaultPrinter implements PrinterService {

    @Override
    public void printHeader(Integer invoiceNumber, StringBuilder stringBuilder) {
        stringBuilder.append("Invoice no.: ")
                .append(invoiceNumber)
                .append(System.lineSeparator());
    }

    @Override
    public void printProduct(Product product, Integer quantity, StringBuilder stringBuilder) {
        stringBuilder.append(product.getName())
                .append(" ")
                .append(quantity)
                .append(" ")
                .append(product.getPrice())
                .append(System.lineSeparator());
    }

    @Override
    public void printFooter(StringBuilder stringBuilder, Map<Product, Integer> products) {
        stringBuilder.append("Lista pozycji: ")
                .append(products.entrySet()
                        .size())
                .append(System.lineSeparator());
    }
}