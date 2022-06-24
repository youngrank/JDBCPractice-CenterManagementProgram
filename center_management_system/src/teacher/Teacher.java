package teacher;

import lombok.Getter;
import lombok.Setter;

@Getter	// ctrl + shift + o 하면 자동으로 import 됨!
@Setter
public class Teacher {
	private int teacherId;
	private String teacherName;
	private String gender;
	private String birthDate;
	private String address;
	private String phoneNumber; 
	
	@Override
	public String toString() {
		return teacherId + " " + teacherName + " " + gender + " " + birthDate + " " + address + " " + phoneNumber;
	}
}
