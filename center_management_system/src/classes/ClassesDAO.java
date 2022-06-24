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
			String sql = "INSERT INTO classes(class_id, class_name, teacher_id, class_place, class_day, class_fee, class_capacity, class_content) "
					+ "VALUES (class_id_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classes.getClassName());
			pstmt.setInt(2, classes.getTeacherId());
			pstmt.setString(3, classes.getClassPlace());
			pstmt.setString(4, classes.getClassDay());
			pstmt.setInt(5, classes.getClassFee());
			pstmt.setInt(6, classes.getClassCapacity());
			pstmt.setString(7, classes.getClassContent());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("신규 강좌 등록이 완료되었습니다.");
			} else {
				System.out.println("신규 강좌 등록에 실패했습니다.");
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
			String sql = "SELECT * FROM classes ORDER BY class_id DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Classes classes = new Classes();
				classes.setClassId(rs.getInt("class_id"));
				classes.setClassName(rs.getString("class_name"));
				classes.setTeacherId(rs.getInt("teacher_id"));
				classes.setClassPlace(rs.getString("class_place"));
				classes.setClassDay(rs.getString("class_day"));
				classes.setClassFee(rs.getInt("class_fee"));
				classes.setClassCapacity(rs.getInt("class_capacity"));
				classes.setClassOpen(rs.getInt("class_open"));
				classes.setClassContent(rs.getString("class_content"));
				
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
			String sql = "SELECT * FROM classes WHERE class_name LIKE '%"+ name +"%' ORDER BY class_id DESC";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Classes classes = new Classes();
				classes.setClassId(rs.getInt("class_id"));
				classes.setClassName(rs.getString("class_name"));
				classes.setTeacherId(rs.getInt("teacher_id"));
				classes.setClassPlace(rs.getString("class_place"));
				classes.setClassDay(rs.getString("class_day"));
				classes.setClassFee(rs.getInt("class_fee"));
				classes.setClassCapacity(rs.getInt("class_capacity"));
				classes.setClassOpen(rs.getInt("class_open"));
				classes.setClassContent(rs.getString("class_content"));
				
				list.add(classes);
				}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	
	
}
