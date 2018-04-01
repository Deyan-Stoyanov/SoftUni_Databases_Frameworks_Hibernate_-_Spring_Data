package org.bookshopsystem;

import org.bookshopsystem.enums.AgeRestriction;
import org.bookshopsystem.enums.EditionType;
import org.bookshopsystem.models.entities.Author;
import org.bookshopsystem.models.entities.Book;
import org.bookshopsystem.models.entities.Category;
import org.bookshopsystem.services.AuthorService;
import org.bookshopsystem.services.BookService;
import org.bookshopsystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Component

public class Runner implements CommandLineRunner {

    private static final String AUTHORS_RESOURCE_FILE = System.getProperty("user.dir") + "/src/main/resources/" +  "authors.txt";

    private static final String BOOKS_RESOURCE_FILE = System.getProperty("user.dir") + "/src/main/resources/" +  "books.txt";

    private static final String CATEGORIES_RESOURCE_FILE = System.getProperty("user.dir") + "/src/main/resources/" +  "categories.txt";

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public Runner(AuthorService authorService, BookService bookService, CategoryService categoryService) throws IOException {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        initAuthors();
        initCategories();
        initBooks();

        findBooksByAgeRestr(reader.readLine());
        findAllByEditionTypeAndCopiesLessThan("GOLD", 5000);
        findBooksBetweenPrices(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
        findBooksNotPublished(Integer.parseInt(reader.readLine()));
        findBooksReleasedBefore(reader.readLine());
        findAuthorsEndingWith(reader.readLine());
        findBooksContaining(reader.readLine());
        booksWhoseAuthorsStartWith(reader.readLine());
        findBooksWithTitleLongerThan(Integer.parseInt(reader.readLine()));
        orderAuthorsBySumOfBookCopies();
        releasedBooks(reader.readLine());
        increaseCopiesOfBooksAfter(reader.readLine(), Integer.parseInt(reader.readLine()));
        deleteBooksWithCopiesLessThan(Integer.parseInt(reader.readLine()));
        getTotalBooksOfAuthor(reader.readLine());
    }

    private void initCategories() throws IOException {
        List<String> allCategories = Files.readAllLines(Paths.get(CATEGORIES_RESOURCE_FILE));
        List<Category> categories = getCategories(allCategories);
        categories.forEach(this.categoryService::addCategoryToDb);
    }

    private static List<Category> getCategories(List<String> list){
        return list.stream().filter(x -> !x.isEmpty()).map(c -> {
            Category category = new Category();
            category.setName(c);
            return category;
        }).collect(Collectors.toList());
    }

    private void initAuthors() throws IOException {
        List<String> allAuthors = Files.readAllLines(Paths.get(AUTHORS_RESOURCE_FILE));
        List<Author> authors =  getAuthors(allAuthors);
        authors.forEach(this.authorService::saveAuthorToDb);
    }

    private static List<Author> getAuthors(List<String> list){
        return list.stream().map(s -> {
            String[] names = s.split("\\s+");
            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);
            return author;
        }).collect(Collectors.toList());
    }

    private void initBooks() throws IOException, ParseException {
        List<String> listOfBooks = Files.readAllLines(Paths.get(BOOKS_RESOURCE_FILE));
        List<Author> allAuthors = this.authorService.getAll();
        List<Category> categories = this.categoryService.getAll();

        Random random = new Random();
        List<Book> allBooks = new ArrayList<>();
        for (String line : listOfBooks) {
            String[] data = line.split("\\s+");

            int authorIndex = random.nextInt(allAuthors.size());
            Author author = allAuthors.get(authorIndex);
            int editionTypeIndex = Integer.parseInt(data[0]);
            EditionType[] editionTypes = EditionType.values();
            EditionType editionType = editionTypes[editionTypeIndex];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType.toString());
            book.setDateTime(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction.toString());
            book.setTitle(title);
            book.getCategories().add(categories.get(random.nextInt(categories.size())));
            allBooks.add(book);
        }

