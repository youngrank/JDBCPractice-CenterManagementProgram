package classes;

import common.Management;

public class ClassesModification extends Management{

	public void run(int classId) {
		while(true) {
			printMenu();
			
			int num = selectNum();
			if(num == 1) {
				// 강좌 정보 수정
				selectUpdate(classId);
			} else if(num == 2) {
				// 강좌 삭제
				delete(classId);
			} else if(num == 9) {
				// 뒤로가기
				break;
			} else {
				System.out.println("inputerror");
			}
		}
	}
	
	@Override
	protected void printMenu() {
		System.out.println();
		System.out.println("1.강좌정보수정 2.강좌삭제 9.뒤로가기");
	}
	
	private void selectUpdate(int classId) {
		int num = 0;
		String input = "";
		String column = "";
		
		System.out.println("수정할 정보를 선택하세요.");
		System.out.println("1.강사번호 2.수업장소 3.수업시간 4.금액 5.개설여부 9.뒤로가기");
		System.out.print("선택> ");
		num = Integer.parseInt(scanner.nextLine());
		
		if(num == 1) {
			column = "teacher_id";
			
			System.out.println("강사번호를 입력하세요> ");
			input = scanner.nextLine();
			classDAO.update(classId, column, input);
		} else if(num == 2) {
			column = "class_place";
			
			System.out.println("수업장소를 입력하세요> ");
			input = "'" + scanner.nextLine() + "'";
			classDAO.update(classId, column, input);
		} else if(num == 3) {
			column = "class_day";
			
			System.out.println("수업시간을 입력하세요> ");
			input = "'" + scanner.nextLine() + "'";
			classDAO.update(classId, column, input);
		} else if(num == 4) {
			column = "class_fee";
			
			System.out.println("금액을 입력하세요> ");
			input = scanner.nextLine();
			classDAO.update(classId, column, input);
		} else if(num == 5) {
			column = "class_open";
			
			System.out.println("개설여부를 입력하세요> ");
			input = "'" + scanner.nextLine() + "'";
			classDAO.update(classId, column, input);
		} else if(num == 9){
			// 뒤로가기
			return;
		} else {
			System.out.println("input error");
		}
	}

	
	private void delete(int classId) {
		String answer;
		while(true) {
			System.out.print("정말로 삭제하시겠습니까? [Y/N] : ");
			answer = scanner.nextLine();
			if(answer.equals("Y")) {
				classDAO.delete(classId);	// 삭제 완료 후 이전 화면이 아닌 MemberManagement 화면으로 넘어가게 수정해야함
				break;
			} else if(answer.equals("N")) {
				System.out.println("이전 화면으로 돌아갑니다.");
				return;
			} else {
				System.out.println("잘못 된 입력입니다.");
			}
		}
	}
	
}
