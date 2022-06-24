package classes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Classes {
	private int classId;
	private String className;
	private int teacherId;
	private String classPlace;
	private String classDay;
	private int classFee;
	private int classCapacity;
	private int classOpen;
	private String classContent;
	
	@Override
	public String toString() {
		return classId + " " + className + " " + teacherId + " " + classPlace + " " + classDay + " " 
				+ classFee + " " + classCapacity + " " + classOpen + " " + classContent;
	}
}
