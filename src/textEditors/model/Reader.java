package textEditors.model;

import textEditors.controller.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader implements Runnable{
    private final File file;
    private Controller controller;

    public Reader(File file, Controller controller) {
        this.file = file;
        this.controller = controller;
    }

    @Override
    public void run() {
        List<String> lines = new ArrayList<>();
        if(file == null){
            System.out.println("No file selected");
            return;
        }
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line + "\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        controller.setSourceText(lines);
    }
}
