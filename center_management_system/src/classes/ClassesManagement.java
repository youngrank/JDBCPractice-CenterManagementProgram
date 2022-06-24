package classes;

import java.util.List;

import common.Management;

public class ClassesManagement extends Management {
	
	public ClassesManagement() {
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
				selecetSearch();
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
		System.out.println("1.신규 강좌 등록 2.전체조회 3.강좌 검색 9. 뒤로가기");
	}
	
	private void newClass() {
		Classes classes = inputInfo();
		classDAO.insertClass(classes);
	}
	
	private Classes inputInfo() {
		Classes classes = new Classes();
		
		System.out.print("강좌명> ");
		classes.setClassName(scanner.nextLine());
		System.out.print("강사번호> ");
		classes.setTeacherId(Integer.parseInt(scanner.nextLine()));
		System.out.print("장소> ");
		classes.setClassPlace(scanner.nextLine());
		System.out.print("요일> ");
		classes.setClassDay(scanner.nextLine());
		System.out.print("금액> ");
		classes.setClassFee(Integer.parseInt(scanner.nextLine()));
		System.out.print("정원> ");
		classes.setClassCapacity(Integer.parseInt(scanner.nextLine()));
		System.out.print("내용> ");
		classes.setClassContent(scanner.nextLine());
		
		return classes;
	}
	
	private void selectAllClass() {
		System.out.println();
		System.out.println("No. 강좌번호 강좌명 강사번호 장소 요일 금액 정원 개설여부 내용");
		
		List<Classes> list = classDAO.selectAll();
		int no = 0;
		for(Classes classes : list) {
			System.out.print(++no + " ");
			System.out.println(classes);
		}
	}
	
	private void selecetSearch() {
		System.out.print("강좌명> ");
		String name = scanner.nextLine();
		
		selectOne(searchClass(name));
	}
	
	private List<Classes> searchClass(String name) {
		System.out.println("No. 강좌번호 강좌명 강사번호 장소 요일 금액 정원 개설여부 내용");		
		List<Classes> list = classDAO.search(name);
		int no = 0;
		for(Classes classes : list) {
			System.out.print(++no + " ");
			System.out.println(classes);
		}
		
		return list;
	}
	
	private void selectOne(List<Classes> list) {
		int no = -1;
		System.out.println("조회 하실 강좌 선택하세요. (No. 숫자 입력)> ");
		no = Integer.parseInt(scanner.nextLine());
		
		System.out.println("No. 강좌번호 강좌명 강사번호 장소 요일 금액 정원 개설여부 내용");
		System.out.println(list.get(no-1));
	}
	
	
	
}