        allBooks.forEach(this.bookService::saveBookToDb);
    }

    private void findBooksByAgeRestr(String restriction){
        System.out.printf("Books with age restriction %s: %n", restriction);
        this.bookService.findAllByAgeRestriction(restriction).forEach(x -> System.out.println(x.getTitle()));
    }

    private void findAllByEditionTypeAndCopiesLessThan(String type, Integer copies){
        System.out.printf("Books of the %s edition with less than %d copies: %n", type, copies);
        this.bookService.findAllByEditionTypeAndCopiesLessThan(type, copies).forEach(x -> System.out.println(x.getTitle()));
    }

    private void findBooksBetweenPrices(BigDecimal low, BigDecimal high){
        System.out.printf("Books less than %.2f and higher than %.2f: %n", low, high);
        this.bookService.findAllByPriceLessThanOrPriceGreaterThan(low, high).forEach(x -> System.out.printf("%s - $%.2f%n", x.getTitle(), x.getPrice()));
    }

    private void findBooksNotPublished(Integer yearInt) throws ParseException {
        System.out.printf("Books not published in %d: %n", yearInt);
        Date firstDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-" + yearInt);
        Date secondDate = new SimpleDateFormat("dd-MM-yyyy").parse("31-12-" + yearInt);
        this.bookService.findAllByDateTimeBeforeOrDateTimeAfter(firstDate, secondDate).forEach(x -> System.out.println(x.getTitle()));
    }

    private void findBooksReleasedBefore(String dateString) throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
        System.out.printf("Books released before %s: %n", dateString);
        this.bookService.findAllByDateTimeBefore(date).forEach(x -> {
            System.out.printf("%s - %s --> %.2f%n", x.getTitle(), x.getEditionType(), x.getPrice());
        });
    }

    private void findAuthorsEndingWith(String ending){
        System.out.printf("Authors with names ending with %s: %n", ending);
        this.authorService.findAllByFirstNameEndsWith(ending).forEach(x -> System.out.println(x.getFirstName() + " " + x.getLastName()));
    }

    private void findBooksContaining(String str){
        System.out.printf("Books with titles containing %s: %n", str);
        this.bookService.findAllByTitleContainsIgnoreCase(str).forEach(x -> System.out.println(x.getTitle()));
    }

    private void booksWhoseAuthorsStartWith(String beginning){
        System.out.printf("Books, whose authors' last name starts with %s : %n", beginning);
        this.bookService.findAllByAuthorLastNameStartsWithOrderByAuthorLastName(beginning).forEach(x -> System.out.printf("%s (%s %s)%n", x.getTitle(), x.getAuthor().getFirstName(), x.getAuthor().getLastName()));
    }

    private void findBooksWithTitleLongerThan(int length){
        System.out.printf("Books with title longer than %d: %n", length);
        System.out.println(this.bookService.countAllByTitleLongerThan(length));
    }

    private void orderAuthorsBySumOfBookCopies() {
        System.out.println("Authors ordered by sum of all books copies: ");
        this.authorService.findAllByBooksCopiesOrderByCopiesDesc().forEach(x -> System.out.printf("%s - %s%n", x[0], x[1]));
    }

    private void releasedBooks(String title){
        System.out.printf("Book with title = %s: %n", title);
        System.out.println(this.bookService.findBookByTitleEquals(title).toString());
    }

    private void increaseCopiesOfBooksAfter(String dateString, Integer number) throws ParseException {
        Date date = new SimpleDateFormat("dd MMM yyyy", Locale.UK).parse(dateString);
        int affectedBooks = this.bookService.countAllByDateTimeAfter(date);
        this.bookService.updateBooksIncreaseCopies(number, date);
        System.out.println(affectedBooks * number);
    }

    private void deleteBooksWithCopiesLessThan(Integer number){
        int booksDeleted = this.bookService.deleteBooksByCopiesLessThan(number);
        System.out.println(booksDeleted + " books were deleted");
    }

    private void getTotalBooksOfAuthor(String fullName) {
        String firstName = fullName.split("\\s+")[0];
        String lastName = fullName.split("\\s+")[1];
        int totalBooks = this.bookService.getTotalBooksOfAuthor(firstName, lastName);
        if(totalBooks == 0){
            System.out.printf("%s %s has not written any books yet%n", firstName, lastName);
        } else if(totalBooks == 1){
            System.out.printf("%s %s has written %d book%n", firstName, lastName, totalBooks);
        } else {
            System.out.printf("%s %s has written %d books%n", firstName, lastName, totalBooks);
        }
    }
}
