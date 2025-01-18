package ru.tasks.logical.auth.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

@RequiredArgsConstructor
public class HttpRequestEndpointChecker {

	private final DispatcherServlet servlet;

	public boolean isEndpointExists(HttpServletRequest request) {
		for (HandlerMapping handlerMapping : servlet.getHandlerMappings()) {
			try {
				HandlerExecutionChain foundHandler = handlerMapping.getHandler(request);
				if (foundHandler != null) {
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
}
