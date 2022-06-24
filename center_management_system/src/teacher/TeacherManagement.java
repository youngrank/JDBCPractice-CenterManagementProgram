package teacher;

import java.util.List;

import common.Management;

public class TeacherManagement extends Management{

	public TeacherManagement() {
		while(true) {
			printMenu();
			
			int num = selectNum();
			if(num == 1) {
				// 신규 강사 등록
				newTeacher();
			} else if(num == 2) {
				// 전체 조회
				selectAllTeacher();
			} else if(num == 3) {
				// 강사 검색
				selectSearch();
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
		System.out.println("1.강사등록 2.전체조회 3.강사검색 9. 뒤로가기");
	}
	
	private void newTeacher() {
		Teacher teacher = inputInfo();
		teacherDAO.insertTeacher(teacher);
	}
	
	private Teacher inputInfo() {
		Teacher teacher = new Teacher();
		
		System.out.print("강사명> ");
		teacher.setTeacherName(scanner.nextLine());;
		System.out.print("성별(F/M)");
		teacher.setGender(scanner.nextLine());
		System.out.print("생년월일(2022-06-23)> ");
		teacher.setBirthDate(scanner.nextLine());
		System.out.print("주소> ");
		teacher.setAddress(scanner.nextLine());
		System.out.print("연락처> ");
		teacher.setPhoneNumber(scanner.nextLine());
		
		return teacher;
	}
	
	private void selectAllTeacher() {
		System.out.println("No. 강사번호 강사명 성별 생년월일 주소 연락처");
		
		List<Teacher> list = teacherDAO.selectAll();
		int no = 0;
		for(Teacher teacher : list) {
			System.out.print(++no + " ");
			System.out.println(teacher);
		}
	}
	
	private void selectSearch() {
		System.out.print("강사명> ");
		String name = scanner.nextLine();
		
		selectOne(searchTeacher(name));
	}
	
	private List<Teacher> searchTeacher(String name) {
		System.out.println("No. 강사번호 강사명 성별 생년월일 주소 연락처");
		
		List<Teacher> list = teacherDAO.search(name);
		int no = 0;
		for(Teacher teacher : list) {
			System.out.print(++no + " ");
			System.out.println(teacher);
		}
		return list;
	}
	
	private void selectOne(List<Teacher> list) {
		int no = -1;
		System.out.println("조회 하실 강사를 선택하세요. (No. 숫자 입력)> ");
		no = Integer.parseInt(scanner.nextLine());
		
		System.out.println("강사번호 강사명 성별 생년월일 주소 연락처");
		System.out.println(list.get(no-1));
	}
}
