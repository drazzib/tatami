package fr.ippon.tatami.security;

import fr.ippon.tatami.domain.User;
import fr.ippon.tatami.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Manages a user's successful login.
 * 
 * @author Julien Dubois
 * @author Damien Raude-Morvan
 */
@Component
public class TatamiAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {

	@Inject
	private UserService userService;

	/**
	 * {@inheritDoc}
	 */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        String login = authentication.getName();
        if (userService.getUserByLogin(login) == null) {
            User user = new User();
            user.setLogin(login);
            user.setFirstName(login);
            user.setLastName("");
            user.setEmail(login + "@ippon.fr");
            userService.createUser(user);
        }
		
		// check if login is originated from ajax call
		if (isXHR(request)) {
			try {
				clearAuthenticationAttributes(request);
				response.setContentType("application/json");
				response.getWriter().print("{\"success\":true");
				if (this.getTargetUrlParameter() != null) {
					response.getWriter().print(", \"targetUrl\":\""+ this.getTargetUrlParameter() + "\"");
				}
				response.getWriter().print("}");
				response.getWriter().flush();
			} catch (IOException e) {
				// handle exception...
			}
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
			
	}

	private boolean isXHR(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}
}
