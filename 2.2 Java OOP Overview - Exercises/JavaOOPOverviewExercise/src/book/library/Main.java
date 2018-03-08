package book.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Library library = new Library("firstLibrary");
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] data = reader.readLine().split(" ");
             String title = data[0];
             String author = data[1];
             String publisher = data[2];
             Date releaseDate = new SimpleDateFormat("dd.MM.yyyy").parse(data[3]);
             String isbn = data[4];
             double price = Double.parseDouble(data[5]);
             Book book = new Book(title, author, publisher, releaseDate, isbn, price);
             library.getBookList().add(book);
        }
        Map<String, Double> sortedLibrary = new HashMap<>();

        library
                .getBookList()
                .stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.summingDouble(Book::getPrice)))
                .forEach(sortedLibrary::putIfAbsent);

        sortedLibrary.entrySet().stream().sorted((o1, o2) -> {
            if(Double.compare(o2.getValue(), o1.getValue()) == 0){
                return o1.getKey().compareTo(o2.getKey());
            }
            return o2.getValue().compareTo(o1.getValue());
        }).forEach((x) -> System.out.printf("%s -> %.2f%n", x.getKey(), x.getValue()));
    }
}