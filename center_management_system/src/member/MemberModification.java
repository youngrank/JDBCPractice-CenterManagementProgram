package member;

import common.Management;

public class MemberModification extends Management{
	public void run(int memberId) {	// 검색한 회원의 정보 수정, 삭제 및 수강 신청
		while(true) {
			printMenu();
			
			int num = selectNum();
			if(num == 1) {
				// 회원 정보 수정
				selectUpdate(memberId);
			} else if(num == 2) {
				// 수강 신청
			} else if(num == 3) {
				// 회원 삭제
				delete(memberId);
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
		System.out.println("1.회원정보수정 2.수강신청 3.회원삭제 9. 뒤로가기");
	}
	
	private void selectUpdate(int memberId) {
		int num = 0;
		String input = "";
		
		System.out.println("수정할 정보를 선택하세요.");
		System.out.println("1.주소 2.연락처 9.뒤로가기");
		System.out.print("선택> ");
		num = Integer.parseInt(scanner.nextLine());
		
		if(num == 1) {
			System.out.println("주소를 입력하세요> ");
			input = scanner.nextLine();
			memberDAO.updateAddress(memberId, input);
		} else if(num == 2) {
			System.out.println("연락처를 입력하세요> ");
			input = scanner.nextLine();
			memberDAO.updatePhoneNumber(memberId, input);
		} else if(num == 9){
			// 뒤로가기
			return;
		} else {
			System.out.println("input error");
		}
	}
	
	private void delete(int memberId) {
		String answer;
		while(true) {
			System.out.print("정말로 삭제하시겠습니까? [Y/N] : ");
			answer = scanner.nextLine();
			if(answer.equals("Y")) {
				memberDAO.delete(memberId);	// 삭제 완료 후 이전 화면이 아닌 MemberManagement 화면으로 넘어가게 수정해야함
				break;
			} else if(answer.equals("N")) {
				System.out.println("이전 화면으로 돌아갑니다.");
				return;
			} else {
				System.out.println("잘못 된 입력입니다.");
			}
		}
	}
}
