package com.epam.serializers;

import com.epam.serializers.factory.JacksonWrapper;
import com.epam.serializers.pojo.Book;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JacksonWrapperTest {
    private JacksonWrapper jacksonWrapper = new JacksonWrapper();

    @Test
    public void testWriteObjectToJsonSuccess() throws IOException {
        Book testBook = new Book(1, "Test Book 1", "Test Author 1");
        jacksonWrapper.writeObjectToJson(new File(this.getClass().getResource("/actual/test_book_jackson.json").getPath()), testBook, false);

        String actual = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(this.getClass().getResource("/actual/test_book_jackson.json").getPath()))){
            while (reader.ready()){
                actual += reader.readLine();
            }
        }

        String expected = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(this.getClass().getResource("/expected/test_book.json").getPath()))){
            while (reader.ready()){
                expected += reader.readLine();
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testReadObjectFromJsonFileSuccess() throws IOException {
        Book expected = new Book(1, "Test Book 1", "Test Author 1");
        Book actual = jacksonWrapper.readObjectFromJsonFile(new File(this.getClass().getResource("/expected/test_book.json").getPath()), Book.class);
        assertEquals(expected, actual);
    }

    @Test
    public void testWriteListObjectsToJsonSuccess() throws IOException {
        Book testBook1 = new Book(1, "Test Book 1", "Test Author 1");
        Book testBook2 = new Book(2, "Test Book 2", "Test Author 2");
        List<Book> books = Arrays.asList(testBook1, testBook2);
        jacksonWrapper.writeObjectToJson(new File(this.getClass().getResource("/actual/test_list_book_jackson.json").getPath()), books, false);

        String actual = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(this.getClass().getResource("/actual/test_list_book_jackson.json").getPath()))){
            while (reader.ready()){
                actual += reader.readLine();
            }
        }

        String expected = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(this.getClass().getResource("/expected/list_test_books.json").getPath()))){
            while (reader.ready()){
                expected += reader.readLine();
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testReadListElementsFromJsonFile() throws IOException {
        Book expected1 = new Book(1, "Test Book 1", "Test Author 1");
        Book expected2 = new Book(2, "Test Book 2", "Test Author 2");
        List<Book> expectedBooks = Arrays.asList(expected1, expected2);
        List<Book> actualBooks = jacksonWrapper.readListElementsFromJsonFile(new File(this.getClass().getResource("/expected/list_test_books.json").getPath()), Book.class);
        assertEquals(expectedBooks, actualBooks);
    }
}
