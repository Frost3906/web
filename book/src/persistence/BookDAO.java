package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.BookVO;

public class BookDAO {
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			String url ="jdbc:oracle:thin:@localhost:1521:orcl";
			String user="javadb";
			String password="12345";
			return DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//booktbl 전체 내용 가져오기
	
	public List<BookVO> getList(){
		List<BookVO> list = new ArrayList<BookVO>();
		String sql = "select * from bookTBL";

		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()){
			
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setCode(rs.getString(1));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setPrice(rs.getInt(4));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int insertBook(BookVO vo) {
		String sql = "insert into bookTBL values(?,?,?,?)";
		int result = 0;
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, vo.getCode());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getPrice());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean deleteBook(String code) {
		String sql = "delete from bookTBL where code = ?";
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, code);
			int result = pstmt.executeUpdate();
			if(result > 0) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public int modifyBook(String code, int price) {
		String sql = "update bookTBL set price = ? where code = ?";
		int result = 0;
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, price);
			pstmt.setString(2, code);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<BookVO> searchBook(String criteria, String keyword){
		List<BookVO> search_list = new ArrayList<BookVO>();
		
		String sql = "";
		if(criteria.equals("code")) {;
			sql = "select * from bookTBL where code like ?";
		}else {
			sql = "select * from bookTBL where writer like ?";
		}
		
		
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql);
			 ){

			pstmt.setString(1, "%"+keyword+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setCode(rs.getString(1));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setPrice(rs.getInt(4));
				search_list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return search_list;
		
	}
}