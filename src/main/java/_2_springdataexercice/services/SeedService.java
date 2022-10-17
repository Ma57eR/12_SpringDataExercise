package _2_springdataexercice.services;

import java.io.IOException;

public interface SeedService {

    void seedAuthors() throws IOException;

    void seedCategories() throws IOException;

    void seedBooks();

    default void seedAll() throws IOException {
        seedAuthors();
        seedBooks();
        seedCategories();
    };
}
