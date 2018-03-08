import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CountWorkingDays {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String startDate = reader.readLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateStartDate = formatter.parse(startDate);
        String endDate = reader.readLine();
        Date dateEndDate = formatter.parse(endDate);
        int countWorkingDays = 0;
        Calendar start = Calendar.getInstance();
        start.setTime(dateStartDate);
        Calendar end = Calendar.getInstance();
        end.setTime(dateEndDate);
        end.add(Calendar.DATE, 1);

        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            if(!isHoliday(date)){
                countWorkingDays++;
            }
        }
        System.out.println(countWorkingDays);
    }

    public static boolean isHoliday(Date date){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        if(c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return true;
        } else if(c1.get(Calendar.DAY_OF_MONTH) == 1 && c1.get(Calendar.MONTH) == Calendar.JANUARY){
            return true;
        } else if(c1.get(Calendar.DAY_OF_MONTH) == 3 && (c1.get(Calendar.MONTH) == Calendar.MARCH)){
            return true;
        } else if((c1.get(Calendar.DAY_OF_MONTH) == 1 || c1.get(Calendar.DAY_OF_MONTH) == 6 || c1.get(Calendar.DAY_OF_MONTH) == 24) && c1.get(Calendar.MONTH) == Calendar.MAY){
            return true;
        } else if((c1.get(Calendar.DAY_OF_MONTH) == 6 || c1.get(Calendar.DAY_OF_MONTH) == 22) && c1.get(Calendar.MONTH) == Calendar.SEPTEMBER){
            return true;
        } else if(c1.get(Calendar.DAY_OF_MONTH) == 1 && c1.get(Calendar.MONTH) == Calendar.NOVEMBER){
            return true;
        } else if((c1.get(Calendar.DAY_OF_MONTH) == 24 || c1.get(Calendar.DAY_OF_MONTH) == 25 || c1.get(Calendar.DAY_OF_MONTH) == 26) && c1.get(Calendar.MONTH) == Calendar.DECEMBER){
            return true;
        } else {
            return false;
        }
    }
}
