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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.gagan.bo.SignUpBO;
import in.gagan.common.constants.ApplicationConstants;
import in.gagan.common.util.CommonUtil;
import in.gagan.common.util.LoggingUtil;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet(description = "Signup Servlet", urlPatterns = { "/SignUp", "/signup", "/register" })
public class SignUpServlet extends HttpServlet {
	private static Logger logger = LoggingUtil.getLoggerInsance();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/SignUp.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		boolean ifNotNull = CommonUtil.checkIfNotNull(request,
				new String("userName,firstName,lastName,dob,phoneNumber,email,password,confirmPassword"));
		SignUpBO signUpBO = null;
		String password, confirmPassword = null;

		password = request.getParameter("password");
		confirmPassword = request.getParameter("confirmPassword");
		try {
			if (ifNotNull) {
				if (password.equals(confirmPassword)) {
					signUpBO = new SignUpBO();
					boolean tmpResult = signUpBO.Register(request);
					if (tmpResult) {
						request.setAttribute(ApplicationConstants.JSPMESSAGE,
								ApplicationConstants.REGISTRATIONSUCCESSFUL);
						dispatcher = request.getRequestDispatcher("jsp/Login.jsp");
					} else {
						request.setAttribute(ApplicationConstants.JSPMESSAGE, ApplicationConstants.REGISTRATIONERROR);
						dispatcher = request.getRequestDispatcher("jsp/SignUp.jsp");
					}
				} else {
					request.setAttribute(ApplicationConstants.JSPMESSAGE,
							ApplicationConstants.PASSWORDANDCONFIRMPASSWORDNOTMATCH);
					dispatcher = request.getRequestDispatcher("jsp/SignUp.jsp");
				}
				dispatcher.forward(request, response);
			} else {
				request.setAttribute(ApplicationConstants.JSPMESSAGE, ApplicationConstants.SIGNUPNULLVALUES);
				dispatcher = request.getRequestDispatcher("jsp/SignUp.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			logger.error("SignUpServlet.doPost error: " + e);
		} finally {
			CommonUtil.makeVariablesNull(new Object[] { confirmPassword, ifNotNull, signUpBO, password });
		}
	}

}
