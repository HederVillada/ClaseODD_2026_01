public class PizzaBuilderDemo {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        
        // Let's make a Hawaiian Pizza
        PizzaBuilder hawaiianBuilder = new HawaiianPizzaBuilder();
        waiter.setPizzaBuilder(hawaiianBuilder);
        waiter.constructPizza();

        Pizza pizza = waiter.getPizza();
        System.out.println("Result: " + pizza);
    }
}