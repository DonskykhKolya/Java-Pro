package com.gmail.ndonskih63.details;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PostToFile {
    private static final int NUMBER_OF_COLUMNS = 4;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");
    private final List<String> formattedData;
    private final ToPost posts = new ToPost();
    private final AddUsers users = new AddUsers();

    private static String read(String fileName, String splitter) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
                new File(fileName).getAbsoluteFile()))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s);
                stringBuilder.append(splitter);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    public PostToFile() {
        formattedData = new Formatter("csv", read("posts.csv", ",")).getInfo();
        addAllPosts();
    }

    private void removeTitles() {
        for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
            formattedData.remove(0);
        }
    }

    private void addAllPosts() {
        for (int i = 0; i < formattedData.size() / NUMBER_OF_COLUMNS; i++) {
            posts.addPost(new ThisPost(formattedData.get(i * NUMBER_OF_COLUMNS),
                                formattedData.get(1 + i * NUMBER_OF_COLUMNS),
                    users.getUser(formattedData.get(3 + i * NUMBER_OF_COLUMNS)),
                    LocalDate.parse(formattedData.get(2 + i * NUMBER_OF_COLUMNS), dtf)));
        }
    }

    public ToPost getPost() {
        return posts;
    }

    public AddUsers getUsers() {
        return users;
    }
}
