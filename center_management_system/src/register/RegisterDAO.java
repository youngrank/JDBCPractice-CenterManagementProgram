package register;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
				System.out.println("수강 신청이 취소되었습니다.");
			} else {
				System.out.println("수강 신청 취소가 실패하였습니다.");
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
	
}
