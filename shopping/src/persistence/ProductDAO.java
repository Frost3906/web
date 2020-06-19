package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.ProductVO;

public class ProductDAO {
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public List<ProductVO> getList(){
		List<ProductVO> list = new ArrayList<ProductVO>();
		String sql = "select pno,category,name,price,amount,regdate from product";
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setPno(rs.getInt("pno"));
				vo.setCategory(rs.getString("category"));
				vo.setName(rs.getString("name"));
				vo.setPrice(rs.getInt("price"));
				vo.setAmount(rs.getInt("amount"));
				vo.setRegdate(rs.getDate("regdate"));
				list.add(vo);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public int insertProduct(ProductVO vo) {
		int result = 0;
		
		String sql = "insert into product(pno,category,name,price,color,amount,psize,content) values(prod_seq.nextval,?,?,?,?,?,?,?)";
		
		try(Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
	
			pstmt.setString(1, vo.getCategory());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getPrice());
			pstmt.setString(4, vo.getColor());
			pstmt.setInt(5, vo.getAmount());
			pstmt.setString(6, vo.getPsize());
			pstmt.setString(7, vo.getContent());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
