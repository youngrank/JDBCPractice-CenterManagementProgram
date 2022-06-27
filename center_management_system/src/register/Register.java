package register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Register {
	private int registerId;
	private int classId;
	private int memberId;
	
	@Override
	public String toString() {
		return registerId + " " + classId + " " + memberId;
	}
}
