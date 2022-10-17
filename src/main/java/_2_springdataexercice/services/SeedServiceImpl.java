package _2_springdataexercice.services;

import _2_springdataexercice.entities.Category;
import _2_springdataexercice.repositories.AuthorRepository;
import _2_springdataexercice.entities.Author;
import _2_springdataexercice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SeedServiceImpl implements SeedService {


    private static final String RESOURCE_PATH = "src/main/resources/files";
    private static final String AUTHORS_FILE_NAME = RESOURCE_PATH + "/authors.txt";
    private static final String CATEGORIES_FILE_NAME = RESOURCE_PATH + "/categories.txt";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;
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
    public void seedBooks() {

    }


}