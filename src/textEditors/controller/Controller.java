package textEditors.controller;

import textEditors.model.*;
import textEditors.view.MainFrame;
import textEditors.view.MainPanel;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller
{
    private List<String> sourceStrings;
    private File selectedFile;
    private MainFrame view;

    public Controller() {
        view = new MainFrame(this);
    }


    public void execute( String[] lines, String find, String replace) {

        view.markWord(find);//create a buffer

     }


    public void setSourceText(List<String> lines)
    {
        view.setSourceText(lines);
    }

    public void loadFile() {
        FileManager fileManager = new FileManager();
        selectedFile = fileManager.onLoadFile();
        Reader reader = new Reader(selectedFile, this);
        new Thread(reader).start();
    }

//    public void startReader(){
//        Reader reader = new Reader(selectedFile);
//        new Thread(reader).start();
//    }



}
