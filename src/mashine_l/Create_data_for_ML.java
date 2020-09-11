package mashine_l;

import java.io.*;
import java.util.*;

public class Create_data_for_ML {

  static void create(){
      LinkedList<String> _29_befor_days = new LinkedList<>();
      List<String> general_data = new ArrayList<>();
      List<String> days_day_of_week_list = new ArrayList<>();
      Map<String,String> map_DAY_day_of_week = new HashMap<>();
      Map<String,String> map_DAY_befor_5_min = new HashMap<>();
      Map<String,String> map_DAY_5_min_from_1_10_h_in_day = new HashMap<>();
      Map<String,String[][]> map_DAY_depart_5_min_from_10_to_20_h_in_day = new HashMap<>();
      List<String[]> count_5min_each_day = new ArrayList<>();
      Map<String,List<String[]>> map_DAY_listticks_of_time_price = new HashMap<>();
      String one_data = null;
      try {
          BufferedReader Days = new BufferedReader(new FileReader(new File("C:\\Users\\Duduka\\jupyter\\finam_quotes\\days_5min_ticks_19_20" +"\\days_by_week_19_20.txt")));
          BufferedReader _5_Minutes = new BufferedReader(new FileReader(new File("C:\\Users\\Duduka\\jupyter\\finam_quotes\\19_20_five_min" +"\\all_5_minutes_19_20.txt")));
          BufferedReader Ticks = new BufferedReader(new FileReader(new File("C:\\Users\\Duduka\\jupyter\\finam_quotes\\days_5min_ticks_19_20" +"\\all_ticks_19_20.txt")));
          String days_line; int fill_29 = 0;
          while ((days_line = Days.readLine())!=null){
              if(!days_line.startsWith("20"))continue;
              fill_29++;
              if(fill_29<30){
                  // получение первых 29 дней без удаление первого дня и добавление последнего
                _29_befor_days.add(days_line.substring(11)+",");
                if(fill_29==29) {

                    one_data = get_day_quotes_for_one_data(_29_befor_days);
                // заполнение карты дата и день недели и списка дата идущих подряд сопутствующий алгоритм который не влияет на получение 29 дней
                  map_DAY_day_of_week.put(days_line.substring(0,8),days_line.substring(9,10));
                  days_day_of_week_list.add(days_line.substring(0,8));
                }
              }
              if(fill_29>29) {
                  // получение последующих отрезков по 29 дней с удалением первого дня и добавлением последнего
                  map_DAY_day_of_week.put(days_line.substring(0,8),days_line.substring(9,10));
                  days_day_of_week_list.add(days_line.substring(0,8));
                  _29_befor_days.remove(0);
                  _29_befor_days.add(days_line.substring(11)+",");
                 // System.out.println(_29_befor_days.size());
                  one_data = get_day_quotes_for_one_data(_29_befor_days);
              }
              if(fill_29>28){
                  // внесение полученных отрезков в 29 дней независимо это первые 29 или последующие
                  general_data.add(one_data); }
          }
          String day_5min = null; boolean open_date = false; String _5_minutes_in_day = ""; int count_line = 0;
          String befor_line = null;
          while ((days_line = _5_Minutes.readLine())!=null){if(!days_line.startsWith("BZ"))continue;

              if(days_line.contains(",01:00,")){
                  if(count_line>1)count_5min_each_day.add(new String[]{Integer.toString(count_line),day_5min});
                  if(count_line>243&&count_line<262){
                    //System.out.println("short days "+day_5min+" count "+count_line);
                      for(; count_line<263; count_line++)_5_minutes_in_day+=befor_line;
                  }


                  if(count_line==262){  map_DAY_befor_5_min.put(day_5min,_5_minutes_in_day); }

                  count_line = 1;
                  day_5min = days_line.substring(5,13);
                  _5_minutes_in_day = days_line.substring(20,days_line.lastIndexOf(",")+1);

                 // if(day_5min.equals("20190210")) {System.out.println("_5_minutes_in_day"); return;}
              } else  { //if(count_line==262)continue;
              count_line++;
                  befor_line = days_line.substring(20,days_line.lastIndexOf(",")+1);
                  _5_minutes_in_day+=befor_line;
              }

          }
         // System.out.println(" 190210 "+map_DAY_befor_5_min.get("20190208"));
          for(String[] a:count_5min_each_day) System.out.println(a[1]+"  "+a[0]);

          _5_Minutes = new BufferedReader(new FileReader(new File("C:\\Users\\Duduka\\jupyter\\finam_quotes\\days_5min_ticks_19_20" +"\\five_min_from_1h_to_20h_19_20.txt")));
          int count_5min = 0; List<String> eror1 = new ArrayList<>();
          while ((days_line = _5_Minutes.readLine())!=null){if(!days_line.startsWith("20"))continue;
              if(map_DAY_befor_5_min.get(days_line.substring(0,8))==null)continue;
              if(days_line.contains(",01:00,")){ open_date = true; count_5min = 0; day_5min = days_line.substring(0,8); _5_minutes_in_day = ""; }
              if(days_line.contains(",10:05,")){ open_date = false; if(count_5min==109)map_DAY_5_min_from_1_10_h_in_day.put(day_5min,_5_minutes_in_day);
                  //System.out.println(count_5min);
                             }
              if(open_date){ count_5min++; _5_minutes_in_day+=days_line.substring(15)+","; }
          }
          /*open_date = false;
          _5_Minutes = new BufferedReader(new FileReader(new File("C:\\Users\\Duduka\\jupyter\\finam_quotes\\days_5min_ticks_19_20" +"\\five_min_from_1h_to_20h_19_20.txt")));
          List<String> eror1 = new ArrayList<>(); List<String> eror2 = new ArrayList<>(); int er=-1;
          while ((days_line = _5_Minutes.readLine())!=null){if(!days_line.startsWith("20"))continue;
              if(map_DAY_befor_5_min.get(days_line.substring(0,8))==null)continue;
              if(!(days_line.substring(0,8).equals("20190503")||days_line.substring(0,8).equals("20190506")))continue;
              //System.out.println(days_line.substring(0,8));
              if(days_line.contains(",01:00,")){ open_date = true; day_5min = days_line.substring(0,8); _5_minutes_in_day = ""; }
              if(days_line.contains(",10:05,")){ open_date = false;  map_DAY_5_min_from_1_10_h_in_day.put(day_5min,_5_minutes_in_day); }
              if(open_date){  if(days_line.startsWith("20190503"))eror1.add(days_line.substring(9,14));
                  if(days_line.startsWith("20190506")) { er++; eror2.add(eror1.get(er)+" "+days_line.substring(9,14));}
              }
          }
          for(String a:eror2) System.out.println(a);*/



          open_date = false;
          _5_Minutes = new BufferedReader(new FileReader(new File("C:\\Users\\Duduka\\jupyter\\finam_quotes\\days_5min_ticks_19_20" +"\\five_min_from_1h_to_20h_19_20.txt")));
          count_line = 117;
          String befor_time_5min = null;
          String [][] one_day_depart_5min = null;
          while ((days_line = _5_Minutes.readLine())!=null){if(!days_line.startsWith("20"))continue;
              if(days_line.contains(",10:05,")){ open_date = true;
              if(count_line<117) { for(; count_line<118; count_line++) { one_day_depart_5min[count_line][0]=befor_line; one_day_depart_5min[count_line][1]=befor_time_5min;}
                  map_DAY_depart_5_min_from_10_to_20_h_in_day.put(day_5min,one_day_depart_5min);
              }

              one_day_depart_5min = new String[118][2]; day_5min = days_line.substring(0,8);
              count_line = -1;}

              if(days_line.startsWith(day_5min)&&open_date){
                  count_line++;
                  befor_line = days_line.substring(15)+",";
                  befor_time_5min = days_line.substring(9,14);
                  one_day_depart_5min[count_line][0]=befor_line;
                  one_day_depart_5min[count_line][1]=befor_time_5min;
                  if(count_line==117){map_DAY_depart_5_min_from_10_to_20_h_in_day.put(day_5min,one_day_depart_5min);open_date = false; }

              }
              /*if(days_line.contains(",19:50,")){ open_date = false; count_line++; one_day_depart_5min[count_line]=days_line.substring(15)+",";
                  map_DAY_depart_5_min_from_10_to_20_h_in_day.put(day_5min,one_day_depart_5min);
                  *//*System.out.println(day_5min);
                  for(String a:map_DAY_depart_5_min_from_10_to_20_h_in_day.get(day_5min))
                      System.out.println(a);
              break;*//*
              }
              if(open_date){
                  if(days_line.contains(",01:00,")){
                      open_date = false;
                      if(count_line>1&&count_line<117){
                          for(; count_line<118; count_line++) one_day_depart_5min[count_line]=befor_line;
                      }

                      map_DAY_depart_5_min_from_10_to_20_h_in_day.put(day_5min,one_day_depart_5min);
                      continue;
                  }

                  count_line++;
                  befor_line = days_line.substring(15)+",";
                  one_day_depart_5min[count_line]=befor_line;
              }*/




          }
          //System.out.println("count "+count_line);
          /*for(Map.Entry entry:map_DAY_day_of_week.entrySet())
              System.out.println(entry.getKey()+"****"+entry.getValue()+"***");*/
          String day_befor_after_29_days = null;
          String today_1_to_10_h = null;
          String data_befor_day = null;
          String data_today_1_to10_h = null;
          String[][] depart_5min_in_day = null;
          List<String> increase_general_data = new ArrayList<>();
          String one_data_2 = null;
          List<String[]> mirror = new ArrayList<>();
          String[] one_data_mirror = null;
         for(int i=0; i<339; i++){
             one_data = general_data.get(i);
             //System.out.println("*"+one_data+"****");
             day_befor_after_29_days = days_day_of_week_list.get(i+1).substring(0,8);
             today_1_to_10_h = days_day_of_week_list.get(i+2).substring(0,8);

             data_befor_day = map_DAY_befor_5_min.get(day_befor_after_29_days);
             data_today_1_to10_h = map_DAY_5_min_from_1_10_h_in_day.get(today_1_to_10_h);

             if(data_befor_day==null||data_today_1_to10_h==null) {
                 System.out.println(today_1_to_10_h);
                 continue;}

             one_data+=data_befor_day+data_today_1_to10_h+map_DAY_day_of_week.get(today_1_to_10_h)+",";
            // System.out.println("        "+one_data.split(",").length);


             depart_5min_in_day = map_DAY_depart_5_min_from_10_to_20_h_in_day.get(today_1_to_10_h);
             //System.out.println(today_1_to_10_h);
             for(int a=0; a<118; a++){
                 one_data+=depart_5min_in_day[a][0];
                 one_data_2 = one_data;
                 for(int s=0; s<2; s++){
                 one_data_mirror = new String[13];
                 one_data_mirror[0] = today_1_to_10_h;
                 one_data_mirror[1] = depart_5min_in_day[a][1];
                 one_data_mirror[2] = depart_5min_in_day[a][0].split(",")[0];
                 if(s==0)one_data_mirror[3] = "1";if(s==1)one_data_mirror[3] = "-1";
                 int inv = 3;
                 for(int v=7; v<16; v++){ inv++;
                     one_data_mirror[inv] = Integer.toString(v);
                 }
                     /*for(int t=0; t<13; t++) System.out.print(" "+one_data_mirror[t]+" ");
                     System.out.println();*/
                 }

                 for(int d=a+1; d<118; d++)one_data_2+="0,0,0,0,";
                 //System.out.println(" ***** "+one_data_2.split(",").length);

                 increase_general_data.add(one_data_2+"1,");
                 increase_general_data.add(one_data_2+"-1,");
                 //if(a==5)break;
             }

             //general_data.set(i,one_data);
             //System.out.println("---"+general_data.get(i));
            // break;
         }

          List<String> increase_general_data_2 = new ArrayList<>();
          String stop = null;
          for(int i=0; i<increase_general_data.size(); i++){
              stop = increase_general_data.get(i);
              for(int a=7; a<16; a++){
                  increase_general_data_2.add(stop+""+i+",");
              }

          }
          //for(int i=0; i<increase_general_data_2.size(); i++){ //int r = increase_general_data.get(i).split(",").length;
              //if(r!=2074) {System.out.println("fffff");break;

          System.out.println(increase_general_data_2.get(0).split(",").length+"  "+increase_general_data_2.get(1111).split(",").length);
          //}
         
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }

  }

