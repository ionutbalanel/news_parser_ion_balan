package com.ionelbalanel.data;

import com.google.gson.Gson;
import com.ionelbalanel.data.models.NewsData;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NewsDataSource {
    private static final Gson gson = new Gson();

    public NewsDataSource(String filePath) {
    }

    public NewsData readData() {
        try {
            String newsDataJsonString = Files.readString(Path.of("src/main/java/com/ionelbalanel/news_data.json"));
            return gson.fromJson(newsDataJsonString, NewsData.class);
        } catch (IOException e) {
            return null;
        }
    }
}
