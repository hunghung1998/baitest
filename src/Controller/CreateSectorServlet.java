package Controller;

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

import Model.Sectors;

@WebServlet(urlPatterns = { "/createProduct" })
@MultipartConfig
public class CreateSectorServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
    public CreateSectorServlet() {
        super();
    }
 
    // Hiển thị trang tạo sản phẩm.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
        dispatcher.forward(request, response);
    }
 
    // Khi người dùng nhập các thông tin sản phẩm, và nhấn Submit.
    // Phương thức này sẽ được gọi.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String id = (String) request.getParameter("id");
        String name = (String) request.getParameter("name");
        Part images = request.getPart("image");
        InputStream input = images.getInputStream();
		byte[] b = IOUtils.toByteArray(input);
		input.read(b);
		String b64 = Base64.getEncoder().encodeToString(b);
        String status = (String) request.getParameter("status");
        //int a = Integer.parseInt(status);
        Sectors sector = new Sectors(id, name,b64, status);
 
        String errorString = null;
 
        // Mã sản phẩm phải là chuỗi chữ [a-zA-Z_0-9]
        // Có ít nhất một ký tự.
        /*String regex = "\\w+";
 
        if (id == null || !id.matches(regex)) {
            errorString = "Product Code invalid!";
        }
        */
        if (id == null) {
            errorString = "Please enter your product key";
        }
 
        if (errorString == null && id!=null) {
            try {
                DBUtils.insertSectors(conn, sector);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
 
        // Lưu thông tin vào request attribute trước khi forward sang views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("sector", sector);
 
        // Nếu có lỗi forward (chuyển tiếp) sang trang 'edit'.
        if (errorString != null) {
        	response.sendRedirect(request.getContextPath() + "/productList");
        }
        // Nếu mọi thứ tốt đẹp.
        // Redirect (chuyển hướng) sang trang danh sách sản phẩm.
        else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }
    }
 
}