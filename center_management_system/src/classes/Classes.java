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
	private int enrollment;
	
	private String teacherName;
	private int registerId;
	
	@Override
	public String toString() {
		return classId + "    " + String.format("%-5s", className) + teacherName + "  " + classPlace + "  " + classDay + " " 
				+ classFee + "   " + classCapacity + "      " + enrollment;
	}
}
