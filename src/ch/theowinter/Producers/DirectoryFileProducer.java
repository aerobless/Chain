package ch.theowinter.producers;

import ch.theowinter.Chain;
import ch.theowinter.ChainEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DirectoryFileProducer implements Chain {
    String path;
    String filetype;

    public DirectoryFileProducer(String path, String filetype) {
        this.path = path;
        this.filetype = filetype;
    }

    public List<ChainEvent> getFolderContent(String path) throws ParseException, IOException {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        List<ChainEvent> results = new ArrayList<ChainEvent>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(filetype)) {
                Path pPath = Paths.get(listOfFiles[i].getAbsolutePath());
                System.out.println(listOfFiles[i].getAbsolutePath());
                BasicFileAttributes attributes = Files.readAttributes(pPath, BasicFileAttributes.class);

                System.out.println(new Date(attributes.creationTime().toMillis()));
                results.add(new ChainEvent("File with Type "+filetype, listOfFiles[i].getAbsolutePath(), new Date(attributes.creationTime().toMillis())));
            }
        }
        return results;
    }

    public List<ChainEvent> compileChain(){
        try {
            return getFolderContent(path);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
