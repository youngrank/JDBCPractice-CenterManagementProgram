package classes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class ClassesDAO extends DAO{
	// 싱글톤
	private static ClassesDAO classesDAO = null;
	private ClassesDAO() {}
	public static ClassesDAO getInstance() {
		if(classesDAO == null) classesDAO = new ClassesDAO();
		return classesDAO;
	}
	
	// 신규 강좌 등록
	public void insertClass(Classes classes) {
		try {
			connect();
			String sql = "INSERT INTO classes(class_id, class_name, teacher_id, class_place, class_day, class_fee, class_capacity) "
					+ "VALUES (class_id_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classes.getClassName());
			pstmt.setInt(2, classes.getTeacherId());
			pstmt.setString(3, classes.getClassPlace());
			pstmt.setString(4, classes.getClassDay());
			pstmt.setInt(5, classes.getClassFee());
			pstmt.setInt(6, classes.getClassCapacity());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println(" >> 신규 강좌 등록이 완료되었습니다 <<");
			} else {
				System.out.println(" >> 신규 강좌 등록에 실패했습니다 <<");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	// 전체 강좌 조회
	public List<Classes> selectAll() {
		List<Classes> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT c.class_id, c.class_name, teacher_name, c.class_place, c.class_day, c.class_fee, c.class_capacity, NVL(e.enrollment, 0) AS enrollment "
					+ "FROM classes c JOIN teachers USING (teacher_id) LEFT OUTER JOIN (SELECT class_id, COUNT(*) AS enrollment "
					+ "FROM register_class GROUP BY class_id) e ON c.class_id = e.class_id ORDER BY class_id DESC";
//			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Classes classes = new Classes();
				classes.setClassId(rs.getInt("class_id"));
				classes.setClassName(rs.getString("class_name"));
				classes.setTeacherName(rs.getString("teacher_name"));
				classes.setClassPlace(rs.getString("class_place"));
				classes.setClassDay(rs.getString("class_day"));
				classes.setClassFee(rs.getInt("class_fee"));
				classes.setClassCapacity(rs.getInt("class_capacity"));
				classes.setEnrollment(rs.getInt("enrollment"));
				list.add(classes);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}
	
	// 강사의 강의 내역 조회
	public List<Classes> selectAll(int teacherId) {
		List<Classes> list = new ArrayList<>();
		try {
			connect();
			// 강의번호 강좌명 장소 요일 정원 등록인원
			String sql = "SELECT c.class_id, c.class_name, c.class_place, c.class_day, c.class_capacity, NVL(e.enrollment, 0) AS enrollment "
					+ "FROM classes c LEFT OUTER JOIN (SELECT class_id, COUNT(*) AS enrollment "
					+ "FROM register_class GROUP BY class_id) e ON c.class_id = e.class_id "
					+ "WHERE c.teacher_id = '" + teacherId + "' ORDER BY c.class_id DESC";
//			String sql = "SELECT class_id, class_name, class_place, class_day, class_capacity "
//					+ "FROM classes WHERE teacher_id = '" + teacherId + "' ORDER BY class_id DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Classes classes = new Classes();
				classes.setClassId(rs.getInt("class_id"));
				classes.setClassName(rs.getString("class_name"));
				classes.setClassPlace(rs.getString("class_place"));
				classes.setClassDay(rs.getString("class_day"));
				classes.setClassCapacity(rs.getInt("class_capacity"));
				classes.setEnrollment(rs.getInt("enrollment"));
				
				list.add(classes);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}
	
	// 특정 강좌 검색
	public List<Classes> search(String name) {
		List<Classes> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT c.class_id, c.class_name, teacher_name, c.class_place, c.class_day, c.class_fee, c.class_capacity, NVL(e.enrollment, 0) AS enrollment "
					+ "FROM classes c JOIN teachers USING (teacher_id) LEFT OUTER JOIN (SELECT class_id, COUNT(*) AS enrollment "
					+ "FROM register_class GROUP BY class_id) e ON c.class_id = e.class_id "
					+ "WHERE class_name LIKE '%" + name + "%' ORDER BY class_id DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Classes classes = new Classes();
				classes.setClassId(rs.getInt("class_id"));
				classes.setClassName(rs.getString("class_name"));
				classes.setTeacherName(rs.getString("teacher_name"));
				classes.setClassPlace(rs.getString("class_place"));
				classes.setClassDay(rs.getString("class_day"));
				classes.setClassFee(rs.getInt("class_fee"));
				classes.setClassCapacity(rs.getInt("class_capacity"));
				classes.setEnrollment(rs.getInt("enrollment"));
				list.add(classes);
				}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	public void update(int classId, String column, String str) {
		try {
			connect();
			String sql = "UPDATE classes SET " + column + " = " + str + " WHERE class_id = " + classId;
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println(" >> 정상적으로 수정되었습니다 <<");
			} else {
				System.out.println(" >> 수정에 실패하였습니다 <<");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	public void delete(int classId) {
		try {
			connect();
			String sql = "DELETE FROM classes WHERE " + classId + " NOT IN (SELECT class_id FROM register_class)";
//			String sql = "DELETE FROM classes WHERE class_id = " + classId;
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println(" >> 강좌 삭제가 완료되었습니다 <<");
			} else {
//				System.out.println(" >> 강좌 삭제에 실패하였습니다 <<");
				System.out.println(" >> 등록 인원이 있으므로 삭제할 수 없습니다 << ");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	
	
	
}
