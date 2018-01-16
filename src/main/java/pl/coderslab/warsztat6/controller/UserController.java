package pl.coderslab.warsztat6.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String loginPost(@ModelAttribute LoginData loginData, Model m, RedirectAttributes ra) {
		User user = this.userRepository.findOneByEmail(loginData.getEmail());
		if (user != null && user.isPasswordCorrect(loginData.getPassword())) {
			HttpSession s = SessionManager.session();
			s.setAttribute("user", user);
			ra.addFlashAttribute("msg", "Jestes zalogowany");
			return "redirect:/";
		}
		m.addAttribute("msg", "Wprowadz poprawne dane");
		return "login";
	}
	
	//------------------------------------- CHANGE -------------------------------------
	
	@GetMapping("/change")
	public String change(Model m) {
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		m.addAttribute("user", u);
		return "change";
	}

	@PostMapping("/change")
	public String changePost(@Valid @ModelAttribute User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/register";
		}
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		user.setId(u.getId());
		this.userRepository.save(user);

		return "redirect:/";
	}
	
	//------------------------------------- DELETE -------------------------------------
	
	@GetMapping("/delete")
	public String delete(Model m) {
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		m.addAttribute("user", u);
		return "delete";
	}
	
	@GetMapping("/delete/{dec}")
	public String deletePost(@PathVariable int dec) {
		if (dec == 1) {
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		s.invalidate();
		this.userRepository.delete(u);
		return "redirect:/login";
		}
		return "redirect:/";
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
