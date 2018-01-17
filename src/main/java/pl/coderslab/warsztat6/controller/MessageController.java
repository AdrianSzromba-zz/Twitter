package pl.coderslab.warsztat6.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.warsztat6.bean.SessionManager;
import pl.coderslab.warsztat6.entity.Message;
import pl.coderslab.warsztat6.entity.User;
import pl.coderslab.warsztat6.repository.MessageRepository;
import pl.coderslab.warsztat6.repository.UserRepository;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("")
	public String messageList(Model m) {
		m.addAttribute("message", new Message());
		return "message_list";
	}

	@GetMapping("/{id}")
	public String message(Model m, @PathVariable long id) {
		HttpSession s = SessionManager.session();
		if (s != null) {
			User sender = (User) s.getAttribute("user");
			if (sender != null && sender.getId() == id) {
				return "redirect:/message";
			}
		}
		m.addAttribute("message", new Message());
		m.addAttribute("recieverId", id);
		return "send_message";
	}

	@PostMapping("/add/{recieverId}")
	public String addPost(@Valid @ModelAttribute Message message, @PathVariable long recieverId,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/message/" + recieverId;
		}
		HttpSession s = SessionManager.session();
		User sender = (User) s.getAttribute("user");
		message.setSender(sender);
		User reciever = this.userRepository.findOne(recieverId);
		message.setReciever(reciever);
		message.setCreated(new Date());
		this.messageRepository.save(message);
		return "redirect:/message";
	}
	
	@GetMapping("/details/{id}")
	public String messageDetails(Model m, @PathVariable long id) {
		Message message = this.messageRepository.findOne(id);
		message.setChecked(1);
		this.messageRepository.save(message);
		m.addAttribute("message", message);
		return "read_message";
	}

	@ModelAttribute("userMessages")
	public List<Message> getMessages() {
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		return this.messageRepository.findByRecieverOrderByCreatedDesc(u);
	}
	
	@ModelAttribute("readMessages")
	public List<Message> getReadMessages() {
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		return this.messageRepository.findByRecieverAndCheckedLikeOrderByCreatedDesc(u, 1);
	}
	
	@ModelAttribute("unreadMessages")
	public List<Message> getUnreadMessages() {
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		return this.messageRepository.findByRecieverAndCheckedLikeOrderByCreatedDesc(u, 0);
	}

	@ModelAttribute("sentMessages")
	public List<Message> getSentMessages() {
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		return this.messageRepository.findBySenderOrderByCreatedDesc(u);
	}
}