  static Map<String,List<String[]>> get_map_DAY_listticks_of_time_price() throws IOException {
      Map<String,List<String[]>> result = new LinkedHashMap<>();
      BufferedReader Ticks = new BufferedReader(new FileReader(new File("C:\\Users\\Duduka\\jupyter\\finam_quotes\\days_5min_ticks_19_20" +"\\all_ticks_19_20.txt")));
      String ticks_line, day = null, remain_day = "20190102"; boolean start_day = true;
      List<String[]> list_ticks = new ArrayList<>(); String[] ticks = null;
      while ((ticks_line = Ticks.readLine())!=null){ if(!ticks_line.startsWith("BZ"))continue;
          day = ticks_line.substring(4,12);
          if(!remain_day.equals(day)){   result.put(remain_day,list_ticks);  remain_day = day;   list_ticks = new ArrayList<>();
              list_ticks.add(new String[]{ticks_line.substring(13,18),ticks_line.substring(19)});
          }
          else {
            list_ticks.add(new String[]{ticks_line.substring(13,18),ticks_line.substring(19)});
              //System.out.println(ticks_line.substring(13,18)+"*"+ticks_line.substring(19));
          }
      }
      result.put(remain_day,list_ticks);

    return result;
  }
  /*static Float[] get_day_quote_from_line(String[] days_quotes){
         Float[] result = new Float[4];
         for(int i=0; i<4; i++){
             result[i] = Float.parseFloat(days_quotes[i]);
             //System.out.println("*"+days_quotes[i]+"*");
         }
         return result;
  }*/



  static String get_day_quotes_for_one_data(LinkedList<String> _29_befor_days){
      StringBuilder result = new StringBuilder();
      for(String day_quotes:_29_befor_days){
          result.append(day_quotes);
      }
      return result.toString();
  }

    public static void main(String[] args) throws IOException {
       // create();
        Map<String,List<String[]>> test =  get_map_DAY_listticks_of_time_price();
        for(Map.Entry<String,List<String[]>> entry:test.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue().size());
            //for(String[] a:entry.getValue()) System.out.println(a[0]+" "+a[1]);
        }
        System.out.println(test.size());
    }

}
