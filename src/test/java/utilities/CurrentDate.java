package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {
    public static void main(String[] args) {

        System.out.println(currentDay());

    }

    public static String currentDay(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        String today = formatter.format(date);
        return today;
    }
    // https://www.javatpoint.com/java-simpledateformat
}
