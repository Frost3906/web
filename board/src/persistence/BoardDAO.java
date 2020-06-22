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
		
		String sql ="select bno, title, name, regdate, readcount, re_lev from board order by re_ref desc, re_seq asc";
		
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
				vo.setRe_lev(rs.getInt("re_lev"));
				list.add(vo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public BoardVO viewArticle(int bno) {
		
		String sql = "select bno, name, title, content, attach, re_ref, re_seq, re_lev from board where bno = ?";
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

				//댓글작업 추가분
				vo.setRe_ref(rs.getInt("re_ref"));
				vo.setRe_seq(rs.getInt("re_seq"));
				vo.setRe_lev(rs.getInt("re_lev"));
		
			
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
	//조회수 업데이트
	public int hitUpdate(int bno) {
		String sql = "update board set readcount = readcount + 1 where bno = ?";
		int result = 0;
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, bno);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteArticle(int bno, String password) {
		int result = 0;
		String sql = "delete from board where bno = ? and password = ?";
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql)){
		
			pstmt.setInt(1, bno);
			pstmt.setString(2, password);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int replyArticle(BoardVO vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
				
		int re_ref = vo.getRe_ref();
		int re_seq = vo.getRe_seq();
		int re_lev = vo.getRe_lev();
		
		
		try {
			con = getConnection();
			
			//댓글 삽입 전 현재 원본글에 달려있는 
			//댓글들의 re_seq 값 변경하기
			String sql = "update board set re_seq = re_seq + 1";
			sql += "where re_ref = ? and re_seq > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			result = pstmt.executeUpdate();
			
			if(!pstmt.isClosed()) {
				pstmt.close();
			}
			sql = "insert into board(bno,name,password,title,content,attach,re_ref,re_lev,re_seq) values(board_seq.nextval,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getAttach());
			pstmt.setInt(6, re_ref); //원본글의 re_ref와 동일
			pstmt.setInt(7, re_lev+1); //원본글의 re_lev와 동일ㄹ
			pstmt.setInt(8, re_seq+1); // 원본글의 re_seq와 동일
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
				if(!con.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public List<BoardVO> SearchArticle(String criteria, String keyword) {
		List<BoardVO> search = new ArrayList<BoardVO>();
		
		String sql = "select bno, title, name, regdate, readcount, re_lev from board where "+criteria+" like ? order by re_ref desc, re_seq asc";
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, "%"+keyword+"%");
			ResultSet rs = pstmt.executeQuery();
		
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setName(rs.getString("name"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setRe_lev(rs.getInt("re_lev"));
				search.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return search;
	}
	
}
