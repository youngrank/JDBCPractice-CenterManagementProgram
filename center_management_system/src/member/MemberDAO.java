package member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Classes;
import common.DAO;

public class MemberDAO extends DAO{
	// 싱글톤
	private static MemberDAO memberDAO = null;
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if(memberDAO == null) memberDAO = new MemberDAO();
		return memberDAO;
	}
	
	// 신규 회원 등록
	public void insertMember(Member member) {
		try {
			connect();
			String sql = "INSERT INTO members VALUES (member_id_seq.NEXTVAL, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getGender());
			pstmt.setString(3, member.getBirthDate());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getPhoneNumber());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println();
				System.out.println(" >> 신규 회원이 등록되었습니다 <<");
			} else {
				System.out.println();
				System.out.println(" >> 신규 회원 등록에 실패했습니다 <<");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	// 전체 회원 조회
	public List<Member> selectAll() {
		List<Member> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT member_id, member_name, gender, TO_CHAR(birth_date, 'yy/mm/dd') AS birth_date "
					+ "FROM members ORDER BY member_id DESC";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getInt("member_id"));
				member.setMemberName(rs.getString("member_name"));
				member.setGender(rs.getString("gender"));
				member.setBirthDate(rs.getString("birth_date"));
				
				list.add(member);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 회원 검색
	public List<Member> search(String name) {
		List<Member> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT member_id, member_name, gender, TO_CHAR(birth_date, 'yy/mm/dd') AS birth_date, address, phone_number "
					+ "FROM members WHERE member_name = '" + name + "' ORDER BY member_id DESC";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getInt("member_id"));
				member.setMemberName(rs.getString("member_name"));
				member.setGender(rs.getString("gender"));
				member.setBirthDate(rs.getString("birth_date"));
				member.setAddress(rs.getString("address"));
				member.setPhoneNumber(rs.getString("phone_number"));
				list.add(member);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}
	
	public void updateAddress(int memberId, String address) {
		try {
			connect();
			String sql = "UPDATE members SET address = '" + address + "' WHERE member_id = " + memberId;
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println("");
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
	
	public void updatePhoneNumber(int memberId, String phoneNumber) {
		try {
			connect();
			String sql = "UPDATE members SET phone_number = '" + phoneNumber + "' WHERE member_id = " + memberId;
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println();
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
	
	public void delete(int memberId) {
		try {
			connect();
			String sql = "DELETE FROM members WHERE member_id = " + memberId;
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println(" >> 회원 삭제가 완료되었습니다 <<");
			} else {
				System.out.println(" >> 회원 삭제에 실패하였습니다 <<");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	public List<Classes> registerClass(int memberId) {
		List<Classes> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT register_id, class_name, teacher_name, class_place, class_day "
					+ "FROM classes JOIN register_class USING(class_id) "
					+ "JOIN teachers USING(teacher_id) WHERE member_id = " + memberId;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Classes classes = new Classes();
				classes.setRegisterId(rs.getInt("register_id"));
				classes.setClassName(rs.getString("class_name"));
				classes.setTeacherName(rs.getString("teacher_name"));
				classes.setClassPlace(rs.getString("class_place"));
				classes.setClassDay(rs.getString("class_day"));
				
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
