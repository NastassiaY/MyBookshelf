package service;

import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public static List<String> fileAsList(Resource resource) {
        List<String> fileAsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile())))
        {
            String s;
            while((s=br.readLine())!=null){
                fileAsList.add(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileAsList;
    }
}
