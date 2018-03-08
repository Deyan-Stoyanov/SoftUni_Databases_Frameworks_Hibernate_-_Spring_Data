package last_digit.name;

public class LastDigitName {
    private int value;
    private String englishName;

    public LastDigitName(int value){
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public void setEnglishName() {
        String englishName = "";
        int lastDigit = this.value % 10;
        switch (lastDigit){
            case 0:
                englishName = "zero";
                break;
            case 1:
                englishName = "one";
                break;
            case 2:
                englishName = "two";
                break;
            case 3:
                englishName = "three";
                break;
            case 4:
                englishName = "four";
                break;
            case 5:
                englishName = "five";
                break;
            case 6:
                englishName = "six";
                break;
            case 7:
                englishName = "seven";
                break;
            case 8:
                englishName = "eight";
                break;
            case 9:
                englishName = "nine";
                break;
        }
        this.englishName = englishName;
    }
    @Override
    public String toString() {
        return this.englishName;
    }
}