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

@WebServlet(urlPatterns = {"/insertItems"})
public class InsertItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public InsertItems() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/itemsView.jsp");
        dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(req);
		
		String mamh = req.getParameter("id");
		String tenmh = req.getParameter("name");
		String mancc = req.getParameter("sp_id");
		String malh = req.getParameter("sec_id");
		String price = req.getParameter("price");
		//Double giaban = Double.parseDouble(price);
		String unit = req.getParameter("unit");
		String status = req.getParameter("status");
		
		Items item = new Items(mamh,tenmh,mancc,malh,price,unit,status);
		
		String errorString = null;
		 
        // Mã sản phẩm phải là chuỗi chữ [a-zA-Z_0-9]
        // Có ít nhất một ký tự.
        /*String regex = "\\w+";
 
        if (id == null || !id.matches(regex)) {
            errorString = "Product Code invalid!";
        }
        */
        if (mamh == null) {
            errorString = "Please enter your product key";
        }
 
        if (errorString == null && mamh!=null) {
            try {
                ItemsDAO.insertItems(conn, item);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
 
        // Lưu thông tin vào request attribute trước khi forward sang views.
        req.setAttribute("errorString", errorString);
        req.setAttribute("item", item);
 
        // Nếu có lỗi forward (chuyển tiếp) sang trang 'edit'.
        if (errorString != null) {
        	resp.sendRedirect(req.getContextPath() + "/itemList");
        }
        // Nếu mọi thứ tốt đẹp.
        // Redirect (chuyển hướng) sang trang danh sách sản phẩm.
        else {
            resp.sendRedirect(req.getContextPath() + "/itemList");
        }
	}
}
