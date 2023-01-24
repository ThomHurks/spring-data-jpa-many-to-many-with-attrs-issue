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

  @Test
  void testAddTagToPost() {
    final Long postId = postService.createPost("Hello world!");
    final Long tagId = tagService.createTag("Awesome posts");
    postService.addTagToPost(postId, tagId);
  }
}
