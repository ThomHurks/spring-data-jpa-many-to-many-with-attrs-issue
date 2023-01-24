The code from the blog https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/ by Vlad Mihalcea, when naively integrated into a Spring Boot project, gives the exception 
"org.springframework.dao.DataIntegrityViolationException: A different object with the same identifier 
value was already associated with the session : 
[com.example.demo.entities.PostTag#com.example.demo.entities.PostTagId@3e2];"

There is a reason for this, but it can be tricky to find out (as I experienced).

Jens Schauder (@schauder), Spring Data JPA developer, explains the following:

>What is happening is you have:
Post has a list of PostTag.
So does Tag.
>
>Within a single transaction you load a Post and a Tag, create a new PostTag referencing both and add that to the lists of both Tag and Post.
>
>You then call save on the PostRepository, which essentially does an EntityManager.merge on the Post.
>
>The merge creates a new PostTag instance which is now in the persistence context (aka 1st level cache).
But the original PostTage is still referenced by the Tag instance, which gets flushed at the end of the transaction, resulting in the exception you are seeing.
>
>Not sure what to do about it.
I'm not sure we can convince the Hibernate team that it is a bug.
I also don't think it is a Spring Data JPA bug.
So I'd say it is just one of these weird corner cases of JPA that you need to avoid.
>
>From a Spring Data / DDD perspective you should avoid bidirectional relationships.
Alternatively you can remove the save call and rely on JPAs dirty checking to do a flush for you.

So the answer is to either use a unidirectional many-to-many relationship or not manually call `save()` but 
let the dirty checking handle it for you.
