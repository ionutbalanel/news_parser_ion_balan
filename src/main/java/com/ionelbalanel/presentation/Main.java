package com.ionelbalanel.presentation;

import com.ionelbalanel.data.NewsDataSource;
import com.ionelbalanel.data.models.Article;
import com.ionelbalanel.domain.NewsParser;
import com.ionelbalanel.presentation.menu.Menu;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Menu menu = new Menu();
    private static Boolean isRunning = true;
    private static final NewsDataSource dataSource = new NewsDataSource("src/main/java/com/ionelbalanel/news_data.json");
    private static final NewsParser parser = new NewsParser(dataSource);

    public static void main(String[] args) {
        setUpMenu();

        menu.printMenu();
        while (isRunning) {
            System.out.print("\nChose your option: ");
            menu.onMenuItemSelected(new Scanner(System.in).nextInt());
        }
    }

    private static void setUpMenu() {
        menu.addMenuItem("Print Articles Number", () -> System.out.println("Articles Number: " + Main.parser.getNumberOfArticles()));
        menu.addMenuItem("Print all Articles", Main.parser::printAllArticles);
        menu.addMenuItem("Display Article Title by ID", () -> {
            System.out.print("Input ID the index of the article for which to print the title: ");
            Main.parser.printArticleTitleById(new Scanner(System.in).nextInt());
        });
        menu.addMenuItem("Display Article Description by ID", () -> {
            System.out.print("Input ID the index of the article for which to print the description: ");
            Main.parser.printArticleDescriptionById(new Scanner(System.in).nextInt());

        });
        menu.addMenuItem("Display Article Full Description by ID", () -> {
            System.out.print("Input ID the index of the article for which to print the full description: ");
            Main.parser.printArticleFullDescriptionById(new Scanner(System.in).nextInt());
        });
        menu.addMenuItem("Search Article by Keywords", () -> {
            System.out.print("Enter search query: ");
            String searchQuery = new Scanner(System.in).nextLine();
            List<Article> searchResults = Main.parser.searchByKeywords(searchQuery);
            if (searchResults.isEmpty()) {
                System.out.println("No keywords found.");
            } else {
                for (Article article : searchResults) {
                    System.out.println(article.keywords);
                }
            }
        });
        menu.addMenuItem("Search Article by Title", () -> {
            System.out.print("Enter search query: ");
            String searchQuery = new Scanner(System.in).nextLine();
            List<Article> searchResults = Main.parser.searchByTitle(searchQuery);
            if (searchResults.isEmpty()) {
                System.out.println("No title found.");
            } else {
                for (Article article : searchResults) {
                    System.out.println(article.title);
                }
            }
        });

        menu.addMenuItem("Search Article by Description", () -> {
            System.out.print("Enter search query: ");
            String searchQuery = new Scanner(System.in).nextLine();
            List<Article> searchResults = Main.parser.searchByDescription(searchQuery);
            if (searchResults.isEmpty()) {
                System.out.println("No description found.");
            } else {
                for (Article article : searchResults) {
                    System.out.println(article.description);
                }
            }
        });

        menu.addMenuItem("Words Count", () -> {
            System.out.print("Input number of article or leave empty for all of them: ");
            String articleId = new Scanner(System.in).nextLine();
            if (articleId.isBlank()) {
                Integer totalWordsCount = Main.parser.getTotalWordsCount();
                System.out.println("Total words count: " + totalWordsCount);
            } else {
                Integer wordsCount = Main.parser.getWordsCountForArticle(Integer.parseInt(articleId));
                System.out.println("Words count for article with id " + articleId + ": " + wordsCount);
            }
        });
        menu.addMenuItem("Words Occurrences", () -> {
            // TODO
        });
        menu.addMenuItem("Print Menu", menu::printMenu);
        menu.addMenuItem("Exit", () -> isRunning = false);
    }
}
