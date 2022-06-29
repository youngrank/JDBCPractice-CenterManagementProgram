package common;

import java.util.Scanner;

public class LoginInfo {
	Scanner scanner = new Scanner(System.in);
	// 싱글톤
	private static Login login = null;
	public static Login getInstance() {
//		if(login == null ) login = new LoginInfo();
		return login;
	}
	
	public LoginInfo() {
		while(true) {
			printMenu();
			
			int num = selectNum();
			if(num == 1) {
				// 로그인
				login();
			} else if(num == 2) {
				// 프로그램 종료
				System.out.println();
				System.out.println(" >> 프로그램이 종료되었습니다 <<");
				break;
			} else {
				System.out.println();
				System.out.println(" >> 메뉴를 확인해주세요 <<");
			}
		}
	}
	
	private void printMenu() {
		System.out.println();
		System.out.println("====================");
		System.out.println(" ①로그인\t②프로그램 종료 ");
		System.out.println("====================");
		System.out.print(" 선택>> ");
	}
	
	private int selectNum() {
		int num = 0;
		try {
			num = Integer.parseInt(scanner.nextLine());
		} catch(NumberFormatException e) {
			System.out.println("  >> 잘못된 입력입니다 <<");
		}
		// 사용자가 선택한 번호를 반환
		return num;
	}
	
	private void login() {
		while(true) {
			Login loginInfo = inputInfo();
			login = LoginDAO.getInstance().selectOne(loginInfo);
			if(login == null) {
				System.out.println(" >> 아이디 또는 비밀번호를 잘못 입력하셨습니다 <<");
			} else	break;
		}
		new Management().run();		
	}
	
	private Login inputInfo() {
		Login info = new Login();
		
		System.out.println();
		System.out.print("■    ID    : ");
		info.setLoginId(scanner.nextLine());
		System.out.print("■ Password : ");
		info.setLoginPwd(scanner.nextLine());
		
		return info;
	}
	
	
}
