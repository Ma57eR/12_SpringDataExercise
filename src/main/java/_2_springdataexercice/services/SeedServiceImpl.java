package _2_springdataexercice.services;

import _2_springdataexercice.entities.*;
import _2_springdataexercice.repositories.AuthorRepository;
import _2_springdataexercice.repositories.BookRepository;
import _2_springdataexercice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class SeedServiceImpl implements SeedService {


    private static final String RESOURCE_PATH = "src/main/resources/files";
    private static final String AUTHORS_FILE_NAME = RESOURCE_PATH + "/authors.txt";
    private static final String CATEGORIES_FILE_NAME = RESOURCE_PATH + "/categories.txt";
    private static final String BOOKS_FILE_NAME = RESOURCE_PATH + "/books.txt";


    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(AUTHORS_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(s -> s.split(" "))
                .map(names -> new Author(names[0], names[1]))
                .forEach(authorRepository::save);


    }

    @Override
    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(CATEGORIES_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(name -> new Category(name))
                .forEach(categoryRepository::save);
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOKS_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(this::getBookObject)
                .forEach(bookRepository::save);

    }

    private Book getBookObject(String line) {
        String[] bookParts = line.split(" ");

        int editionTypeIndex = Integer.parseInt(bookParts[0]);
        EditionType currentType = EditionType.values()[editionTypeIndex];

        LocalDate releaseDate = LocalDate.parse(bookParts[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

        int copies = Integer.parseInt(bookParts[2]);

        BigDecimal price = new BigDecimal(bookParts[3]);

        int ageRestrictionIndex = Integer.parseInt(bookParts[4]);
        AgeRestriction ageRestriction = AgeRestriction.values()[ageRestrictionIndex];

        return null;
    }


}
