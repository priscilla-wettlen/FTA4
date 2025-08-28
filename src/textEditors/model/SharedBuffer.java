package textEditors.model;

import textEditors.controller.Controller;

import java.util.List;

public class SharedBuffer {
    private final int maxBufferSize = 20;
    private final String[] storageArray = new String[maxBufferSize];
    private String text;
    private int readerIndex;
    private int writerIndex;
    private int modifierIndex;
    private final BufferStatus[] statuses = new BufferStatus[maxBufferSize];
    private Controller controller;

    public SharedBuffer(Controller controller){
        this.controller = controller;
        for (int i = 0; i < maxBufferSize; i++) {
            statuses[i] = BufferStatus.Empty;
        }
    }

    public synchronized void write(String str) throws InterruptedException{
        while (statuses[writerIndex] != BufferStatus.Empty){
            wait();
        }
        if(str != null){
            storageArray[writerIndex] = str;
            //System.out.println(storageArray[writerIndex]);
            statuses[writerIndex] = BufferStatus.New;
            notifyAll();
        }
    }

    public synchronized void read(List<String> lines) throws InterruptedException{
        while(statuses[writerIndex] == BufferStatus.Empty){
            wait();
        }
        if(lines.get(writerIndex) != null){
            lines.set(readerIndex, storageArray[writerIndex]);
//            System.out.println(storageArray[writerIndex]);
//            System.out.println(storageArray[readerIndex]);
//            System.out.println(lines);
            statuses[writerIndex] = BufferStatus.Checked;
            notifyAll();
        }
    }

    public int getMaxBufferSize() {
        return maxBufferSize;
    }

    public String[] getStorageArray() {
        return storageArray;
    }



}
