package beer.counter;

public class BeerCounter {
    private static int beerInStock = 0;
    private static int drankBeers = 0;

    public BeerCounter() { }

    public static int getBeerInStock() {
        return beerInStock;
    }

    public static int getDrankBeers() {
        return drankBeers;
    }

    public static void buyBeer(int beers) {
        BeerCounter.beerInStock += beers;
    }

    public static void drinkBeer(int beers) {
        BeerCounter.beerInStock -= beers;
        BeerCounter.drankBeers += beers;
    }
}
