package in.gagan.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.gagan.bo.LoginBO;
import in.gagan.common.constants.ApplicationConstants;
import in.gagan.common.util.CommonUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(
		description = "Login authentication servlet", 
		urlPatterns = { "/login" },
		loadOnStartup=0,
		initParams = { 
				@WebInitParam(name = "userName", value = "Anonymous", description = "New user")
		})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		boolean ifNotNull = CommonUtil.checkIfNotNull(new String("userName,password"));
		boolean result=false;
		RequestDispatcher dispatcher=null;
		LoginBO loginBO =null;
		String password =null;
		String userName = null;
		
		if(ifNotNull){
			loginBO = new LoginBO();
			userName = request.getParameter("userName");
			password = request.getParameter("password");
			result = loginBO.Authenticate(userName,password);
			if(result){
			dispatcher = request.getRequestDispatcher("jsp/Success.jsp");
			}
			else{
			request.setAttribute("message", ApplicationConstants.EMPTYUSERNAMEORPASSWORD);
			dispatcher = request.getRequestDispatcher("jsp/Login.jsp");
			}
			dispatcher.forward(request, response);
		}
		else{
			request.setAttribute("message", ApplicationConstants.NOUSERNAMEORPASSWORD);
			dispatcher = request.getRequestDispatcher("jsp/Login.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Login.jsp");
		dispatcher.forward(request, response);
	}

}
