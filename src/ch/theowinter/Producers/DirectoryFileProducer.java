package ch.theowinter.producers;

import ch.theowinter.Chain;
import ch.theowinter.ChainEvent;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DirectoryFileProducer implements Chain {
    String path;
    String filetype;

    public DirectoryFileProducer(String path, String filetype) {
        this.path = path;
        this.filetype = filetype;
    }

    public List<ChainEvent> getFolderContent(String path) throws ParseException {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        List<ChainEvent> results = new ArrayList<ChainEvent>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(filetype)) {
                //System.out.println("File " + listOfFiles[i].getName());
                File currentFile = new File(listOfFiles[i].getAbsolutePath());
                //TODO: last modified is not what we want.
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                results.add(new ChainEvent("File with Type "+filetype, listOfFiles[i].getAbsolutePath(), sdf.parse(sdf.format(listOfFiles[i].lastModified()))));
            }
        }
        return results;
    }

    public List<ChainEvent> compileChain(){
        try {
            return getFolderContent(path);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
