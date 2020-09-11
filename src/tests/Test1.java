package tests;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Test1 {


    public static void main(String[] args) {
        String[] names = {"Sam", "Pamela", "Dave", "Pascal", "Erik"};
        AtomicInteger index = new AtomicInteger();
        /*List<String> list = Arrays.stream(names)
                .filter(n -> index.incrementAndGet()>1)
                .collect(Collectors.toList());*/

        String a = "PokerStars Zoom Hand #105825162774:  Hold'em No Limit ($0.01/$0.02) - 2013/10/21 10:55:43 ET\n" +
                "Table 'McNaught' 9-max Seat #1 is the button\n" +
                "Seat 1: Sikorek ($1.22 in chips) \n" +
                "Seat 2: DayOfVictory ($3.88 in chips) \n" +
                "Seat 3: numaoase ($2.72 in chips) \n" +
                "Seat 4: spravochnick ($2 in chips) \n" +
                "Seat 5: martitin47 ($4.80 in chips) \n" +
                "Seat 6: KoMiTeKrade ($2.01 in chips) \n" +
                "Seat 7: mkwii ($2 in chips) \n" +
                "Seat 8: matildius ($2.26 in chips) \n" +
                "Seat 9: kon14 ($0.83 in chips) \n" +
                "DayOfVictory: posts small blind $0.01\n" +
                "numaoase: posts big blind $0.02\n" +
                "*** HOLE CARDS ***\n" +
                "Dealt to DayOfVictory [Ac 8h]\n" +
                "spravochnick: folds \n" +
                "martitin47: folds \n" +
                "KoMiTeKrade: folds \n" +
                "mkwii: folds \n" +
                "matildius: folds \n" +
                "kon14: folds \n" +
                "Sikorek: calls $0.02\n" +
                "DayOfVictory: calls $0.01\n" +
                "numaoase: checks \n" +
                "*** FLOP *** [Js 9c Qh]\n" +
                "DayOfVictory: checks \n" +
                "numaoase: checks \n" +
                "Sikorek: checks \n" +
                "*** TURN *** [Js 9c Qh] [5c]\n" +
                "DayOfVictory: checks \n" +
                "numaoase: checks \n" +
                "Sikorek: bets $0.04\n" +
                "DayOfVictory: folds \n" +
                "numaoase: folds \n" +
                "Uncalled bet ($0.04) returned to Sikorek\n" +
                "Sikorek collected $0.06 from pot\n" +
                "Sikorek: doesn't show hand \n" +
                "*** SUMMARY ***\n" +
                "Total pot $0.06 | Rake $0 \n" +
                "Board [Js 9c Qh 5c]\n" +
                "Seat 1: Sikorek (button) collected ($0.06)\n" +
                "Seat 2: DayOfVictory (small blind) folded on the Turn\n" +
                "Seat 3: numaoase (big blind) folded on the Turn\n" +
                "Seat 4: spravochnick folded before Flop (didn't bet)\n" +
                "Seat 5: martitin47 folded before Flop (didn't bet)\n" +
                "Seat 6: KoMiTeKrade folded before Flop (didn't bet)\n" +
                "Seat 7: mkwii folded before Flop (didn't bet)\n" +
                "Seat 8: matildius folded before Flop (didn't bet)\n" +
                "Seat 9: kon14 folded before Flop (didn't bet)\n"+
                "\n"+
        "\n";
        //"\n";
        String test =
                "ccc\r\n"+
                "ttt\r\n"+
                        "PokerStars\r\n"+
                "aaaaaaa\r\n"+
                "PokerStars\r\n"+
                "\r\n"+
                 "ssssssssss\r\n";
        String[] leng = test.split("\r\n");
        //index.incrementAndGet()


       
        int t = index.get();
        //System.out.println(t);
        String tes = "A";
        //System.out.println(ObjectSizeFetcher.getObjectSize(tes));
        AtomicBoolean start = new AtomicBoolean(false);
        AtomicBoolean end = new AtomicBoolean(true);

        Stream<String> lines = test.lines().filter(n -> { if(n.startsWith("PokerStars")) start.set(true);return start.get();}).
                filter(n-> {if(n.length()==0) end.set(false);return end.get();});
        /*String[] arr = lines.toArray(String[]::new);
        for(String b:arr) System.out.println("*"+b+"*");*/
        System.out.println(test.lines().filter(n -> { if(n.startsWith("PokerStars")) start.set(true);return start.get();}).
                filter(n-> {if(n.length()==0) end.set(false);return end.get();}).collect(Collectors.joining("\r\n")));
      boolean start_str_pokerstarts = false;
      int index_str = 0;
      int add =0,ind_end_str =0;
      for(int i=0; i<5; i++){
          if(test.startsWith("PokerStars")){break;}
          index_str++;
          ind_end_str = test.indexOf("\r\n",add);
          if(test.startsWith("PokerStars",ind_end_str+2)){break;}
          add = ind_end_str+1;
      }
      int count_str = 0; add = 0;
      for(;;){

          ind_end_str = test.indexOf("\r\n",add);
          if(ind_end_str==-1||ind_end_str-add<2)break;
          count_str++;
          //if(a.startsWith("\n",ind_end_str+1)){break;}
          //if(ind_end_str-add==0)break;

          add = ind_end_str+2;
          System.out.println(ind_end_str+" "+add);
      }
        System.out.println("start "+index_str+" end "+count_str+" ind "+ind_end_str+" add "+add);
        for(int i=0; i<count_str-1; i++){
            System.out.println("*"+leng[i]+"*");
        }

        List<String> list = Arrays.asList("1","2","3","4","5");
        for(int i=0; i<list.size(); i+=2){
            int endl = i+2; if(endl>list.size())endl=list.size();

            for(String s:list.subList(i,5)) System.out.print(" "+s);
            System.out.println();
        }
     String te = " test       ";
        System.out.println("*"+te.trim()+"*");
        System.out.println("trim *"+te+"*");

        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(0);
        integerSet.add(1);
        System.out.println("bool "+integerSet.add(1));

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.remove(0);
        System.out.println("indexe lin "+linkedList.get(0));


        String[] arrt= {"1,","2,","3,","4,","5,"};
        String sum = "";
        String sum2 = null;
        List<String> list1 = new ArrayList<>();
        /*for(int i = 0; i<5; i++) {
            sum+=arrt[i];
            //for(int v=i+1; v<5; v++)sum+="0,";
            list1.add(sum);
            String sum1 = list1.get(list1.size()-1);
            for(int v=i+1; v<5; v++)sum1+="0,";
            list1.set(list1.size()-1,sum1);
        }*/

        for(int i = 0; i<5; i++) {
            sum+=arrt[i];
            sum2 = sum;
            for(int v=i+1; v<5; v++)sum2+="0,";
            list1.add(sum2+"1,");
            list1.add(sum2+"-1,");
        }

        for(String l:list1) System.out.println(l);


        String[][]testar = {{"1","01"},{"2","02"}};
        Map<Integer,String>integerStringMap = new LinkedHashMap<>();
        integerStringMap.put(1,"1");
        integerStringMap.put(2,"2");
        integerStringMap.put(3,"3");
        System.out.println("d "+(99%3));
        System.out.println("d "+((20%10)-1));

        BufferedImage allwindow2 = null;
        try {
            allwindow2 = ImageIO.read(new File(System.getProperty("user.dir")+"\\allwindow2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        long s = System.currentTimeMillis();
        BufferedImage withoutflop1 = allwindow2.getSubimage(318,671-469,17,17);
        System.out.println("time "+(System.currentTimeMillis()-s));
    }

}
