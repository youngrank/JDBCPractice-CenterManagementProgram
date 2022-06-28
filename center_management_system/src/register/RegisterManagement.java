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
		int classId = selectSearch(memberId);
		
		if(classId > 29999)	registerDAO.insertRegister(classId, memberId);
		else if(classId == -1) System.out.println(" >> 이미 신청한 강좌입니다 <<");
		else if(classId == -2) System.out.println(" >> 수강인원이 모두 찼습니다 <<");
	}
	
	private int selectSearch(int memberId) {
		System.out.print("■ 신청 강좌명> ");
		String name = scanner.nextLine();
		int classId = selectOne(searchClass(name));
	
		int memberClassId = registerDAO.selectMemberClass(memberId, classId);
		int capacityClassId = registerDAO.registerCapacity(classId);
		
		// classId 가 register_class의 member_id가 이미 가진 값이라면
		// 이미 신청한 강의이므로 수강 신청 불가
		if(classId == memberClassId) classId = -1;
		// 수강정원이 넘어서면 수강신청 불가
		if(classId == capacityClassId) classId = -2;
		
		return classId;
	}
	
	private List<Classes> searchClass(String name) {
		System.out.println();
		System.out.println("[No.] [강좌번호] [강좌명] [강사] [장소] [요일] [금액] [정원] [등록인원]");		
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
			System.out.println(" >> 검색된 데이터가 없습니다 <<");
			return 0;
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
	
	public List<Classes> selectMemberClass(int memberId) {
		System.out.println("[No.] [신청번호] [강좌명] [강사명] [장소] [요일]");
		
		List<Classes> list = memberDAO.registerClass(memberId);
		int no = 0;
		for(Classes classes : list) {
			System.out.print("  " + ++no + "    ");
			System.out.println(classes.getRegisterId() + "    " + String.format("%-5s", classes.getClassName()) + " " + classes.getTeacherName() + "   " + classes.getClassPlace() + " " + classes.getClassDay());
		}
		
		return list;
	}
	
	public int selectDelete(List<Classes> list) {
		if(list.isEmpty()) {
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
