package common.controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface InterCommand {
	void execute(HttpServletRequest request, HttpServletResponse response) throws IOException; 
}
