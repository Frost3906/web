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

import domain.BoardVO;

public class BoardDAO {
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
	//게시글 등록
	public int insertArticle(BoardVO vo) {
		
		String sql = "insert into board(bno,name,password,title,content,attach,re_ref,re_lev,re_seq) values(board_seq.nextval,?,?,?,?,?,board_seq.currval,?,?)";
		int result = 0;
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getAttach());
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<BoardVO> ListArticle(){
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		String sql ="select bno, title, name, regdate, readcount from board order by bno desc";
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()){
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setName(rs.getString("name"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setReadcount(rs.getInt("readcount"));
				list.add(vo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public BoardVO viewArticle(int bno) {
		
		String sql = "select bno, name, title, content, attach from board where bno = ?";
		BoardVO vo = null;
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, bno);

			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setAttach(rs.getString("attach"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	public int modifyArticle(BoardVO vo) {
		int result = 0;
		String sql = null;
		if(vo.getAttach()!=null) {
			sql = "update BOARD set title = ?, content = ?, attach = ? where password = ? and bno = ?";
			try (Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getAttach());
				pstmt.setString(4, vo.getPassword());
				pstmt.setInt(5, vo.getBno());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			sql = "update BOARD set title = ?, content = ? where password = ? and bno = ?";
			try (Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getPassword());
				pstmt.setInt(4, vo.getBno());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		return result;
	}
	
}
