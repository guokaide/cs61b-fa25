public class Dessert {

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }

    static int numDesserts;

    int flavor;
    int price;

    public Dessert(int flavor, int price) {
        this.flavor = flavor;
        this.price = price;
        numDesserts++;
    }

    public void printDessert() {
        System.out.println(flavor + " " + price + " " + numDesserts);
    }
}
