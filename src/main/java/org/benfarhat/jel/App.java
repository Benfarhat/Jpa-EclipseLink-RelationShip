package org.benfarhat.jel;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.benfarhat.jel.model.Author;
import org.benfarhat.jel.model.Book;
import org.benfarhat.jel.model.Genre;
import org.benfarhat.jel.model.Lang;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static final String PERSISTENCE_UNIT_NAME = "books";
	private static EntityManagerFactory factory;
	
	private EntityManager getEntityManager() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);    
        return factory.createEntityManager();	
	}
    public static void main( String[] args )
    {
    	System.out.println("** JPA * ECLIPSELINK **");

        App app = new App();
        
        EntityManager em = app.getEntityManager();
        
        Query ql = em.createQuery("select l from Lang l");
        Query qg = em.createQuery("select g from Genre g");
        Query qa = em.createQuery("select a from Author a");
        Query qb = em.createQuery("select b from Book b");

        boolean createNewLangEntries = (ql.getResultList().size() == 0);
        boolean createNewGenreEntries = (qg.getResultList().size() == 0);
        boolean createNewAuthorEntries = (qa.getResultList().size() == 0);
        boolean createNewBookEntries = (qb.getResultList().size() == 0);

        if(createNewLangEntries)
        	app.addLang(em);
        if(createNewGenreEntries)
        	app.addGenre(em);
        if(createNewAuthorEntries)
        	app.addAuthor(em);
        if(createNewBookEntries)
        	app.addBook(em);
        
        app.demo(em);
        
        em.close();
       
    }

    private void addLang(EntityManager em) {

    	em.getTransaction().begin();
    	// ---
    	Lang l1 = new Lang("English");
    	em.persist(l1);
    	Lang l2 = new Lang("French");
    	em.persist(l2);
    	Lang l3 = new Lang("Arabic");
    	em.persist(l3);
    	Lang l4 = new Lang("German");
    	em.persist(l4);
    	Lang l5 = new Lang("Spanish");
    	em.persist(l5);
    	// ---
    	em.getTransaction().commit();
    	
    	System.out.println("\nLangage List:\n");
        Query q = em.createQuery("select l from Lang l order by l.id");
        List<Lang> langList = q.getResultList();
        for (Lang lang : langList) {
            System.out.println(lang);
        }
    
    }
    private void addGenre(EntityManager em) {

    	em.getTransaction().begin();
    	// ---
    	Genre g1 = new Genre("Science fiction");
    	em.persist(g1);
    	Genre g2 = new Genre("Drama");
    	em.persist(g2);
    	Genre g3 = new Genre("Adventure");
    	em.persist(g3);
    	Genre g4 = new Genre("Mystery");
    	em.persist(g4);
    	Genre g5 = new Genre("Horror");
    	em.persist(g5);
    	Genre g6 = new Genre("Fantasy");
    	em.persist(g6);
    	Genre g7 = new Genre("Guide");
    	em.persist(g7);
    	Genre g8 = new Genre("Children's");
    	em.persist(g8);
    	// ---
    	em.getTransaction().commit();
    	
    	System.out.println("\nGenre List:\n");
        Query q = em.createQuery("select g from Genre g order by g.id");
        List<Genre> genreList = q.getResultList();
        for (Genre genre : genreList) {
            System.out.println(genre);
        }
    
    }

    

    private void addAuthor(EntityManager em) {

    	em.getTransaction().begin();
    	// ---
    	Author a1 = new Author("Ayn", "Rand");
    	em.persist(a1);
    	Author a2 = new Author("Ernest", "Hemingway");
    	em.persist(a2);
    	Author a3 = new Author("Joan", "Didion");
    	em.persist(a3);
    	Author a4 = new Author("Ray", "Bradbury");
    	em.persist(a4);
    	Author a5 = new Author("Gillian", "Flynn");
    	em.persist(a5);
    	Author a6 = new Author("Meg", "Wolitzer");
    	em.persist(a6);
    	Author a7 = new Author("Erik", "Larson");
    	em.persist(a7);
    	Author a8 = new Author("F.Scott", "Fitzgerald");
    	em.persist(a8);
    	Author a9 = new Author("R.L.", "Stine");
    	em.persist(a9);
    	Author a10 = new Author("J.K.", "Rowling");
    	em.persist(a10);
    	// ---
    	em.getTransaction().commit();
    	
    	System.out.println("\nAuthor List:\n");
        Query q = em.createQuery("select a from Author a order by a.id");
        List<Author> authorList = q.getResultList();
        for (Author author : authorList) {
            System.out.println(author);
        }
    }  
    
    private void addBook(EntityManager em) {
    	em.getTransaction().begin();
    	System.out.println("\nCreate random book:\n");
    	Query g = em.createQuery("select g.id from Genre g");
    	Query l = em.createQuery("select l.id from Lang l");
    	Query a = em.createQuery("select a.id from Author a");
        List genreList =  (List) g.getResultList();
        List langList =  (List) l.getResultList();
        List authorList =  (List) a.getResultList();
        Random rand = new Random();
    	for(int i = 0; i < 250; i++) {
            int randomIndex1 = rand.nextInt(genreList.size());
            int randomGenre = (int) genreList.get(randomIndex1);
            int randomIndex2 = rand.nextInt(langList.size());
            int randomLang = (int) langList.get(randomIndex2);
            int randomIndex3 = rand.nextInt(authorList.size());
            int randomAuthor = (int) authorList.get(randomIndex3);
            
        	Query q1 = em.createQuery("select g from Genre g where g.id = :random")
        			.setParameter("random", randomGenre);
            Genre rg = (Genre) q1.getSingleResult();              
        	Query q2 = em.createQuery("select g from Lang g where g.id = :random")
        			.setParameter("random", randomLang);
            Lang rl = (Lang) q2.getSingleResult();           
        	Query q3 = em.createQuery("select a from Author a where a.id = :random")
        			.setParameter("random", randomAuthor);
            Author ra = (Author) q3.getSingleResult();
            
            
            Book book = new Book(
            		"Title - " + i,
            		ra,
            		rl, 
            		rg , 
            		(int) (Math.random() * ((800 - 100) + 1)) + 100,
            		LocalDate.now().minusDays((int) (Math.random() * (1000 + 1)) + 10)
            		);
            em.persist(book);
    	}    
    	
    	em.getTransaction().commit();
    
    	System.out.println("\nBook List:\n");
        Query q = em.createQuery("select b from Book b order by b.id");
        List<Book> bookList = q.getResultList();
        for (Book book : bookList) {
            System.out.println(book);
        }
        

    }        
    

    private void demo(EntityManager em) {
    	
    	/*

		@TODO
		
		- Inside Author model add something like this:
		
	    @OneToMany
	    protected Collection<Book> books = new ArrayList<Book>();
	    
	    public Collection<Book> getBooks() {
	        return books;
	    }
	    
	    public void addBook(Book book) {
	        if (!getBooks().contains(book)) {
	            getBooks().add(book);
	        }
	    }	
	    
	    - Trying to remove with
	    Book book = ....
		books.removeIf(b -> b.equals(book));

	    books.removeAll()
	    
	    or with filter
	    Book book = ....
		List<Book> filtered = books.stream()
		                           .filter(b -> b.equals(other))
		                           .collect(Collectors.toList());

		- Fix our join query:
		- The collection-valued path 'a.Book' cannot be resolved to a valid association field
        Query q = em.createQuery("SELECT a, b FROM Author a LEFT JOIN a.Book b");
        List resultList = q.getResultList();
        for (Object result : resultList) {
            printResult(result);
            
        }
        */
    }
    

    private static void printResult(Object result) {
      if (result == null) {
        System.out.print("NULL");
      } else if (result instanceof Object[]) {
        Object[] row = (Object[]) result;
        System.out.print("[");
        for (int i = 0; i < row.length; i++) {
          printResult(row[i]);
        }
        System.out.print("]");
      } else if (result instanceof Long || result instanceof Double
          || result instanceof String) {
        System.out.print(result.getClass().getName() + ": " + result);
      } else {
        System.out.print(result);
      }
      System.out.println();
    }

    
}
