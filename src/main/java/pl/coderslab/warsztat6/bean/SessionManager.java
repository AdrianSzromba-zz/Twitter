package pl.coderslab.warsztat6.bean;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionManager {

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); //obiekt trzymający request
		return attr.getRequest().getSession(true); //Dostanie się do sesji z request, jezeli jej nie ma, zostanie stworzona
	}
}
