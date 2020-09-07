package Items;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.DBUtils;
import Controller.MyUtils;

@WebServlet(urlPatterns = { "/deleteItems" })
public class DeleteItems extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
    public DeleteItems() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection conn = MyUtils.getStoredConnection(request);
    	 
        String id = (String) request.getParameter("id");
 
        String errorString = null;
 
        try {
            ItemsDAO.deleteItems(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
         
        
        if (errorString != null) {
            
            request.setAttribute("errorString", errorString);
            // 
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/deleteItemsView.jsp");
            dispatcher.forward(request, response);
        }
       
        else {
            response.sendRedirect(request.getContextPath() + "/itemList");
        }
    }
}
