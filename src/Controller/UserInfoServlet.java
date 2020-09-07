package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import Model.UserAccount;

 
@WebServlet(urlPatterns = { "/userInfo" })
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public UserInfoServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
 
        // Kiá»ƒm tra ngÆ°á»�i dÃ¹ng Ä‘Ã£ Ä‘Äƒng nháº­p (login) chÆ°a.
       UserAccount loginedUser =MyUtils.getLoginedUser(session);
 
        // Náº¿u chÆ°a Ä‘Äƒng nháº­p (login).
       /* if (loginedUser == null) {
            // Redirect (Chuyá»ƒn hÆ°á»›ng) tá»›i trang login.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        */
       
        request.setAttribute("user", loginedUser);
       
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
