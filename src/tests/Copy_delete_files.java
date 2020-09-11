package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Copy_delete_files {


    static void copy_delet(){
        String home = System.getProperty("user.dir");
        Path source = Paths.get(home + "\\in_file\\test.xml");
        Path target = Paths.get(home + "\\out_file\\test2.xml");
        String in_folder = home+"\\in_file";
        for (File myFile : Objects.requireNonNull(new File(in_folder).listFiles())){
            if (myFile.isFile()) {
                if (myFile.getName().endsWith(".xml")) {
                    try {
                        Files.move(myFile.toPath(), Paths.get(home + "\\out_file\\"+myFile.getName()), REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } }
    }

    static void read_files(File folder){
        for (File myFile : Objects.requireNonNull(folder.listFiles())){
            if (myFile.isFile()) {
                if (myFile.getName().endsWith(".txt")) {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(myFile));
                        String line;
                        while ((line = br.readLine()) != null) {
                            if(line.contains("TrenHN"))
                                System.out.println(myFile.getAbsolutePath());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(myFile.isDirectory()){read_files(myFile);}
        }
    }


    public static void main(String[] args) {

       read_files(new File("F:\\flash"));
    }
}
