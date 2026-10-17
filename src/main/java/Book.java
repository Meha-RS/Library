public class Book {
    private String author;
    private String name;
    private boolean issued = false;

    public Book(String author, String name){
        this.author = author;
        this.name = name;
    }

    public Book(String author, String name, boolean issued){
        this.author = author;
        this.name = name;
        this.issued = issued;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public String getName(){
        return name;
    }

    public boolean isIssued(){
        return issued;
    }

    public void issueBook() throws BookIsIssuedException{
        if(issued)
            throw new BookIsIssuedException("Книга уже выдана!");
        issued = true;
    }

    public void returnBook() throws BookIsIssuedException{
        if(!issued)
            throw new BookIsIssuedException("Книга ещё свободна!");
        issued = false;
    }

    public String toString(){
        return "Книга: " + name +
                "\nАвтор: " + author +
                "\nСтатус: " + (issued? "Выдана" : "Свободна");
    }
}
