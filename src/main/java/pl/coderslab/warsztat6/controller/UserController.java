package pl.coderslab.warsztat6.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.coderslab.warsztat6.bean.LoginData;
import pl.coderslab.warsztat6.bean.SessionManager;
import pl.coderslab.warsztat6.entity.User;
import pl.coderslab.warsztat6.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	//------------------------------------- REGISTER -------------------------------------
	
	@GetMapping("/register")
	public String register(Model m) {
		m.addAttribute("user", new User()); // przekazanie do formularza modelu User
		return "register";
	}

	@PostMapping("/register")
	public String registerPost(@Valid @ModelAttribute User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/register";
		}
		this.userRepository.save(user);
		return "redirect:/";
	}
	
	//------------------------------------- LOGIN -------------------------------------
	
	@GetMapping("/login")
	public String login(Model m) {
		m.addAttribute("loginData", new LoginData()); // przekazanie do formularza modelu User
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPost(@ModelAttribute LoginData loginData, Model m) {
		User user = this.userRepository.findOneByEmail(loginData.getEmail());
		if (user != null && user.isPasswordCorrect(loginData.getPassword())) {
			HttpSession s = SessionManager.session();
			s.setAttribute("user", user);
			return "redirect:/";
		}
		m.addAttribute("msg", "Wprowadz poprawne dane");
		return "login";
	}
	
	//------------------------------------- LOGOUT -------------------------------------
	
	@GetMapping("/logout")
	public String logout(Model m) {
		m.addAttribute("loginData", new LoginData());
		HttpSession s = SessionManager.session();
		s.invalidate();
		return "redirect:/";
	}

}
