package common;

import java.sql.SQLException;

public class LoginDAO extends DAO{
	// 싱글톤
	private static LoginDAO loginDAO = null;
	private LoginDAO() {}
	public static LoginDAO getInstance() {
		if(loginDAO == null) loginDAO = new LoginDAO();
		return loginDAO;
	}
	
	public Login selectOne(Login loginInfo) {
		Login login = null;
		try {
			connect();
			String sql = "SELECT * FROM login "
					+ "WHERE login_id = '" + loginInfo.getLoginId() + "' AND login_password = '" + loginInfo.getLoginPwd() + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				if(rs.getString("login_id").equals(loginInfo.getLoginId()) && rs.getString("login_password").equals(loginInfo.getLoginPwd())) {
					login = new Login();
					login.setLoginId(rs.getString("login_id"));
					login.setLoginPwd(rs.getString("login_password"));
					
					System.out.println();
					System.out.println(" >> 로그인 되었습니다 <<");
				} 
			} 
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return login;
	}
}
