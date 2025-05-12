package com.dev.bookstore.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(age, author.age) && Objects.equals(description, author.description) && Objects.equals(image, author.image) && Objects.equals(createdAt, author.createdAt) && Objects.equals(updatedAt, author.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, description, image, createdAt, updatedAt);
    }
}
