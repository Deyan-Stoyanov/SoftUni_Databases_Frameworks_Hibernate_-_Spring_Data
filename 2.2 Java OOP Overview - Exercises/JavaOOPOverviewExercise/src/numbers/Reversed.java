package numbers;
public class Reversed {
    private double number;
    private String reverced;

    public Reversed(double number) {
        this.number = number;
    }

    public double getNumber() {
        return this.number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getReverced() {
        return this.reverced;
    }

    public void setReverced() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNumber());
        String a = sb.toString();
        boolean isInt = true;
        for (int i = a.indexOf('.') + 1; i < a.length(); i++) {
            if (a.charAt(i) != '0'){
                isInt = false;
                break;
            }
        }
        if(isInt){
            a = a.substring(0, a.indexOf('.'));
        }
        sb = new StringBuilder();
        sb.append(a);
        sb.reverse();
        this.reverced = sb.toString();
    }
}
