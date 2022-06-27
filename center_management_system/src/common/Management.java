package common;

import java.util.Scanner;

import classes.ClassesDAO;
import classes.ClassesManagement;
import member.MemberDAO;
import member.MemberManagement;
import register.RegisterDAO;
import teacher.TeacherDAO;
import teacher.TeacherManagement;

public class Management {
	// 필드
	protected Scanner scanner = new Scanner(System.in);
	protected MemberDAO memberDAO = MemberDAO.getInstance();
	protected ClassesDAO classDAO = ClassesDAO.getInstance();
	protected TeacherDAO teacherDAO = TeacherDAO.getInstance();
	protected RegisterDAO registerDAO = RegisterDAO.getInstance();
	// 생성자
	
	// 메소드
	public void run() {
		while(true) {
			printMenu();
			int num = selectNum();
			
			if(num == 1) {
				// 회원관리
				new MemberManagement().run();
			} else if (num == 2) {
				// 강좌관리
				new ClassesManagement().run();
			} else if (num == 3) {
				// 강사관리
				new TeacherManagement().run();
			} else if (num == 9) {
				// 로그아웃
				break;
			} else {
				// 입력 오류
			}
		}
	}
	
	protected void printMenu() {
		System.out.println("①회원관리 ②강좌관리 ③강사관리 ⑨로그아웃");
	}
	
	protected int selectNum() {
		int num = 0;
		try {
			System.out.print(" 선택>> ");
			num = Integer.parseInt(scanner.nextLine());
		} catch(NumberFormatException e) {
			System.out.println("숫자를 입력해주세요.");
		}
		return num;
	}
}
