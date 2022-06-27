package member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	private int memberId;
	private String memberName;
	private String gender;
	private String birthDate;
	private String address;
	private String phoneNumber;
	
	@Override
	public String toString() {
		return "  " + memberId + "   " + memberName + "    " + gender + "   " + birthDate + "  " + phoneNumber + "  " + address;
	}
}
