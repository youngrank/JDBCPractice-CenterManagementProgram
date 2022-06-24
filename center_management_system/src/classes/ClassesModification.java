package classes;

import common.Management;

public class ClassesModification extends Management{

	public void run(int classId) {
		while(true) {
			printMenu();
			
			int num = selectNum();
			if(num == 1) {
				// 강사 정보 수정
				selectUpdate(classId);
			} else if(num == 2) {
				// 강사 삭제
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
		System.out.println("1.강좌정보수정 3.강좌삭제 9.뒤로가기");
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
		} else if(num == 9){
			// 뒤로가기
			return;
		} else {
			System.out.println("input error");
		}
	}
	
	private void delete(int classId) {
		
	}
	
}
