package pl.coderslab.warsztat6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.warsztat6.entity.Comment;
import pl.coderslab.warsztat6.entity.Tweet;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findByTweet(Tweet tweet);
	List<Comment> findByTweetIdOrderByCreatedDesc(Long id);
}
