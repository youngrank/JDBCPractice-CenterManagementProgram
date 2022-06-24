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
				System.out.println("신규 강사 등록이 완료되었습니다.");
			} else {
				System.out.println("신규 깅사 등록에 실패했습니다.");
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
			String sql = "SELECT * FROM teachers ORDER BY teacher_name";
			
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
	
	// 강사 검색
	public List<Teacher> search(String name) {
		List<Teacher> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM teachers WHERE teacher_name = '" + name + "' ORDER BY teacher_id DESC";
			
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
	
	
}
