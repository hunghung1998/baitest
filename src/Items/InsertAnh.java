package Items;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import Controller.MyUtils;
import Controller.SQLServerConnUtils_SQLJDBC;
import Model.Items;
import Model.thu;


@WebServlet(urlPatterns = {"/insertanh"}) 
@MultipartConfig
public class InsertAnh extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public InsertAnh() {
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
		
		String user = req.getParameter("user");
		System.out.println(user);
		//String image = req.getParameter("base");
		//InputStream input = new FileInputStream(image);
		Part image = req.getPart("base");
		InputStream input = image.getInputStream();
		
		byte[] b = IOUtils.toByteArray(input);
		input.read(b);
		String b64 = Base64.getEncoder().encodeToString(b);
		thu t = new thu(user,b64);
		System.out.println(b64);
		String errorString = null;
		if (errorString == null && user!=null) {
        try {
			ItemsDAO.insertanh(conn, t);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 errorString = e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
        
        // Lưu thông tin vào request attribute trước khi forward sang views.
        req.setAttribute("errorString", errorString);
        req.setAttribute("add", t);
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
