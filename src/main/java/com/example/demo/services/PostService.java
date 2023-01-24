package com.example.demo.services;

import com.example.demo.entities.Post;
import com.example.demo.entities.Tag;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

  private final PostRepository postRepository;

  private final TagRepository tagRepository;

  public PostService(
      final PostRepository postRepository,
      TagRepository tagRepository) {
    this.postRepository = postRepository;
    this.tagRepository = tagRepository;
  }

  @Transactional
  public Long createPost(final String title) {
    final Post post = new Post(title);
    return postRepository.save(post)
                         .getId();
  }

  @Transactional
  public void addTagToPost(final Long postId, final Long tagId) {
    final Post post = postRepository.findById(postId)
                                    .orElseThrow();
    final Tag tag = tagRepository.findById(tagId)
                                 .orElseThrow();
    post.addTag(tag);
    postRepository.save(post);
  }
}
