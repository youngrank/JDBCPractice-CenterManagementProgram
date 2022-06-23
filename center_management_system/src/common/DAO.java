package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DAO {
	// Oracle DB 정보
	private String jdbcDriver;
	private String oracleUrl;
	private String connectedId;
	private String connectedPwd;
	
	// 공통으로 사용되는 필드
	// 자식 클래스에서 사용 할 것이기 때문에, 제한자가 protected가 되어야 함.
	protected Connection conn;
	protected Statement stmt;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	public DAO() {
		dbConfig();
	}
	
	// DB에 접속하는 메소드
	public void connect() {
		try {
			Class.forName(jdbcDriver);
			
			conn = DriverManager.getConnection(oracleUrl, connectedId, connectedPwd);
			
		} catch(ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch(SQLException e) {
			System.out.println("DB 연결 실패");
		} 
	}
	
	// DB 정보를 가져오는 메소드
	private void dbConfig() {
		String resource = "Config/db.properties";
		Properties properties = new Properties();
		
		try {
			String filePath = ClassLoader.getSystemClassLoader().getResource(resource).getPath();
		properties.load(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		jdbcDriver = properties.getProperty("driver");
		oracleUrl = properties.getProperty("url");
		connectedId = properties.getProperty("id");
		connectedPwd = properties.getProperty("password");
	}
	// DB 접속을 해제하는 메소드
	public void disconnect() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
