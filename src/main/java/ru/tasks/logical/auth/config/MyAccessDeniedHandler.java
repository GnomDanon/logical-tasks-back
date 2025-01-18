package ru.tasks.logical.auth.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import java.io.IOException;

@RequiredArgsConstructor
public class MyAccessDeniedHandler extends AccessDeniedHandlerImpl {

	private final HttpRequestEndpointChecker endpointChecker;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
					   AccessDeniedException accessDeniedException) throws IOException, ServletException {

		if (!endpointChecker.isEndpointExists(request)) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
		} else {
			super.handle(request, response, accessDeniedException);
		}
	}
}
