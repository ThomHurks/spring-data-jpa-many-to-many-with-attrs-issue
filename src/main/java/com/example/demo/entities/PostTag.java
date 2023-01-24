package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Objects;

@Entity(name = "PostTag")
@Table(name = "post_tag")
public class PostTag {

  @EmbeddedId
  private PostTagId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("postId")
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("tagId")
  @JoinColumn(name = "tag_id", nullable = false)
  private Tag tag;

  @Column(name = "created_on")
  private Instant createdOn = Instant.now();

  public PostTag() {
  }

  public PostTag(Post post, Tag tag) {
    this.post = post;
    this.tag = tag;
    this.id = new PostTagId(post.getId(), tag.getId());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof final PostTag other) {
      return Objects.equals(getPost(), other.getPost()) &&
          Objects.equals(getTag(), other.getTag());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPost(), getTag());
  }

  public PostTagId getId() {
    return id;
  }

  public void setId(PostTagId id) {
    this.id = id;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }

  public Instant getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }
}
