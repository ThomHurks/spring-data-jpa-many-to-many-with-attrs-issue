package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostTagId implements Serializable {

  @Column(name = "post_id", nullable = false)
  private Long postId;

  @Column(name = "tag_id", nullable = false)
  private Long tagId;

  public PostTagId() {
  }

  public PostTagId(
      Long postId,
      Long tagId) {
    this.postId = postId;
    this.tagId = tagId;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof final PostTagId other) {
      return Objects.equals(getPostId(), other.getPostId()) &&
          Objects.equals(getTagId(), other.getTagId());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPostId(), getTagId());
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public Long getTagId() {
    return tagId;
  }

  public void setTagId(Long tagId) {
    this.tagId = tagId;
  }
}