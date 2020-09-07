package Items;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.glass.ui.Window.Level;
import com.sun.istack.internal.logging.Logger;

import Controller.DBUtils;
import Controller.MyUtils;
import Controller.SQLServerConnUtils_SQLJDBC;
import Model.Items;
import Model.Sectors;
import Model.thu;

public class ItemsDAO {
    Connection connection;
    Statement stmt;  
public ItemsDAO() {}
private Connection getConnection() throws SQLException,ClassNotFoundException {
    Connection con = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
    return con;
}

public int getcountItem() {
	int rows=0;
	String query = "{CALL COUNT_ITEMS}";
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

public List<thu> Viewthu(Connection conn) throws ClassNotFoundException, SQLException
{
	conn = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
	String sql = "SELECT * FROM SM_IMAGE";
	PreparedStatement pstm = conn.prepareStatement(sql);
	ResultSet rs = pstm.executeQuery();
	 List<thu> list = new ArrayList<thu>();
     while (rs.next()) {
    	 thu t = new thu();
    	 t.setUser(rs.getString("id"));
    	 t.setImage(rs.getString("images"));
         list.add(t);
     }
     return list;
}

public static void insertanh(Connection conn, thu t) throws ClassNotFoundException, SQLException {
	conn = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
	String sql = "INSERT INTO SM_IMAGE VALUES(?,?)";
	PreparedStatement pstm = conn.prepareStatement(sql);
	pstm.setString(1, t.getUser());
	pstm.setString(2, t.getImage());
	pstm.executeUpdate();
}
public List<Items> viewAllEmployees(int page,int pagesize) throws ClassNotFoundException
{
	int from = page*pagesize-(pagesize-1);
	int to = page*pagesize;
	
	ArrayList<Items> list = new ArrayList<Items>();
    String query = "{CALL GET_ITEMS(?,?)}";
    ResultSet rs ;
   
    try {
    	CallableStatement stsm = getConnection().prepareCall(query);
    	stsm.setInt(1, from);
    	stsm.setInt(2, to);
    	rs = stsm.executeQuery();
        while(rs.next()) {
        	Items item = new Items();
			item.setId(rs.getString("Ite_Id"));
			item.setName(rs.getString("Ite_Name"));
			item.setSp_id(rs.getString("Sup_Id"));
			item.setSec_id(rs.getString("Sec_Id"));
			item.setPrice(rs.getString("Ite_Price"));
			item.setUnit(rs.getString("Ite_Unit"));
			//item.setStatus(rs.getInt("Ite_Status"));
			String st = null;
			String c = rs.getString("Ite_Status");
			if(c.equals("1")) {
				st = "Còn hàng";
			}
			else
				if(c.equals("2")) {
					st = "Hết hàng";
				}
			item.setStatus(st);
			list.add(item);
        	
        	
        }
	} catch (SQLException e) {
		//Logger.getLogger(ItemsDAO.class.getName()).log(Level.SEVERE,null,e);
	}
    return list;
 
}

public static void insertItems(Connection conn, Items item) throws SQLException, ClassNotFoundException {   
	conn = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
    CallableStatement pstm = conn.prepareCall("{CALL Insert_Items(?,?,?,?,?,?,?)}");
    pstm.setString(1, item.getId());
    pstm.setString(2, item.getName());
    pstm.setString(3, item.getSp_id());
    pstm.setString(4, item.getSec_id());
    pstm.setString(5, item.getPrice());
    pstm.setString(6, item.getUnit());
    pstm.setString(7, item.getStatus());
    pstm.executeUpdate();
}

public static void deleteItems(Connection conn, String id) throws ClassNotFoundException, SQLException {
	conn = SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
	CallableStatement pstm = conn.prepareCall("{CALL Delete_Items(?)}");
	pstm.setString(1, id);
	pstm.executeUpdate();
}
}