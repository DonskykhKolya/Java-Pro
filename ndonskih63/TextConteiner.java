package com.gmail.ndonskih63;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "testFile.txt")
public class TextConteiner {
    private String str = "testing string for write in file";

    @Saver
    public void methodIsWriter(String title){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(title))) {
            bw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
