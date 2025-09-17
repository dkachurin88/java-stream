import java.util.List;

public class StreamCollectorsExample {
    static class Order {
        private String product;
        private double cost;

        public Order(String product, double cost) {
            this.product = product;
            this.cost = cost;
        }

        public String getProduct() {
            return product;
        }

        public double getCost() {
            return cost;
        }
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        var totalCostByProduct = orders.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        Order::getProduct,
                        java.util.stream.Collectors.summingDouble(Order::getCost)
                ));

        var top3Products = totalCostByProduct.entrySet().stream()
                .sorted(java.util.Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .toList();

        System.out.println("Три самых дорогих продукта:");
        for (int i = 0; i < top3Products.size(); i++) {
            var entry = top3Products.get(i);
            System.out.println((i + 1) + ". " + entry.getKey() + ": " + entry.getValue());
        }
    }
}