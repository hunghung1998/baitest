package Items;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.DBUtils;
import Controller.MyUtils;
import Model.Items;
import Model.thu;
@WebServlet(urlPatterns = {"/itemList"})
public class Listitems extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public Listitems() {
		super();
	}
	
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ItemsDAO itemDAO = new ItemsDAO();
        int page=1,pageSize = 5;  
		int totalpage = itemDAO.getcountItem();
        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(totalpage %  pageSize ==0) {
        	totalpage = totalpage/pageSize;
        }else {
        	totalpage = totalpage/pageSize+1;
        }
       
        List<Items> list1=null;
		try {
			list1 = itemDAO.viewAllEmployees(page,pageSize);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Connection conn = MyUtils.getStoredConnection(request);
		ArrayList<thu> list=null;
		
		try {
			list = (ArrayList<thu>) itemDAO.Viewthu(conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //List<Items> list = dao.viewAllEmployees();
        System.out.print(page+"space"+totalpage);
        request.setAttribute("employeeList", list1);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("page", page);
        request.setAttribute("view", list);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/itemsView.jsp");
        view.forward(request, response);
    }
}


