package common;

import java.util.Scanner;

import member.MemberDAO;
import member.MemberManagement;

public class Management {
	// 필드
	protected Scanner scanner = new Scanner(System.in);
	protected MemberDAO memberDAO = MemberDAO.getInstance();
	
	// 생성자
	
	// 메소드
	public void run() {
		while(true) {
			printMenu();
			int num = selectNum();
			
			if(num == 1) {
				// 회원관리
				new MemberManagement();
			} else if (num == 2) {
				// 강좌관리
			} else if (num == 3) {
				// 강사관리
			} else if (num == 9) {
				// 로그아웃
				break;
			} else {
				// 입력 오류
			}
		}
	}
	
	protected void printMenu() {
		System.out.println("1.회원관리 2.강좌관리 3.강사관리 9.로그아웃");
	}
	
	protected int selectNum() {
		int num = 0;
		try {
			num = Integer.parseInt(scanner.nextLine());
		} catch(NumberFormatException e) {
			System.out.println("숫자를 입력해주세요.");
		}
		return num;
	}
}
