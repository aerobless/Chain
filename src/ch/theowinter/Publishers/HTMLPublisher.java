package ch.theowinter.publishers;

import ch.theowinter.ChainEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class HTMLPublisher {
    public List<ChainEvent> eventList;

    public HTMLPublisher(List<ChainEvent> eventList) {
        Collections.sort(eventList);
        this.eventList = eventList;
    }

    public void publish(){
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append(readFile("src/ch/theowinter/html/index.html", StandardCharsets.UTF_8));

            for(ChainEvent chainEvent : eventList){
                String event = chainEvent.occurance + " " + chainEvent.name;
                event = event.replace("'", "");
                buffer.append("    listView.append('<li class=\"list-group-item\">" + event + "</li>');\n");
            }
            buffer.append(
                    "</script>\n" +
                            "</body>\n" +
                            "</html>");

            writeToFile("out/chainIndex.html", buffer);

        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static void writeToFile(String pFilename, StringBuffer pData) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(pFilename));
        out.write(pData.toString());
        out.flush();
        out.close();
    }
}
