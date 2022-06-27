package teacher;

import java.util.List;

import classes.Classes;
import common.Management;

public class TeacherManagement extends Management{

	public void run() {
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
		System.out.println();
		System.out.println("[No.] [강사번호] [강사명] [성별] [생년월일]");
		
		List<Teacher> list = teacherDAO.selectAll();
		int no = 0;
		for(Teacher teacher : list) {
			System.out.print("  " + ++no + "    ");
			System.out.println(teacher.getTeacherId() + "    " + teacher.getTeacherName() + "    " + teacher.getGender() + "   " + teacher.getBirthDate());
		}
	}
	
	private void selectSearch() {
		System.out.print("강사명> ");
		String name = scanner.nextLine();
		
		selectOne(searchTeacher(name));
	}
	
	private List<Teacher> searchTeacher(String name) {
		System.out.println();
		System.out.println("[No.] [강사번호] [강사명] [성별] [생년월일]");
		
		List<Teacher> list = teacherDAO.search(name);
		int no = 0;
		for(Teacher teacher : list) {
			System.out.print("  " + ++no + "    ");
			System.out.println(teacher.getTeacherId() + "    " + teacher.getTeacherName() + "    " + teacher.getGender() + "   " + teacher.getBirthDate());
		}
		return list;
	}
	
	private void selectOne(List<Teacher> list) {
		if(list.isEmpty()) {
			System.out.println("검색한 강사와 일치하는 강사가 없습니다.");
			return;
		}
		
		int no = -1;
		int teacherId;
		System.out.print("조회하실 강사를 선택하세요(No.)> ");
		no = Integer.parseInt(scanner.nextLine());
		
		System.out.println("[강사번호] [강사명] [성별] [생년월일] [    연락처    ]  [  주소  ] ");
		System.out.println(list.get(no-1));
		teacherId = list.get(no-1).getTeacherId();
		System.out.println("=[강의내역]================================================");
		System.out.println("[No.] [강의번호] [강좌명] [장소] [요일] [정원]");
		List<Classes> Classlist =  classDAO.selectAll(teacherId);
		int num = 0;
		for(Classes classes : Classlist) {
			System.out.print("  " + ++num + "    ");
			System.out.println(classes.getClassId() + " " + classes.getClassName() + " " + classes.getClassPlace() + " " + classes.getClassDay()+ " " + classes.getClassCapacity());
		}
		new TeacherModification().run(teacherId);
	}
}
