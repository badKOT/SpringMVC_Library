package ru.spring.tutorial.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.spring.tutorial.models.Book;
import ru.spring.tutorial.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show (int id) {
        return jdbcTemplate.query("select * from book where id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream()
                .findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book (title, author, year) values (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book newBook) {
        jdbcTemplate.update("update book set title=?, author=?, year=? where id=?",
                newBook.getTitle(), newBook.getAuthor(), newBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id=?", id);
    }

    public void takeTheBook(int book_id, int person_id) {
        jdbcTemplate.update("update book set person_id=? where id=?", person_id, book_id);
    }

    public void returnBook(int book_id) {
        jdbcTemplate.update("update book set person_id=null where id=?", book_id);
    }

    public List<Book> booksTakenByPerson(int person_id) {
        return jdbcTemplate.query("select * from book where person_id=?",
                new Object[] {person_id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Person personThatTookTheBook(int book_id) {
        return jdbcTemplate.query("select person_id, name from book " +
                        "join person on person.id = book.person_id where book.id=?",
                new Object[] {book_id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
}
