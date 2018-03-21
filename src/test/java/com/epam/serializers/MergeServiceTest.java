package com.epam.serializers;

import com.epam.serializers.pojo.Book;
import com.epam.serializers.service.MergeService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MergeServiceTest {
    private MergeService mergeService = new MergeService();

    @Test
    public void testMergeTwoBooks() throws IllegalAccessException {
        Book book = new Book(1, "The Name Of The Wind", null);
        Book book2 = new Book(2, "Gamlet", "William Shakespeare");
        Book expectedResult = new Book(1, "The Name Of The Wind", "William Shakespeare");
        Book actualResult = mergeService.mergeElements(book, book2);

        assertEquals(expectedResult, actualResult);
    }
}
