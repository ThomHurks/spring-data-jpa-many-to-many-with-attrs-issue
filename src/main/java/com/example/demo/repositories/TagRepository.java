package com.example.demo.repositories;

import com.example.demo.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

@Repository
@Validated
public interface TagRepository extends JpaRepository<Tag, Long> {

}