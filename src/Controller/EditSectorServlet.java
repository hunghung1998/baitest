package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import Model.Sectors;


@WebServlet(urlPatterns = { "/editProduct" })
public class EditSectorServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	 
	    public EditSectorServlet() {
	        super();
	    }
	 
	    // Hiển thị trang sửa sản phẩm.
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        Connection conn = MyUtils.getStoredConnection(request);
	 
	        String id = (String) request.getParameter("id");
	 
	        Sectors sector = null;
	 
	        String errorString = null;
	        	try {
					sector = DBUtils.findSector(conn, id);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     
	        if (errorString != null && sector == null) {
	            response.sendRedirect(request.getServletPath() + "/productList");
	            return;
	        }
	 
	        // Lưu thông tin vào request attribute trước khi forward sang views.
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("sector", sector);
	 
	        RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
	        dispatcher.forward(request, response);
	 
	    }
	 
	    // Sau khi người dùng sửa đổi thông tin sản phẩm, và nhấn Submit.
	    // Phương thức này sẽ được thực thi.
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        Connection conn = MyUtils.getStoredConnection(request);
	 
	        String id = (String) request.getParameter("id");
	        String name = (String) request.getParameter("name");
	        Part images = request.getPart("image");
	        InputStream input = images.getInputStream();
			byte[] b = IOUtils.toByteArray(input);
			input.read(b);
			String b64 = Base64.getEncoder().encodeToString(b);
	        String status =  request.getParameter("status");
	        
	        //int ad = Integer.parseInt(status);
	        Sectors sector = new Sectors(id, name,b64, status);
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
	       // if (errorString != null) {/
	        //    RequestDispatcher dispatcher = request.getServletContext()
	      //              .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
	          //  dispatcher.forward(request, response);
	      //  }
	        // Nếu mọi thứ tốt đẹp.
	        // Redirect sang trang danh sách sản phẩm.
	      //  else {
	        	 response.sendRedirect(request.getServletPath() + "/productList");
	        	// RequestDispatcher dispatcher = request.getServletContext()
		        //            .getRequestDispatcher("/WEB-INF/views/productListView.jsp");
		         //   dispatcher.forward(request, response);
	 //       }
	    }
	 
	}
