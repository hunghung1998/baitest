package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Sectors;

@WebServlet(urlPatterns = { "/searchSector" })
public class searchSector extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn =MyUtils.getStoredConnection(request);
		String action  = request.getParameter("action");
		Sectors list=null;
		if(action.equals("search")) {
			String id = request.getParameter("txtid");
			 try {
			list =DBUtils.findSector(conn, id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 request.setAttribute("listid", list);
			 RequestDispatcher dispatcher = request.getServletContext()
		                .getRequestDispatcher("/WEB-INF/views/productListView.jsp");
		        dispatcher.forward(request, response);
		}
		
	}
}
