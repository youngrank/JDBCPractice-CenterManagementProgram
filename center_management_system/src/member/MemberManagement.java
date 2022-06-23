package member;

import java.util.List;

import common.Management;

public class MemberManagement extends Management{
	
	public MemberManagement() {
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
				
			}
		}
	}
	
	@Override
	protected void printMenu() {
		System.out.println();
		System.out.println("1.회원등록 2.전체조회 3.회원검색 9. 뒤로가기");
	}
	
	private void newMember() {
		Member member = inputInfo();
		memberDAO.insertMember(member);
	}
	
	private Member inputInfo() {
		Member member = new Member();
		
		System.out.print("회원명> ");
		member.setMemberName(scanner.nextLine());;
		System.out.print("성별(F/M)> ");
		member.setGender(scanner.nextLine());
		System.out.print("생년월일(2022-06-23)> ");
		member.setBirthDate(scanner.nextLine());
		System.out.print("주소> ");
		member.setAddress(scanner.nextLine());
		System.out.print("연락처> ");
		member.setPhoneNumber(scanner.nextLine());
		
		return member;
	}
	
	private void selectAllMember() {
		System.out.println("No. 회원명 성별 생년월일 주소 연락처");
		
		List<Member> list = memberDAO.selectAll();
		int no = 0;
		for(Member member : list) {
			System.out.print(++no + " ");
			System.out.println(member);
		}
	}
	
	private void selecetSearch() {
		System.out.print("회원명> ");
		String name = scanner.nextLine();
		
		searchMember(name);
	}
	
	private void searchMember(String name) {
		List<Member> list = memberDAO.search(name);
		for(Member member : list) {
			System.out.println(member);
		}
	}
}
