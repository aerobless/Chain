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
                buffer.append("    listView.append('");
                buffer.append("<li class=\"list-group-item\">");
                buffer.append("<div class=\"row\">");
                    buffer.append("<div class=\"col-xs-1\">");
                        buffer.append("<i class=\""+selectTypeIcon(sanitize(chainEvent.type).toLowerCase())+" fa-5x\"></i>");
                    buffer.append("</div>");
                    buffer.append("<div class=\"col-xs-11\">");
                        buffer.append("<b>"+sanitize(chainEvent.name)+"</b><br>");
                        buffer.append("<b>Occurance: </b>"+sanitize(chainEvent.occurrence+"")+"<br>");
                        buffer.append("<b>Type: </b>"+sanitize(chainEvent.type)+"<br>");
                    buffer.append("</div>");
                buffer.append("</div>");
                buffer.append("</li>'");
                buffer.append(");\n");
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

    public void writeToFile(String pFilename, StringBuffer pData) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(pFilename));
        out.write(pData.toString());
        out.flush();
        out.close();
    }

    public String sanitize(String input){
        return input.replace("'", "");
    }

    public String selectTypeIcon(String type){
        String result = "fa fa-paper-plane";
        if(type.equals("birthday")){
            result = "fa fa-birthday-cake";
        } else if(type.equals("calendar")){
            result = "fa fa-calendar";
        } else if(type.equals("jpg") || type.equals("jpeg") || type.equals("png") || type.equals("cr2") || type.equals("dng")){
            result = "fa fa-camera";
        } else if(type.equals("txt") || type.equals("doc") || type.equals("pages") || type.equals("docx")){
            result = "fa fa-file-text";
        }
        return result;
    }
}
