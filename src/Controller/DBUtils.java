package Controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.javafx.scene.layout.region.Margins.Converter;

import Model.Items;
import Model.Sectors;
import Model.UserAccount;
public class DBUtils {
	Connection connection;
    Statement stmt;
    private  int noOfRecords;
    
	private Connection getConnection() throws SQLException,ClassNotFoundException {
	    Connection con = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
	    return con;
	}

	public int getcountSectors() {
		int rows=0;
		String query = "{CALL COUNT_SECTORS}";
		ResultSet rs;
		try {
			CallableStatement stsm = getConnection().prepareCall(query);
			rs = stsm.executeQuery();
			if(rs.next()) {
				rows = rs.getInt("TOTAL");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			
		}
		return rows;
	}

	public static UserAccount findUser(Connection conn, 
            String userName, String password) throws SQLException, ClassNotFoundException {
        conn = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
        CallableStatement pstm = conn.prepareCall("{call SELECT_STAFFS(?,?)}");
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            return user;
        }
        return null;
    }
 
    public List<Sectors> querySector(int page,int pagesize)throws ClassNotFoundException{
         
    	int from = page*pagesize-(pagesize-1);
    	int to = page*pagesize;
    	
    	ArrayList<Sectors> list = new ArrayList<Sectors>();
        String query = "{CALL GET_SECTORS(?,?)}";
        ResultSet rs ;
        
        try {
        	CallableStatement stsm = getConnection().prepareCall(query);
        	stsm.setInt(1, from);
        	stsm.setInt(2, to);
        	rs = stsm.executeQuery();
            while (rs.next()) {        
                Sectors sec = new Sectors(); 
    			sec.setId(rs.getString("Sec_Id"));
                sec.setName(rs.getString("Sec_Name"));
                sec.setImage(rs.getString("Sec_Image"));
                //sec.setStatus(rs.getInt("Sec_Status"));
                String chuoi=null;
                
                String ad = rs.getString("Sec_Status");
                if(ad.equals("1")) {
                	chuoi = "Còn hàng";
                }
                else
                	if(ad.equals("2")) {
                		chuoi = "Hết hàng";
                	}
                //int cd = Integer.parseInt(chuoi);
                sec.setStatus(chuoi);
                list.add(sec);
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        return list;
    }
    
    public static Sectors findSector(Connection conn, String id) throws SQLException, ClassNotFoundException{
    	conn = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
        CallableStatement pstm = conn.prepareCall("{ CALL SELECT_SECTORS_ID(?)}");
        pstm.setString(1,id);
        ResultSet rs = pstm.executeQuery();
        //Sectors sec = new Sectors();
        while (rs.next()) {
            //String Id = rs.getString("Sec_Id");
            String Name= rs.getString("Sec_Name");
            String Image = rs.getString("Sec_Image");
            String Status = rs.getString("Sec_Status");
            Sectors sec = new Sectors(id,Name,Image,Status);
            return sec;
        }
		return null;
    }
    
    public static ArrayList<Sectors> searchSector(Connection conn, String id) throws ClassNotFoundException, SQLException {
    	conn = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
    	CallableStatement pstm = conn.prepareCall("{ CALL SELECT_SECTORS_ALL(?)}");
        pstm.setString(1,id);
        ResultSet rs = pstm.executeQuery();
        ArrayList<Sectors> list = new ArrayList<Sectors>();        
        while (rs.next()) {
        	Sectors sector = new Sectors();
            sector.setId(rs.getString("Sec_Id"));
            sector.setName(rs.getString("Sec_Name"));
            sector.setImage(rs.getString("Sec_Image"));
            String st = null;
			String c = rs.getString("Sec_Status");
			if(c.equals("1")) {
				st = "Còn hàng";
			}
			else
				if(c.equals("2")) {
					st = "Hết hàng";
				}
			sector.setStatus(st);
            //sector.setStatus(rs.getString("Sec_Status"));
            list.add(sector);
        }
		return list;
    }
    
    public static Items findItems(Connection conn, String id) throws ClassNotFoundException, SQLException {
    	conn = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
    	CallableStatement pstm = conn.prepareCall("{SELECT_ITEMS_ID(?)}");
    	pstm.setString(1, id);
    	ResultSet rs = pstm.executeQuery();
    	while(rs.next()) {
    		String Id = rs.getString("Ite_Id");
    		String Name = rs.getString("Ite_Name");
    		String Sp_Id = rs.getString("Sup_Id");
    		String Sec_Id = rs.getString("Sec_Id");
    		String Price = rs.getString("Ite_Price");
    		String Unit = rs.getString("Ite_Unit");	
    		String Status = rs.getString("Ite_Status");
    		
    		Items item = new Items(Id,Name,Sp_Id,Sec_Id,Unit,Price,Status);
    		return item;
    	}
    	return null;
    }

    public static void updateSectors(Connection conn, Sectors sector) throws SQLException, ClassNotFoundException {
    	conn = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
        CallableStatement pstm = conn.prepareCall("{call Update_Sectors(?,?,?,?)}");
        pstm.setString(1, sector.getId());
        pstm.setString(2, sector.getName());
        pstm.setString(3, sector.getImage());
        pstm.setString(4, sector.getStatus());
        pstm.executeUpdate();
    }
 
    public static void insertSectors(Connection conn, Sectors sector) throws SQLException, ClassNotFoundException {   
    	conn = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
        CallableStatement pstm = conn.prepareCall("{CALL Insert_Sectors(?,?,?,?)}");
        pstm.setString(1, sector.getId());
        pstm.setString(2, sector.getName());
        pstm.setString(3, sector.getImage());
        pstm.setString(4, sector.getStatus());
 
        pstm.executeUpdate();
    }
 
    public static void deleteSectors(Connection conn, String id) throws SQLException, ClassNotFoundException {
         conn = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
         CallableStatement pstm = conn.prepareCall("{call Delete_Sectors(?)}");
        pstm.setString(1, id);
        pstm.executeUpdate();
    }
    
    public XSSFWorkbook exportToExcel() {
		ArrayList<Sectors> rooms = new ArrayList<Sectors>(); 
			
		XSSFWorkbook  workbook = new XSSFWorkbook();
		XSSFSheet  sheet = workbook.createSheet("Room sheet"); 
		// Create a CellStyle with the font  
		CellStyle headerCellStyle = createStyleForHeader(workbook);
		CellStyle titleCellStyle = createStyleForTitle(workbook);

		        int rownum = 1;
		        Cell cell;
		        Row row;
		        Row titleRow;
		        Row signRow;
		        titleRow = sheet.createRow(0);
		        cell = titleRow.createCell(0);
		        cell.setCellValue("Hotel Room Management");
		        cell.setCellStyle(titleCellStyle);
		        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		        
		        row = sheet.createRow(rownum);
		        
		        // EmpNo
		        cell = row.createCell(0);
		        cell.setCellValue("Sec_Id");
		        cell.setCellStyle(headerCellStyle);
		        
		        // EmpName
		        cell = row.createCell(1);
		        cell.setCellValue("Sec_Name");
		        cell.setCellStyle(headerCellStyle);
		        // Salary
		        cell = row.createCell(2);
		        cell.setCellValue("Sec_Status");
		        cell.setCellStyle(headerCellStyle);
		        
		        for (Sectors r : rooms) {
		            rownum++;
		            row = sheet.createRow(rownum);
		 
		            // EmpNo (A)
		            cell = row.createCell(0);
		            cell.setCellValue(r.getId());
		            // EmpName (B)
		            cell = row.createCell(1);
		            cell.setCellValue(r.getName());
		            // Salary (C)
		            cell.setCellValue(r.getStatus());  
		        }
		        
		        titleRow = sheet.createRow(rownum+2);
		        cell = titleRow.createCell(5);
		        cell.setCellValue("Signature");
		        cell.setCellStyle(titleCellStyle);
		        for(int i=0;i<=5;i++) {
		        	sheet.autoSizeColumn(i);
		        }
		        
		        System.out.println("number of row"+rownum);
//				try {
//					File file = new File("C:/demo/RoomManagement.xls");
//			        file.getParentFile().mkdirs();
//			 
//			        FileOutputStream outFile = new FileOutputStream(file);
//			        workbook.write(outFile);
//
//			        // Closing the workbook  
//			        workbook.close();
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}  
		        
		     return workbook;  
		 
	}
	
	private  CellStyle createStyleForHeader(XSSFWorkbook workbook) {
		Font font = workbook.createFont();
		font.setBold(true);  
		font.setFontHeightInPoints((short) 13);  
		font.setColor(IndexedColors.BLUE.getIndex());
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
	
	private  CellStyle createStyleForTitle(XSSFWorkbook workbook) {
		Font font = workbook.createFont();
		font.setBold(true);  
		font.setFontHeightInPoints((short) 15);  
		font.setColor(IndexedColors.AQUA.getIndex());
		
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        return style;
    }
	
}