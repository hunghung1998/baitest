package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.Sectors;


@WebServlet(urlPatterns = { "/productList" })
public class ProductListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	 public ProductListServlet() {
	        super();
}
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        Connection conn =MyUtils.getStoredConnection(request);
	        String errorString = null;
	        ArrayList<Sectors> list = null;
	        
	        DBUtils db = new DBUtils();
	        
	        int page=1,pageSize = 5;  
			int totalpage = db.getcountSectors();
	        if(request.getParameter("page") != null){
	            page = Integer.parseInt(request.getParameter("page"));
	        }
	        if(totalpage %  pageSize ==0) {
	        	totalpage = totalpage/pageSize;
	        }else {
	        	totalpage = totalpage/pageSize+1;
	        }
	       
	        List<Sectors> lists=null;
			try {
				lists = db.querySector(page, pageSize);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
		        String id = (String) request.getParameter("id");
		        Sectors sector = null;
		        	try {
						sector =DBUtils.findSector(conn, id);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		     
		        	
		        	String action1  = (String) request.getParameter("action");
		        	ArrayList<Sectors> list1 = null;
					//if(action1.equals("search")) {
						String id1 = request.getParameter("txtid");
						 try {
						list1 =DBUtils.searchSector(conn, id1);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	
				//	}   
		        	
	        // Lưu thông tin vào request attribute trước khi forward sang views.
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("sector", sector);
			request.setAttribute("productList",lists);
			request.setAttribute("productList1",list1);
	        request.setAttribute("totalpage", totalpage);
	        request.setAttribute("page", page);
	        // Forward sang /WEB-INF/views/productListView.jsp
	        RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/productListView.jsp");
	        dispatcher.forward(request, response);
	    }
	 
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	        
	        request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        Connection conn = MyUtils.getStoredConnection(request);
	        String errorString = null;
	        //ArrayList<Sectors> list = null;
	        //String errorString = null;
	   
	        
	        String action  = (String) request.getParameter("action");
        	
			/*if(action1.equals("search")) {
				String id1 = request.getParameter("txtid");
				 try {
				list =DBUtils.searchSector(conn, id1);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
				 request.setAttribute("productList",list);*/

	        if("Export".equals(action)) {
	        	
				 try {
						response.setContentType("application/vnd.ms-excel");
			            response.setHeader("Content-Disposition", "attachment; filename=RoomManager.xls");
						DBUtils roomDB = new DBUtils();
						XSSFWorkbook  workbook = roomDB.exportToExcel();
						workbook.write(response.getOutputStream());
					}
					catch (Exception e) {
						// TODO: handle exception
					}	 
				 
       	} 
	        
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
	 
	     
	 
	        try {
	            DBUtils.updateSectors(conn, sector);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            errorString = e.getMessage();
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
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
	 
	}
	
	