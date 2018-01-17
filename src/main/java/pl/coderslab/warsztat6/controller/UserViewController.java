package pl.coderslab.warsztat6.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.coderslab.warsztat6.bean.SessionManager;
import pl.coderslab.warsztat6.entity.Tweet;
import pl.coderslab.warsztat6.entity.User;
import pl.coderslab.warsztat6.repository.TweetRepository;

@Controller
public class UserViewController {

	@Autowired
	private TweetRepository tweetRepository;
	
	@GetMapping("/user")
	public String user(Model m) {
		m.addAttribute("tweet", new Tweet());
		return "user";
	}
	
	@ModelAttribute("userTweets")
	public List<Tweet> getUserTweets() {
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		return this.tweetRepository.findAllByUserIdOrderByCreatedDesc(u.getId());
	}
}
