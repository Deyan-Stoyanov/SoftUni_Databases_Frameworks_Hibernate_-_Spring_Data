package mankind;

public class Human {
    private String firstName;
    private String lastName;

    public Human(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.substring(0, 1).equals(firstName.substring(0, 1).toLowerCase())){
            throw new IllegalArgumentException("Expected upper case letter!Argument: firstName");
        }
        if(firstName.length() < 4){
            throw new IllegalArgumentException("Expected length at least 4 symbols!Argument: firstName");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.substring(0, 1).equals(lastName.substring(0, 1).toLowerCase())){
            throw new IllegalArgumentException("Expected upper case letter!Argument: firstName");
        }
        if(lastName.length() < 3){
            throw new IllegalArgumentException("Expected length at least 4 symbols!Argument: firstName");
        }
        this.lastName = lastName;
    }
}
