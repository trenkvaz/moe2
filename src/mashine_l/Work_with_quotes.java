package mashine_l;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Work_with_quotes {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    static Calendar c = Calendar.getInstance();
    static String[] prices_sunday = null;
    static List<String> stringList_days = new ArrayList<>();
    static List<String> dates = new ArrayList<>();
    static List<String> five_min_from_1h_to_20h_list = new ArrayList<>();

    static void set_day_week(String folder_history){
        //stringList_days.add("date,day_week,open,high,low,close");
        five_min_from_1h_to_20h_list.add("date,time,open,high,low,close");
        for (File myFile : Objects.requireNonNull(new File(folder_history).listFiles())){
            if (myFile.isFile()) {
                if (myFile.getName().endsWith(".txt")) {
                    //read_file(myFile);
                    read_files_2(myFile);
                }
            } }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            String home = System.getProperty("user.dir");
            Files.write(Paths.get(home+"\\five_min_from_1h_to_20h_19_20.txt"), five_min_from_1h_to_20h_list, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void read_file(File file){
        try {
            BufferedReader   br = new BufferedReader(new FileReader(file));
            int day_week = -1; String line, data = null, line_new = null;
            float high_sunday = 0, low_sunday = 0, high_monday = 0, low_monday = 0;
            String[] prices_monday = null;
            while ((line = br.readLine())!=null){
                 if(!line.startsWith("BZ"))continue;
                 data = line.substring(5,13);
                 day_week = get_day_week(data);
                //System.out.println("*"+data+"*");
                if(day_week!=0&&day_week!=1){
                  line_new = data+","+day_week+","+line.substring(20,line.lastIndexOf(","));
                  stringList_days.add(line_new);
                }else {
                    if(day_week==0){ prices_sunday =  line.substring(20,line.lastIndexOf(",")).split(","); }
                    else {
                        prices_monday = line.substring(20,line.lastIndexOf(",")).split(",");
                        assert prices_sunday != null;
                        line_new = data+","+day_week+","+prices_sunday[0]+",";
                        high_sunday = Float.parseFloat(prices_sunday[1]);
                        low_sunday = Float.parseFloat(prices_sunday[2]);
                        high_monday = Float.parseFloat(prices_monday[1]);
                        low_monday = Float.parseFloat(prices_monday[2]);
                        if(high_monday>high_sunday)line_new+=prices_monday[1];else line_new+=prices_sunday[1];
                        line_new+=",";
                        if(low_monday<low_sunday)line_new+=prices_monday[2];else line_new+=prices_sunday[2];
                        line_new+=","+prices_monday[3];
                        stringList_days.add(line_new);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String d:stringList_days) System.out.println(d);
    }
    
    static int get_day_week(String day){
        try {
            Date yourDate = sdf.parse(day);
            c.setTime(yourDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.get(Calendar.DAY_OF_WEEK)-1;
    }

    static void check_one_hour(File file){

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line, date = null;
            while ((line=bufferedReader.readLine())!=null){
                if(line.startsWith("date"))continue;
                date = line.substring(0,8);
                dates.add(date);
                //System.out.println("*"+date+"*");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    static int index = -1;
    static void read_files_2(File file){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line, date = null; boolean open_date = false, is_found = false;
            String patern_date = null;
            while ((line=bufferedReader.readLine())!=null){
                if(line.contains(",01:00,")){ open_date = true; }
                if(line.contains(",20:05,")){ open_date = false; }
                if(open_date){
                    //int hour = Integer.parseInt(line.substring(9,11));
                    //System.out.println(line.substring(14,16));
                    //if(hour>20)System.out.println(hour);
                    five_min_from_1h_to_20h_list.add(line.substring(5,line.lastIndexOf(",")));
                }

                  //five_min_from_1h_to_20h_list.add(line.substring(5,line.lastIndexOf(",")));
                    //System.out.println(line.substring(5,line.lastIndexOf(",")));


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String folder = "C:\\Users\\Duduka\\jupyter\\finam_quotes\\19_20_five_min";
        //19_20_days
        // 19_20_five_min
        String days = System.getProperty("user.dir")+"\\demo.txt";
        //check_one_hour(new File(folder));
        set_day_week(folder);
    }

}
