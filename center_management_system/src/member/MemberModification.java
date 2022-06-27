package member;

import common.Management;
import register.RegisterManagement;

public class MemberModification extends Management{
	public void run(int memberId) {	// 검색한 회원의 정보 수정, 삭제 및 수강 신청
		while(true) {
			printMenu();
			
			int num = selectNum();
			if(num == 1) {
				// 회원 정보 수정
				selectUpdate(memberId);
			} else if(num == 2) {
				// 수강 신청 관리
				new RegisterManagement().run(memberId);
			} else if(num == 3) {
				// 회원 삭제가 완료되면 이전 화면으로 돌아가고,
				// 회원 삭제를 하지 않으면 회원조회 메뉴 출력
				if(delete(memberId)) break;
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
		System.out.println("[회원조회]");
		System.out.println(" ①회원정보수정 ②수강신청관리 ③회원삭제 ⑨뒤로가기");
	}
	
	private void selectUpdate(int memberId) {
		int num = 0;
		String input = "";
		
		System.out.println();
		System.out.println(" >> 수정할 정보를 선택하세요 <<");
		System.out.println("  ①주소 ②연락처 ⑨뒤로가기");
		System.out.print(" 선택>> ");
		num = Integer.parseInt(scanner.nextLine());
		System.out.println();
		
		if(num == 1) {
			System.out.print("■ 변경 주소> ");
			input = scanner.nextLine();
			memberDAO.updateAddress(memberId, input);
		} else if(num == 2) {
			System.out.print("■ 변경 연락처> ");
			input = scanner.nextLine();
			memberDAO.updatePhoneNumber(memberId, input);
		} else if(num == 9){
			// 뒤로가기
			return;
		} else {
			System.out.println("input error");
		}
	}
	
	private boolean delete(int memberId) {
		String answer;
		while(true) {
			System.out.println();
			System.out.print("■ 정말로 삭제하시겠습니까? [Y/N] : ");
			answer = scanner.nextLine();
			if(answer.equals("Y")) {
				memberDAO.delete(memberId);
				return true;
			} else if(answer.equals("N")) {
				System.out.println("이전 화면으로 돌아갑니다.");
				return false;
			} else {
				System.out.println("잘못 된 입력입니다.");
			}
		}
	}
	
	
	
}
