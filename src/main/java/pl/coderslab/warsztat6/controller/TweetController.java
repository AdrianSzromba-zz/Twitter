package pl.coderslab.warsztat6.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.warsztat6.bean.SessionManager;
import pl.coderslab.warsztat6.entity.Tweet;
import pl.coderslab.warsztat6.entity.User;
import pl.coderslab.warsztat6.repository.TweetRepository;

@Controller
@RequestMapping("/tweet")
public class TweetController {
	
	@Autowired
	TweetRepository tweetRepository;

	@PostMapping("/add")
	public String addPost(@Valid @ModelAttribute Tweet tweet, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "home";
		}
		HttpSession s = SessionManager.session();
		User u = (User)s.getAttribute("user");
		tweet.setUser(u);
		tweet.setCreated(new Date());
		this.tweetRepository.save(tweet);
		return "redirect:/";
	}
	
}
