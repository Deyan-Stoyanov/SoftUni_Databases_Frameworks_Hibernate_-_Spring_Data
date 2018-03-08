package intersection.circles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] c1 = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] c2 = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int x1 = c1[0];
        int y1 = c1[1];
        int r1 = c1[2];
        Circle firstCircle = new Circle(new Point(x1, y1), r1);
        int x2 = c2[0];
        int y2 = c2[1];
        int r2 = c2[2];
        Circle secondCircle = new Circle(new Point(x2, y2), r2);
        boolean intersect = false;
        double d = Math.sqrt((Math.pow((secondCircle.getCenter().getX() - firstCircle.getCenter().getX()), 2))  + (Math.pow((secondCircle.getCenter().getY() - firstCircle.getCenter().getY()), 2)));
        if(d <= (firstCircle.getRadius() + secondCircle.getRadius())){
            intersect = true;
        }
        if(intersect){
            System.out.println("Yes");
        } else{
            System.out.println("No");
        }
    }
}
