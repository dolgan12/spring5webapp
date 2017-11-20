package me.garpo.spring5webapp.bootstrap;

import me.garpo.spring5webapp.model.Author;
import me.garpo.spring5webapp.model.Book;
import me.garpo.spring5webapp.model.Publisher;
import me.garpo.spring5webapp.repositories.AuthorRepository;
import me.garpo.spring5webapp.repositories.BookRepository;
import me.garpo.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        Publisher pub = new Publisher("Macmillan Audio");
        publisherRepository.save(pub);

        Author sanderson = new Author("Brandon", "Sanderson");
        Book oath = new Book("Oathbringer","967078678",pub);
        sanderson.getBooks().add(oath);
        oath.getAuthors().add(sanderson);

        authorRepository.save(sanderson);
        bookRepository.save(oath);

        Author cadle = new Author("Lou", "Cadle");
        Book gray = new Book("Gray", "890234", pub);
        cadle.getBooks().add(gray);
        gray.getAuthors().add(cadle);

        authorRepository.save(cadle);
        bookRepository.save(gray);

    }
}
