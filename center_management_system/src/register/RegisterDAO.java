package register;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Classes;
import common.DAO;

public class RegisterDAO extends DAO{
	
	private static RegisterDAO registerDAO = null;
	private RegisterDAO() {}
	public static RegisterDAO getInstance() {
		if(registerDAO == null) registerDAO = new RegisterDAO();
		return registerDAO;
	}
	
	// 수강 신청
	public void insertRegister(int classId, int memberId) {
		try {
			connect();
			String sql = "INSERT INTO register_class VALUES (register_id_seq.NEXTVAL, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classId);
			pstmt.setInt(2, memberId);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("수강 신청이 완료되었습니다.");
			} else {
				System.out.println("수강 신청에 실패했습니다.");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	public void delete(int registerId) {
		try {
			connect();
			String sql = "DELETE FROM register_class WHERE register_id = " + registerId;
			
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println(" >> 수강 신청이 취소되었습니다 <<");
			} else {
				System.out.println(" >> 신청한 강좌가 없습니다. <<");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	// 선택한 회원의 신청 강좌 조회
	public List<Register> selectAll(int memberId) {
		List<Register> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM register_class WHERE member_id = " + memberId + " ORDER BY class_id DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Register register = new Register();
				register.setRegisterId(rs.getInt("register_id"));
				register.setClassId(rs.getInt("class_id"));
				register.setMemberId(rs.getInt("member_id"));
				
				list.add(register);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}
	
	public int selectMemberClass(int memberId, int classId) {
		int result = 0;
		try {
			connect();
			String sql = "SELECT class_id FROM register_class WHERE member_id = " + memberId + "AND class_id = " + classId;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				Classes classes = new Classes();
				classes.setClassId(rs.getInt("class_id"));
				result = classes.getClassId();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
	public int registerCapacity(int classId) {
		int result = 0;
		try {
			connect();
			String sql = "SELECT c.class_id FROM classes c LEFT OUTER JOIN (SELECT class_id, COUNT(*) AS enrollment FROM register_class GROUP BY class_id) e ON c.class_id = e.class_id WHERE c.class_capacity = e.enrollment";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				Classes classes = new Classes();
				classes.setClassId(rs.getInt("class_id"));
				result = classes.getClassId();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
}
