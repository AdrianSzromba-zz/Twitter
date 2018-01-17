package pl.coderslab.warsztat6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.warsztat6.entity.Message;
import pl.coderslab.warsztat6.entity.User;

public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByRecieverIdOrderByCreatedDesc(long id);
	List<Message> findByRecieverOrderByCreatedDesc(User reciever);
	List<Message> findBySenderOrderByCreatedDesc(User sender);
	List<Message> findByRecieverAndCheckedLikeOrderByCreatedDesc(User reciever, int checked);
}
