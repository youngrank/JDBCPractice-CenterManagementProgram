package member;

import java.util.List;

import classes.Classes;
import common.Management;
import register.RegisterManagement;

public class MemberManagement extends Management{
	
	public void run() {
		while(true) {
			printMenu();
			
			int num = selectNum();
			if(num == 1) {
				// 신규 회원 등록
				newMember();
			} else if(num == 2) {
				// 전체 조회
				selectAllMember();
			} else if(num == 3) {
				// 회원 검색
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
		System.out.println("[회원관리]");
		System.out.println(" ①회원등록 ②전체조회 ③회원검색 ⑨뒤로가기");
	}
	
	private void newMember() {
		Member member = inputInfo();
		memberDAO.insertMember(member);
	}
	
	private Member inputInfo() {
		Member member = new Member();
		System.out.println();
		System.out.print("■ 회원명> ");
		member.setMemberName(scanner.nextLine());;
		System.out.print("■ 성별(F/M)> ");
		member.setGender(scanner.nextLine());
		System.out.print("■ 생년월일(2022-06-23)> ");
		member.setBirthDate(scanner.nextLine());
		System.out.print("■ 주소> ");
		member.setAddress(scanner.nextLine());
		System.out.print("■ 연락처> ");
		member.setPhoneNumber(scanner.nextLine());
		
		return member;
	}
	
	private void selectAllMember() {
		System.out.println();
		System.out.println("[No.] [회원번호] [회원명] [성별] [생년월일]");
		
		List<Member> list = memberDAO.selectAll();
		int no = 0;
		
		for(Member member : list) {
			System.out.printf("%3d    ", ++no);
			System.out.print(member.getMemberId() + "    " + member.getMemberName() + "    " + member.getGender() + "   " + member.getBirthDate());
			System.out.println();
		}
	}
	
	private void selecetSearch() {
		System.out.println();
		System.out.print("회원명) ");
		String name = scanner.nextLine();
		
		selectOne(searchMember(name));
	}
	
	private List<Member> searchMember(String name) {
		System.out.println("[No.] [회원번호] [회원명] [성별] [생년월일]");
		
		List<Member> list = memberDAO.search(name);
		int no = 0;
		for(Member member : list) {
			System.out.print("  " + ++no + "    ");
			System.out.println(member.getMemberId() + "    " + member.getMemberName() + "    " + member.getGender() + "   " + member.getBirthDate());
		}
		
		return list;
	}
	
	private void selectOne(List<Member> list) {
		if(list.isEmpty()) {
			System.out.println(">> 검색한 회원과 일치하는 회원이 없습니다. <<");
			return;
		}
		
		int no = -1;
		int memberId;
		System.out.println();
		System.out.print("조회 하실 회원을 선택하세요(No.)>> ");
		no = Integer.parseInt(scanner.nextLine());
		
		System.out.println("[회원번호] [회원명] [성별] [생년월일] [    연락처    ]  [  주소  ] ");
		System.out.println(list.get(no-1));
		memberId = list.get(no-1).getMemberId();
		System.out.println("---[수강내역]----------------------------------------------");
		
		
		List<Classes> registerList = memberDAO.registerClass(memberId);
		if(registerList.isEmpty()) {
			System.out.println(" >> 등록된 수강 내역이 없습니다 <<");
		} else {
			System.out.println("[No.] [신청번호] [강좌명] [강사명] [장소] [요일]");
			int num = 0;
			for(Classes classes : registerList) {
				System.out.print("  " + ++num + "    ");
				System.out.println(classes.getRegisterId() + " " + classes.getClassName() + " " + classes.getTeacherName() + " " + classes.getClassPlace() + " " + classes.getClassDay());
			}
		}
		new MemberModification().run(memberId);
	}
}
