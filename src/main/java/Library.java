import java.util.ArrayList;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Library {
    private ArrayList<Book> library = new ArrayList<>();
    Path LIB = Path.of("LIBRARY.txt");
    public void addBook(Book book){
        library.add(book);
    }

    public Book findBook(String name){
        for (Book book : library)
            if(book.getName().equalsIgnoreCase(name)) {
                return book;
            }
        return null;
    }

    public Book getBook(String name) throws BookIsIssuedException{
        for (Book book : library)
            if(book.getName().equalsIgnoreCase(name) && !book.isIssued()) {
                book.issueBook();
                return book;
            }
        throw new BookIsIssuedException("Книга уже выдана!");
    }

    public void returnBook(String name) throws BookIsIssuedException{
        for (Book book : library)
            if(book.getName().equalsIgnoreCase(name) && book.isIssued()) {
                book.returnBook();
                return;
            }
        throw new BookIsIssuedException("Книга ещё свободна!");
    }

    public String toString(){
        StringBuilder returnLib = new StringBuilder();
        for(Book book : library)
            returnLib.append(library.indexOf(book) + 1 + ") " + book.getName() + "\n" + "Статус: " + (book.isIssued()? "Выдана\n\n" : "Свободна\n\n"));
        return "--- Библеотека---\n" +
                "Книги:\n" + returnLib;
    }

    public void save() throws IOException{
        StringBuilder toSave = new StringBuilder();
        for(Book book : library) {
            toSave.append(book.getAuthor() + "|" + book.getName() + "|" + book.isIssued() + "\n");
        }
        Files.writeString(LIB, toSave, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void load() throws IOException{
        String all = Files.readString(LIB);
        String[] lines = all.split("\n");
        for(String line: lines){
            if(line.isEmpty())
                continue;
            String[] parts = line.split("\\|");
            boolean result = Boolean.parseBoolean(parts[2]);
            library.add(new Book(parts[0], parts[1], result));
        }
    }
}
