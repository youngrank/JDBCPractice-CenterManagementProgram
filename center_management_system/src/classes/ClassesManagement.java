package classes;

import java.util.List;

import common.Management;

public class ClassesManagement extends Management {
	
	public void run() {
		while(true) {
			printMenu();
			
			int num = selectNum();
			if(num == 1) {
				// 신규 강좌 등록
				newClass();
			} else if(num == 2) {
				// 전체 조회
				selectAllClass();
			} else if(num == 3) {
				// 강좌 검색
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
		System.out.println("[강좌관리]");
		System.out.println(" ①신규강좌등록 ②전체조회 ③강좌검색 ⑨뒤로가기");
	}
	
	private void newClass() {
		Classes classes = inputInfo();
		classDAO.insertClass(classes);
	}
	
	private Classes inputInfo() {
		Classes classes = new Classes();
		
		System.out.println();
		System.out.print("■ 강좌명> ");
		classes.setClassName(scanner.nextLine());
		System.out.print("■ 강사번호> ");
		classes.setTeacherId(Integer.parseInt(scanner.nextLine()));
		System.out.print("■ 장소> ");
		classes.setClassPlace(scanner.nextLine());
		System.out.print("■ 요일> ");
		classes.setClassDay(scanner.nextLine());
		System.out.print("■ 금액> ");
		classes.setClassFee(Integer.parseInt(scanner.nextLine()));
		System.out.print("■ 정원> ");
		classes.setClassCapacity(Integer.parseInt(scanner.nextLine()));
		
		return classes;
	}
	
	private void selectAllClass() {
		System.out.println();
		System.out.println("[No.] [강좌번호] [강좌명] [강사] [장소] [요일] [금액] [정원] [등록인원]");
		
		List<Classes> list = classDAO.selectAll();
		int no = 0;
		for(Classes classes : list) {
			System.out.print("  " + ++no + "    ");
			System.out.println(classes);
		}
	}
	
	private void selectSearch() {
		System.out.println();
		System.out.print("■ 강좌명> ");
		String name = scanner.nextLine();
		
		selectOne(searchClass(name));
	}
	
	private List<Classes> searchClass(String name) {
		System.out.println("[No.] [강좌번호] [강좌명] [강사] [장소] [요일] [금액] [정원] [등록인원]");		
		List<Classes> list = classDAO.search(name);
		int no = 0;
		for(Classes classes : list) {
			System.out.print("  " + ++no + "    ");
			System.out.println(classes);
		}
		
		return list;
	}
	
	private void selectOne(List<Classes> list) {
		if(list.isEmpty()) {
			System.out.println(" >> 검색된 데이터가 없습니다 <<");
			return;
		}
		
		int no = -1;
		int classId;
		System.out.println();
		System.out.print("조회 하실 강좌를 선택하세요(No.)> ");
		no = Integer.parseInt(scanner.nextLine());
		
		// 선택한 강좌 출력
		System.out.println();
		System.out.println("[강좌번호] [강좌명] [강사] [장소] [요일] [금액] [정원] [등록인원]");	
		System.out.println(" " + list.get(no-1));
		classId = list.get(no-1).getClassId();
		
		new ClassesModification().run(classId);
	}
	
	
	
}
