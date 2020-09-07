package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import Model.UserAccount;

 
@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	Connection conn;
    private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
    }
 
    // Hiá»ƒn thá»‹ trang Login.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
 
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                user = DBUtils.findUser(conn, userName, password);
                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
 
            // LÆ°u cÃ¡c thÃ´ng tin vÃ o request attribute trÆ°á»›c khi forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward (Chuyá»ƒn tiáº¿p) tá»›i trang /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
            dispatcher.forward(request, response);
        }
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
 
            
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            else {
                MyUtils.deleteUserCookie(response);
            }
 
            // Redirect (Chuyá»ƒn hÆ°á»›ng) sang trang /userInfo.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
 
}
