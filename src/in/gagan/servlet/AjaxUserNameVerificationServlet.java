package in.gagan.servlet;

import java.io.IOException;
import java.util.Map;

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
import jdk.nashorn.internal.ir.debug.JSONWriter;

/**
 * Servlet implementation class AjaxUserNameVerificationServlet
 */
@WebServlet("/verifyusername")
public class AjaxUserNameVerificationServlet extends HttpServlet {
	private static Logger logger = LoggingUtil.getLoggerInsance();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxUserNameVerificationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = null;
		SignUpBO signUpBO = null;
		String result = ApplicationConstants.FALSE;
		boolean ifNotNull = CommonUtil.checkIfNotNull(request, new String("userName"));
		if (ifNotNull) {
			username = request.getParameter("userName");
			try {
				result = signUpBO.getUserName(username);
			} catch (Exception e) {
				logger.error("AjaxUserNameVerificationServlet.doPost error: " + e);
			}
		}
		write(response,result);
	}
	
	private void write(HttpServletResponse response, String data){
		try{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(data);
		}catch(Exception e){
			logger.error("AjaxUserNameVerificationServlet.write error: "+e);
		}
	}
}
