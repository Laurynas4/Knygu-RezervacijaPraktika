package com.company.DAO;

import com.company.Entity.LibraryBooks;
import com.company.Utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class LibraryBooksDAO {

    public LibraryBooksDAO() {
    }

    public void addBook(LibraryBooks libraryBooks) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(libraryBooks);
        session.getTransaction().commit();
        System.out.println("Book added!");
    }

    public void editBook(int id, String title, String summary, int pages) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        LibraryBooks libraryBooks = session.get(LibraryBooks.class, id);
        libraryBooks.setBookTitle(title);
        libraryBooks.setBookSummary(summary);
        libraryBooks.setNumberOfPages(pages);
        System.out.println(libraryBooks);
        session.update(libraryBooks);
        session.getTransaction().commit();
    }

    public void deleteBookByID(int bookId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        LibraryBooks libraryBooks = session.get(LibraryBooks.class, bookId);
        if (libraryBooks != null) {
            session.delete(libraryBooks);
            System.out.println("Book deleted by ID.");
        }
        session.getTransaction().commit();
    }

    public void allAvailableBooks(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<LibraryBooks> libraryBooks = session.createQuery("select a from LibraryBooks a where reservation = 0").getResultList();
        System.out.println("All books in the library:\n");
        for(LibraryBooks list : libraryBooks){
            System.out.println(list);
        }
        session.getTransaction().commit();
    }

    public void bookReservation(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        LibraryBooks libraryBooks = session.get(LibraryBooks.class, id);
        libraryBooks.setReservation(true);
        session.update(libraryBooks);
        session.getTransaction().commit();
    }
}