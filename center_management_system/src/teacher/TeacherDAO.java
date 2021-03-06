package teacher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class TeacherDAO extends DAO{
	// 싱글톤
	private static TeacherDAO teacherDAO = null;
	private TeacherDAO() {}
	public static TeacherDAO getInstance() {
		if(teacherDAO == null) teacherDAO = new TeacherDAO();
		return teacherDAO;
	}
	
	// 신규 강사 등록
	public void insertTeacher(Teacher teacher) {
		try {
			connect();
			String sql = "INSERT INTO teachers VALUES (teacher_id_seq.NEXTVAL, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getTeacherName());
			pstmt.setString(2, teacher.getGender());
			pstmt.setString(3, teacher.getBirthDate());
			pstmt.setString(4, teacher.getAddress());
			pstmt.setString(5, teacher.getPhoneNumber());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println(" >> 신규 강사 등록이 완료되었습니다 <<");
			} else {
				System.out.println(" >> 신규 강사 등록에 실패했습니다 <<");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	// 전체 강사 조회
	public List<Teacher> selectAll() {
		List<Teacher> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT teacher_id, teacher_name, gender, TO_CHAR(birth_date, 'yy/mm/dd') AS birth_date "
					+ "FROM teachers ORDER BY teacher_id DESC";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setTeacherId(rs.getInt("teacher_id"));
				teacher.setTeacherName(rs.getString("teacher_name"));
				teacher.setGender(rs.getString("gender"));
				teacher.setBirthDate(rs.getString("birth_date"));
				
				list.add(teacher);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list; 
	}
	
	// 강사 검색
	public List<Teacher> search(String name) {
		List<Teacher> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT teacher_id, teacher_name, gender, TO_CHAR(birth_date, 'yy/mm/dd') AS birth_date, address, phone_number "
					+ "FROM teachers WHERE teacher_name = '" + name + "' ORDER BY teacher_id DESC";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setTeacherId(rs.getInt("teacher_id"));
				teacher.setTeacherName(rs.getString("teacher_name"));
				teacher.setGender(rs.getString("gender"));
				teacher.setBirthDate(rs.getString("birth_date"));
				teacher.setAddress(rs.getString("address"));
				teacher.setPhoneNumber(rs.getString("phone_number"));
				
				list.add(teacher);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	public void updateAddress(int teacherId, String address) {
		try {
			connect();
			String sql = "UPDATE teachers SET address = '" + address + "' WHERE teacher_id = " + teacherId;
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println(" >> 정상적으로 수정되었습니다 <<");
			} else {
				System.out.println("수정 실패");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	public void updatePhoneNumber(int teacherId, String phoneNumber) {
		try {
			connect();
			String sql = "UPDATE teachers SET phone_number = '" + phoneNumber + "' WHERE teacher_id = " + teacherId;
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println(" >> 정상적으로 수정되었습니다 <<");
			} else {
				System.out.println("수정 실패");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	public void delete(int teacherId) {
		try {
			connect();
			String sql = "DELETE FROM teachers WHERE teacher_id = " + teacherId;
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println("강사 삭제가 완료되었습니다.");
			} else {
				System.out.println("강사 삭제에 실패하였습니다.");
			}
		} catch(SQLException e) {
			System.out.println(" >> 해당 강사의 강의 내역을 먼저 삭제해주세요 <<");
//			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	
}
