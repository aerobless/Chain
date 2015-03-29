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
import java.text.SimpleDateFormat;
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

    private List<ChainEvent> getFolderContent() throws ParseException, IOException {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        List<ChainEvent> results = new ArrayList<ChainEvent>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(filetype)) {
                Date creationDate;

                if(filenameContainsDate(listOfFiles[i])){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    creationDate = sdf.parse(listOfFiles[i].getName().substring(0,10));
                } else {
                    Path pPath = Paths.get(listOfFiles[i].getAbsolutePath());
                    BasicFileAttributes attributes = Files.readAttributes(pPath, BasicFileAttributes.class);
                    creationDate = new Date(attributes.creationTime().toMillis());
                }
                results.add(new ChainEvent("File with Type "+filetype, listOfFiles[i].getAbsolutePath(), creationDate));
            }
        }
        return results;
    }

    private boolean filenameContainsDate(File listOfFile) {
        return listOfFile.getName().matches("^\\d{4}-\\d\\d-\\d\\d [\\s\\S]*");
    }

    /**
     * Generates a list of ChainEvents containing the filetype, path and creation date of files in a
     * specific folder. If the filename starts with a date of the following format: yyyy-mm-dd that date is used
     * as creation date. If the filename does not contain a date DirectoryFileProducer attempts to read the
     * creationDate from the file-meta-data. Be aware that not all filesystems store the file-creation date and so
     * this may not work for you. Tested on HFS, OS X.
     * @return
     */
    public List<ChainEvent> compileChain(){
        try {
            return getFolderContent();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
