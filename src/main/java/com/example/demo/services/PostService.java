package com.example.demo.services;

import com.example.demo.entities.Post;
import com.example.demo.entities.Tag;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Service
@Validated
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
  public Long createPost(final @NotBlank String title) {
    final Post post = new Post(title);
    return postRepository.save(post)
                         .getId();
  }

  /**
   * This approach does not work; the manual save causes a merge to be done on Post. The merge creates a new instance
   * of PostTag, but the original PostTag is still referenced from Tag, so this causes an exception.
   */
  @Transactional
  public void addTagToPostWithSave(final @NotNull Long postId, final @NotNull Long tagId) {
    final Post post = postRepository.findById(postId)
                                    .orElseThrow();
    final Tag tag = tagRepository.findById(tagId)
                                 .orElseThrow();
    post.addTag(tag);
    postRepository.save(post);
  }

  /**
   * You need to rely on JPAs dirty checking to do a flush for you; in that case you can successfully create the
   * bidirectional many-to-many relationship.
   */
  @Transactional
  public void addTagToPostWithoutSave(final @NotNull Long postId, final @NotNull Long tagId) {
    final Post post = postRepository.findById(postId)
                                    .orElseThrow();
    final Tag tag = tagRepository.findById(tagId)
                                 .orElseThrow();
    post.addTag(tag);
  }
}
