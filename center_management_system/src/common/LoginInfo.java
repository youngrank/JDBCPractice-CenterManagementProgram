package common;

import java.util.Scanner;

public class LoginInfo {
	Scanner scanner = new Scanner(System.in);
	// 싱글톤
	private static LoginInfo login = null;
	
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
				System.out.println("메뉴를 확인해주세요.");
			}
		}
	}
	
	public static LoginInfo getInstance() {
		if(login == null ) login = new LoginInfo();
		return login;
	}
	
	private void printMenu() {
		System.out.println(" ①로그인\t②프로그램 종료 ");
		System.out.print(" 입력>> ");
	}
	
	private int selectNum() {
		int num = 0;
		try {
			num = Integer.parseInt(scanner.nextLine());
		} catch(NumberFormatException e) {
			System.out.println("잘못된 입력입니다.");
		}
		// 사용자가 선택한 번호를 반환
		return num;
	}
	
	private void login() {
		while(true) {
			System.out.println();
			System.out.print("■    ID    : ");
			String id = scanner.nextLine();
			System.out.print("■ Password : ");
			String password = scanner.nextLine();
			
			if( id.equals("admin") && password.equals("admin")) {
				break;
			} else {
				System.out.println("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
			}
		}
		System.out.println();
		System.out.println(" >> 로그인 되었습니다 <<");
		System.out.println();
		new Management().run();		
	}
	
	
}
