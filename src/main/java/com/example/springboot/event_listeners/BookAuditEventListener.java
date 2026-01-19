package com.example.springboot.event_listeners;

import com.example.springboot.events.BookCreatedEvent;
import com.example.springboot.events.BookDeletedEvent;
import com.example.springboot.events.BookUpdatedEvent;
import com.example.springboot.entities.AuditLog;
import com.example.springboot.repositories.AuditLogRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class BookAuditEventListener {

    private final AuditLogRepository auditLogRepository;

    public BookAuditEventListener(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @TransactionalEventListener
    public void handleBookCreated(BookCreatedEvent event) {
        auditLogRepository.save(
                new AuditLog(
                        "BOOK_CREATED",
                        "BOOK",
                        event.bookId(),
                        "Book created: " + event.title() + " by " + event.author()
                )
        );
    }

    @TransactionalEventListener
    public void handleBookUpdated(BookUpdatedEvent event) {
        auditLogRepository.save(
                new AuditLog(
                        "BOOK_UPDATED",
                        "BOOK",
                        event.bookId(),
                        "Book updated: " + event.title() + " by " + event.author()
                )
        );
    }

    @TransactionalEventListener
    public void handleBookDeleted(BookDeletedEvent event) {
        auditLogRepository.save(
                new AuditLog(
                        "BOOK_DELETED",
                        "BOOK",
                        event.bookId(),
                        "Book deleted"
                )
        );
    }
}

