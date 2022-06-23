package member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
				System.out.println("신규 회원이 등록되었습니다.");
			} else {
				System.out.println("신규 회원 등록에 실패했습니다.");
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
			String sql = "SELECT * FROM members ORDER BY member_id DESC";
			
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
	
	// 회원 검색
	public List<Member> search(String name) {
		List<Member> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM members WHERE member_name = '" + name + "' ORDER BY member_id DESC";
			
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
}
