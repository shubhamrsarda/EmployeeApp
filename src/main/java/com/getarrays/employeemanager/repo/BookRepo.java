package com.getarrays.employeemanager.repo;

import com.getarrays.employeemanager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Long> {
}
