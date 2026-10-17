import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
public class LibraryTest {
    private static final Path LIB = Path.of("LIBRARY.txt");

    @Test
    void findBookWithoutReg(){
        Library lib = new Library();
        lib.addBook(new Book("Me", "Java"));

        Book foundBook = lib.findBook("java");

        assertNotNull(foundBook, "Книга должна быть найдена при поиске без учёта регистра");
        assertEquals("Java", foundBook.getName());

    }

    @Test
    void doubleGetting(){
        Library lib = new Library();
        lib.addBook(new Book("Me", "Java"));
        lib.getBook("Java");
        assertThrows(BookIsIssuedException.class, () -> lib.getBook("Java"));
    }

    @Test
    void returnFreeBook(){
        Library lib = new Library();
        lib.addBook(new Book("Me", "Java"));
        assertThrows(BookIsIssuedException.class, () -> lib.returnBook("Java"));
    }

    @Test
    void findBookWithoutBook(){
        Library lib = new Library();
        assertNull(lib.findBook("Java"));
    }

    @Test
    void trueWithGetBook(){
        Library lib = new Library();
        lib.addBook(new Book("Me", "Java"));
        lib.getBook("Java");
        assertTrue(lib.findBook("Java").isIssued());
    }

    @Test
    void haveTitleOnToString1(){
        Library lib = new Library();
        lib.addBook(new Book("Me", "Test1"));
        String out = lib.toString();
        assertTrue(out.contains("Test1"));
        assertFalse(out.contains("Java"));
    }

    @Test
    void haveTitleOnToString2(){
        Library lib = new Library();
        lib.addBook(new Book("Me", "Test1"));
        String out = lib.toString();
        assertFalse(out.contains("Java"));
    }

    @Test
    void haveStatusOnString1(){
        Library lib = new Library();
        lib.addBook(new Book("Me", "Test"));
        String out = lib.toString();
        assertTrue(out.contains("Свободна"));
    }

    @Test
    void haveStatusOnString2(){
        Library lib = new Library();
        lib.addBook(new Book("Me", "Test"));
        lib.getBook("Test");
        String out = lib.toString();
        assertTrue(out.contains("Выдана"));
    }

    @Test
    void saveToFile(){
        Library lib1 = new Library();
        lib1.addBook(new Book("Me", "Test"));
        try {
            lib1.save();
            assertTrue(Files.exists(LIB));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void loadingFromFile(){
        Library lib1 = new Library();
        Library lib2 = new Library();
        lib1.addBook(new Book("Me", "Test"));
        try{
            lib1.save();
            lib2.load();
            assertNotNull(lib2.findBook("Test"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
