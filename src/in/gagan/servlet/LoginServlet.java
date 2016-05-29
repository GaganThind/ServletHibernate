/*
 * Copyright (C) 2016 Gagandeep Singh Thind
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.gagan.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.gagan.bo.LoginBO;
import in.gagan.common.constants.ApplicationConstants;
import in.gagan.common.util.CommonUtil;
import in.gagan.common.util.LoggingUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Login authentication servlet", urlPatterns = { "/login" }, loadOnStartup = 0, initParams = {
		@WebInitParam(name = "userName", value = "Anonymous", description = "New user") })
public class LoginServlet extends HttpServlet {
	private static Logger logger = LoggingUtil.getLoggerInsance();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean ifNotNull = CommonUtil.checkIfNotNull(request, new String("userName,password"));
		boolean result = false;
		RequestDispatcher dispatcher = null;
		LoginBO loginBO = null;
		try {
			if (ifNotNull) {
				loginBO = new LoginBO();
				result = loginBO.Authenticate(request);
				if (result) {
					dispatcher = request.getRequestDispatcher("jsp/Success.jsp");
				} else {
					request.setAttribute(ApplicationConstants.JSPMESSAGE, ApplicationConstants.AUTHENTICATIONFAILED);
					dispatcher = request.getRequestDispatcher("jsp/Login.jsp");
				}
				dispatcher.forward(request, response);
			} else {
				request.setAttribute(ApplicationConstants.JSPMESSAGE, ApplicationConstants.NOUSERNAMEORPASSWORD);
				dispatcher = request.getRequestDispatcher("jsp/Login.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			logger.error("LoginServlet.doPost error: " + e);
		} finally {
			CommonUtil.makeVariablesNull(new Object[] { ifNotNull, loginBO, result, dispatcher });
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Login.jsp");
		dispatcher.forward(request, response);
	}

}
