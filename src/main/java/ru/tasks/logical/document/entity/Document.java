package ru.tasks.logical.document.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.tasks.logical.user.entity.User;

import java.util.UUID;

@Entity
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "document")
public class Document {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

}
