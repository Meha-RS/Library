import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BookTest {
    @Test
    void issueMakesBookIssued() {
        Book book = new Book("Me", "Java");
        book.issueBook();
        assertTrue(book.isIssued());
    }

    @Test
    void returnMakesBookFree(){
        Book book = new Book("Me", "Java", true);
        book.returnBook();
        assertFalse(book.isIssued());
    }

    @Test
    void secondIssueThrows() {
        Book book = new Book("Me", "Java");
        book.issueBook();
        assertThrows(BookIsIssuedException.class, () -> book.issueBook());
    }

    @Test
    void secondReturnIssueThrows(){
        Book book = new Book("Me", "Java", true);
        book.returnBook();
        assertThrows(BookIsIssuedException.class, () -> book.returnBook());
    }


}
