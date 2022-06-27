package register;

import java.util.List;

import classes.Classes;
import common.Management;

public class RegisterManagement extends Management{
	public void run(int memberId) {
		while(true) {
			printMenu();
			int num = selectNum();
			
			if(num == 1) {
				// 수강 신청할 강좌 검색 ..
				registerClass(memberId);
			} else if(num == 2) {
				// 수강 신청 철회
				registerDelete(memberId);
			} else if(num == 9) {
				break;
			} else {
				System.out.println("inputerror");
			}
		}
	}
	
	@Override
	protected void printMenu() {
		System.out.println();
		System.out.println("[수강신청관리]");
		System.out.println(" ①수강신청 ②신청철회 ⑨뒤로가기");
	}
	
	private void registerClass(int memberId) {
		int classId = selectSearch();
		
		if(classId > 0)	registerDAO.insertRegister(classId, memberId);
	}
	
	private int selectSearch() {
		System.out.print("■ 신청 강좌명> ");
		String name = scanner.nextLine();
		int classId = selectOne(searchClass(name));
		
		return classId;
	}
	
	private List<Classes> searchClass(String name) {
		System.out.println();
		System.out.println("[No.] [강좌번호] [강좌명] [강사] [장소] [요일] [금액] [정원]");		
		List<Classes> list = classDAO.search(name);
		int no = 0;
		for(Classes classes : list) {
			System.out.print("  " + ++no + "    ");
			System.out.println(classes);
		}
		
		return list;
	}
	
	private int selectOne(List<Classes> list) {
		if(list.isEmpty()) {
			System.out.println("검색된 데이터가 없습니다.");
			return -1;
		}
		
		int no = -1;
		int classId;
		System.out.println();
		System.out.print("신청 하실 강좌를 선택하세요(No.)> ");
		no = Integer.parseInt(scanner.nextLine());
		
		classId = list.get(no-1).getClassId();
		
		return classId;
	}
	
	private void registerDelete(int memberId) {
		System.out.println();
		registerDAO.delete(selectDelete(selectMemberClass(memberId)));
	}
	
	public List<Register> selectMemberClass(int memberId) {
		System.out.println("No. 신청번호 강좌명 강사번호 장소 요일 금액 정원 개설여부 내용");
		
		List<Register> list = registerDAO.selectAll(memberId);
		int no = 0;
		for(Register register : list) {
			System.out.print(++no + " ");
			System.out.println(register);
		}
		
		return list;
	}
	
	public int selectDelete(List<Register> list) {
		if(list.isEmpty()) {
			System.out.println("신청한 강좌가 없습니다.");
			return -1;
		}
		
		int no = -1;
		int registerId;
		System.out.println();
		System.out.print("신청 취소 하실 강좌를 선택하세요(No.)> ");
		no = Integer.parseInt(scanner.nextLine());
		
		registerId = list.get(no-1).getRegisterId();
		
		return registerId;
	}
}
