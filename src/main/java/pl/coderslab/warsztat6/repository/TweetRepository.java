package pl.coderslab.warsztat6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.warsztat6.entity.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long>{

	List<Tweet> findAllByUserUsername(String username);
	List<Tweet> findAllByUserIdOrderByCreatedDesc(Long id);
	List<Tweet> findByUserEmail(String email);
}
