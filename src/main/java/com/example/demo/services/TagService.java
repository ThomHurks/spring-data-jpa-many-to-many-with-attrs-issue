package com.example.demo.services;

import com.example.demo.entities.Tag;
import com.example.demo.repositories.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagService {

  private final TagRepository tagRepository;

  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  @Transactional
  public Long createTag(final String name) {
    final Tag tag = new Tag(name);
    return tagRepository.save(tag)
                        .getId();
  }
}
