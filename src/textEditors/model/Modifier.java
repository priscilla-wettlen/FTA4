package textEditors.model;

import textEditors.controller.Controller;

import java.util.List;

public class Modifier implements Runnable{
    private SharedBuffer sharedBuffer;
    private List<String> lines;
    private int replaceIndex;

    public Modifier(SharedBuffer sharedBuffer, List<String> lines, int replaceIndex){
        this.sharedBuffer = sharedBuffer;
        this.lines = lines;
        this.replaceIndex = replaceIndex;
    }

    @Override
    public void run() {
        try {
            sharedBuffer.read(lines);
            if (replaceIndex >= 0 && replaceIndex < lines.size()) {
                lines.set(replaceIndex, sharedBuffer.getStorageArray()[replaceIndex]);
                System.out.println("Modified line at " + replaceIndex + ": " + lines.get(replaceIndex));
                System.out.println(lines + "this is coming from modifier" + "\n");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
/*
        while (!buffer.continuModify())
        {
            try {
                Thread.sleep(50);
                buffer.modifyData();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/
    }
}
