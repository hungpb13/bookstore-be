package com.dev.bookstore.mappers;

public interface Mapper<A, B> {
    A toEntity(B dto);

    B toDto(A entity);
}
