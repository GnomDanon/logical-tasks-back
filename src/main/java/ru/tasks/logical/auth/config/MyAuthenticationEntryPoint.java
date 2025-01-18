package ru.tasks.logical.auth.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import java.io.IOException;

@RequiredArgsConstructor
public class MyAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

	private final HttpRequestEndpointChecker endpointChecker;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException arg2) throws IOException {

		if (!endpointChecker.isEndpointExists(request)) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
		} else {
			super.commence(request, response, arg2);
		}
	}
}
