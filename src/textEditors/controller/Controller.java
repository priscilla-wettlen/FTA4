package textEditors.controller;

import textEditors.model.*;
import textEditors.view.MainFrame;
import textEditors.view.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller{
    private List<String> sourceStrings;
    private File selectedFile;
    private MainFrame view;
    private SharedBuffer sharedBuffer;
    private int index = 0;


    public Controller() {
        view = new MainFrame(this);
        this.sharedBuffer = new SharedBuffer(this);
    }

    public void execute( List<String> lines, String find, String replace) throws InterruptedException {
        Writer writer = new Writer(selectedFile, this, sharedBuffer);
        new Thread(writer).start();
        Modifier modifier = new Modifier(sharedBuffer, lines, index);
        new Thread(modifier).start();
        view.markWord(find);//create a buffer
        sharedBuffer.write(replace);
        System.out.println("replaced with word "+replace);
        sharedBuffer.read(lines);
        //System.out.println(lines);

     }


    public void setSourceText(List<String> sourceStrings)
    {
        view.setSourceText(sourceStrings);
    }

    public void loadFile() {
        FileManager fileManager = new FileManager();
        selectedFile = fileManager.onLoadFile();
        Reader reader = new Reader(selectedFile, this);
        new Thread(reader).start();
    }




}
