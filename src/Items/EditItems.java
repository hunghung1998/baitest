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
import Model.Items;
import Model.Sectors;

@WebServlet(urlPatterns = {"/editItems"})
public class EditItems extends HttpServlet{
	
    public EditItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	/*@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		 
        String id = (String) request.getParameter("id");
 
        Items item = null;
 
        String errorString = null;
 
      
        	try {
				item = DBUtils.findItems(conn, id);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     
        if (errorString != null && item == null) {
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }
 
        // Lưu thông tin vào request attribute trước khi forward sang views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("sector", item);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editItemView.jsp");
        dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Connection conn = MyUtils.getStoredConnection(request);
 
        String id = (String) request.getParameter("id");
        String name = (String) request.getParameter("name");
        String sp_id =  request.getParameter("sp_id");
        String sec_id = (String) request.getParameter("sec_id");
        String unit = (String) request.getParameter("unit");
        String price = request.getParameter("price");
        String status =  request.getParameter("status");
        
        //int ad = Integer.parseInt(status);
        Items item = new Items(id, name, sp_id,sec_id,unit,price,status);
 
        String errorString = null;
 
        try {
            DBUtils.updateSectors(conn, sector);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Lưu thông tin vào request attribute trước khi forward sang views.
   
        
        request.setAttribute("errorString", errorString);
        request.setAttribute("sector", sector);
 
        // Nếu có lỗi forward sang trang edit.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
            dispatcher.forward(request, response);
        }
        // Nếu mọi thứ tốt đẹp.
        // Redirect sang trang danh sách sản phẩm.
        else {
        	 //response.sendRedirect(request.getServletPath() + "/productList");
        	 RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/productListView.jsp");
	            dispatcher.forward(request, response);
        }
    }
	}*/
}
