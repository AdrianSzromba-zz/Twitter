package pl.coderslab.warsztat6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.warsztat6.repository.TweetRepository;

@Controller
@RequestMapping("/tweet")
public class TweetController {
	
	@Autowired
	TweetRepository tweetRepository;

}
