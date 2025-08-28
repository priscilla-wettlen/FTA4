package textEditors.model;

import textEditors.controller.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer implements Runnable{
    private final File file;
    private Controller controller;
    private SharedBuffer sharedBuffer;

    public Writer(File file, Controller controller) {
        this.file = file;
        this.controller = controller;
        this.sharedBuffer = new SharedBuffer(controller);
    }

    @Override
    public void run() {
        try{
            String str = "";
            sharedBuffer.write(str);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
