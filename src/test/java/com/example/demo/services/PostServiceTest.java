package com.example.demo.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

  @Autowired
  private PostService postService;

  @Autowired
  private TagService tagService;

  /**
   * This approach does not work; the manual save causes a merge to be done on Post. The merge creates a new instance
   * of PostTag, but the original PostTag is still referenced from Tag, so this causes an exception.
   */
  @Test
  void testAddTagToPostWithSaveDoesNotWork() {
    final Long postId = postService.createPost("Hello world!");
    final Long tagId = tagService.createTag("Awesome posts");
    postService.addTagToPostWithSave(postId, tagId);
  }

  /**
   * You need to rely on JPAs dirty checking to do a flush for you; in that case you can successfully create the
   * bidirectional many-to-many relationship.
   */
  @Test
  void testAddTagToPostWithoutSaveDoesWork() {
    final Long postId = postService.createPost("My Second post");
    final Long tagId = tagService.createTag("Less awesome posts");
    postService.addTagToPostWithoutSave(postId, tagId);
  }
}
