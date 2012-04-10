package fr.ippon.tatami.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * Manages a user's failure during login.
 * 
 * @author Damien Raude-Morvan
 */
@Component
public class TatamiAuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	/**
	 * {@inheritDoc}
	 */
    @Override
	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response,
										AuthenticationException exception)
			throws IOException, ServletException {

		// check if login is originated from ajax call
		if (isXHR(request)) {
			try {
				response.setContentType("application/json");
				response.getWriter().print("{\"success\":false}");
				response.getWriter().flush();
			} catch (IOException e) {
				// handle exception...
			}
		} else {
			super.onAuthenticationFailure(request, response, exception);
		}
	}
	
	private boolean isXHR(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

}
