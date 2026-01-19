package com.example.springboot.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "audit_logs")
public class AuditLog extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private String entityType;
    private Long entityId;

    private String message;


    protected AuditLog() {}

    public AuditLog(String action, String entityType, Long entityId, String message) {
        this.action = action;
        this.entityType = entityType;
        this.entityId = entityId;
        this.message = message;
    }

    // getters & setters
}

