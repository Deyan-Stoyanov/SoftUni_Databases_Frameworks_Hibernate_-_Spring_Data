package shopping.spree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> listOfPeople = new ArrayList<>();
        List<Product> listOfProducts = new ArrayList<>();
        String[] people = reader.readLine().split("[;=]");
        for (int i = 0; i < people.length; i+=2) {
            String name = people[i];
            if(name.isEmpty()){
                System.out.println("Name cannot be empty");
                continue;
            }
            double money = Double.parseDouble(people[i+1]);
            if(money < 0){
                System.out.println("Money cannot be negative");
                continue;
            }
            Person person = new Person(name, money);
            listOfPeople.add(person);
        }
        String[] products = reader.readLine().split("[;=]");
        for (int i = 0; i < products.length; i+=2) {
            String productName = products[i];
            double price = Double.parseDouble(products[i+1]);
            Product product = new Product(productName, price);
            listOfProducts.add(product);
        }
        while (true){
            boolean onlyProduct = false;
            if(listOfPeople.size() == 1 && listOfProducts.size() == 1){
                onlyProduct = true;
            }
            String[] command = reader.readLine().split(" ");
            if(command.length == 0 || command[0].toUpperCase().equals("END") || command.length == 1){
                break;
            }
            String personName = command[0];
            String productName = command[1];
            for (Person person:listOfPeople) {
                if(person.getName().equals(personName)){
                    for (Product product:listOfProducts) {
                        if(product.getName().equals(productName)){
                            if(product.getPrice() > person.getMoney()){
                                System.out.printf("%s can't afford %s%n", person.getName(), product.getName());
                                if(listOfPeople.size() == 1 && listOfProducts.size() == 1){
                                    onlyProduct = true;
                                    break;
                                }
                                break;
                            } else {
                                person.setMoney(person.getMoney() - product.getPrice());
                                person.getListOfProducts().add(product);
                                System.out.printf("%s bought %s%n", person.getName(), product.getName());
                            }
                        }

                    }
                    if(onlyProduct){
                        break;
                    }
                }
            }
            if(onlyProduct){
                break;
            }
        }
        for (Person p: listOfPeople) {
            System.out.printf("%s - ", p.getName());
            if(p.getListOfProducts().size() == 0){
                System.out.println("Nothing bought");
            } else {
                StringBuilder sb = new StringBuilder();
                p.getListOfProducts().forEach(x -> sb.append(x.getName()).append(", "));
                System.out.printf("%s%n",sb.toString().substring(0, sb.toString().length() - 2));
            }
        }
    }
}