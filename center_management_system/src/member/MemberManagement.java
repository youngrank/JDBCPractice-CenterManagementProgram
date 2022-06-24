package member;

import java.util.List;

import common.Management;

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
		System.out.println("No. 회원번호 회원명 성별 생년월일 주소 연락처");
		
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
		
		selectOne(searchMember(name));
	}
	
	private List<Member> searchMember(String name) {
		System.out.println("No. 회원번호 회원명 성별 생년월일 주소 연락처");
		
		List<Member> list = memberDAO.search(name);
		int no = 0;
		for(Member member : list) {
			System.out.print(++no + " ");
			System.out.println(member);
		}
		
		return list;
	}
	
	private void selectOne(List<Member> list) {
		if(list.isEmpty()) {
			System.out.println("검색한 회원과 일치하는 회원이 없습니다.");
			return;
		}
		
		int no = -1;
		int memberId;
		System.out.println("조회 하실 회원을 선택하세요. (No. 숫자 입력)> ");
		no = Integer.parseInt(scanner.nextLine());
		
		System.out.println("회원번호 회원명 성별 생년월일 주소 연락처");
		System.out.println(list.get(no-1));
		memberId = list.get(no-1).getMemberId(); 
		
		new MemberModification().run(memberId);
	}
}
