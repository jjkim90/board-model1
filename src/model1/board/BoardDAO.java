package model1.board;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.servlet.ServletContext;
import common.JDBConnect;

public class BoardDAO extends JDBConnect {
    public BoardDAO(ServletContext application) {
        super(application);
    }

    public int selectCount(Map<String, Object> map) {
        int totalCount = 0;

        String query = "SELECT COUNT(*) FROM board";
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%'";
        }

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            totalCount = rs.getInt(1);
        }
        catch (Exception e) {
            System.out.println("�Խù� ���� ���ϴ� �� ���� �߻�");
            e.printStackTrace();
        }

        return totalCount; 
    }
    
    public List<BoardDTO> selectList(Map<String, Object> map) { 
        List<BoardDTO> bbs = new Vector<BoardDTO>();

        String query = "SELECT * FROM board "; 
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }
        query += " ORDER BY num DESC "; 

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                BoardDTO dto = new BoardDTO(); 

                dto.setNum(rs.getString("num"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setId(rs.getString("id"));
                dto.setVisitcount(rs.getString("visitcount"));

                bbs.add(dto);
            }
        } 
        catch (Exception e) {
            System.out.println("�Խù� ��ȸ �� ���� �߻�");
            e.printStackTrace();
        }

        return bbs;
    }
    
    public int insertWrite(BoardDTO dto) {
        int result = 0;
        
        try {
            String query = "INSERT INTO board ( "
                         + " num,title,content,id,visitcount) "
                         + " VALUES ( "
                         + " seq_board_num.NEXTVAL, ?, ?, ?, 0)";  

            psmt = con.prepareStatement(query); 
            psmt.setString(1, dto.getTitle());  
            psmt.setString(2, dto.getContent());
            psmt.setString(3, dto.getId());  
            
            result = psmt.executeUpdate(); 
        }
        catch (Exception e) {
            System.out.println("�Խù� �Է� �� ���� �߻�");
            e.printStackTrace();
        }
        
        return result;
    }

    public BoardDTO selectView(String num) { 
        BoardDTO dto = new BoardDTO();
        
        String query = "SELECT B.*, M.name " 
                     + " FROM member M INNER JOIN board B " 
                     + " ON M.id=B.id "
                     + " WHERE num=?";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, num); 
            rs = psmt.executeQuery(); 

            if (rs.next()) {
                dto.setNum(rs.getString(1)); 
                dto.setTitle(rs.getString(2));
                dto.setContent(rs.getString("content"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setId(rs.getString("id"));
                dto.setVisitcount(rs.getString(6));
                dto.setName(rs.getString("name")); 
            }
        } 
        catch (Exception e) {
            System.out.println("�Խù� �󼼺��� �� ���� �߻�");
            e.printStackTrace();
        }
        
        return dto; 
    }

    public void updateVisitCount(String num) { 
        String query = "UPDATE board SET "
                     + " visitcount=visitcount+1 "
                     + " WHERE num=?";
        
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, num); 
            psmt.executeQuery(); 
        } 
        catch (Exception e) {
            System.out.println("�Խù� ��ȸ�� ���� �� ���� �߻�");
            e.printStackTrace();
        }
    }
    
    public int updateEdit(BoardDTO dto) { 
        int result = 0;
        
        try {
            String query = "UPDATE board SET "
                         + " title=?, content=? "
                         + " WHERE num=?";
            
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getTitle());
            psmt.setString(2, dto.getContent());
            psmt.setString(3, dto.getNum());
            
            result = psmt.executeUpdate();
        } 
        catch (Exception e) {
            System.out.println("�Խù� ���� �� ���� �߻�");
            e.printStackTrace();
        }
        
        return result; 
    }

    public int deletePost(BoardDTO dto) { 
        int result = 0;

        try {
            String query = "DELETE FROM board WHERE num=?"; 

            psmt = con.prepareStatement(query); 
            psmt.setString(1, dto.getNum()); 

            result = psmt.executeUpdate(); 
        } 
        catch (Exception e) {
            System.out.println("�Խù� ���� �� ���� �߻�");
            e.printStackTrace();
        }
        
        return result;
    }
    
    public int insertMember(BoardDTO dto) {
        int result = 0;
        
        try {
            String query = "INSERT INTO member ( "
                         + " id,pass,name) "
                         + " VALUES ( "
                         + " ?, ?, ?)";  

            psmt = con.prepareStatement(query);  // ���� ���� 
            psmt.setString(1, dto.getId());  
            psmt.setString(2, dto.getPass());
            psmt.setString(3, dto.getName());  
            
            result = psmt.executeUpdate(); 
        }
        catch (Exception e) {
            System.out.println("�Խù� �Է� �� ���� �߻�");
            e.printStackTrace();
        }
        
        return result;
    }
    
    public List<BoardDTO> selectMemberList(Map<String, Object> map) { 
        List<BoardDTO> bbs = new Vector<BoardDTO>();  // ���(�Խù� ���)�� ���� ����

        String query = "SELECT * FROM member "; 
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }
        query += " ORDER BY regidate DESC "; 

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                BoardDTO dto = new BoardDTO(); 

                dto.setId(rs.getString("id"));
                dto.setPass(rs.getString("pass"));
                dto.setName(rs.getString("name"));
                dto.setRegidate(rs.getDate("regidate"));

                bbs.add(dto);
            }
        } 
        catch (Exception e) {
            System.out.println("�Խù� ��ȸ �� ���� �߻�");
            e.printStackTrace();
        }

        return bbs;
    }

    public BoardDTO selectMember(String id) { 
        BoardDTO dto = new BoardDTO();
        
        String query = "SELECT * FROM member " 
                     + " WHERE id=?";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, id); 
            rs = psmt.executeQuery(); 

            if (rs.next()) {
                dto.setId(rs.getString("id")); 
                dto.setPass(rs.getString("pass"));
                dto.setName(rs.getString("name"));
                dto.setRegidate(rs.getDate("regidate"));
            }
        } 
        catch (Exception e) {
            System.out.println("ȸ�� ���� ���� �� ���� �߻�");
            e.printStackTrace();
        }
        
        return dto; 
    }
    
    
    public int deleteMember (BoardDTO dto) { 
        int result = 0;

        try {
            String query = "DELETE FROM member WHERE id=?"; 

            psmt = con.prepareStatement(query); 
            psmt.setString(1, dto.getId()); 

            result = psmt.executeUpdate(); 
        } 
        catch (Exception e) {
            System.out.println("�Խù� ���� �� ���� �߻�");
            e.printStackTrace();
        }
        
        return result;
    }
}
