package section01;

public class Validation {
	public static boolean pwValidation(String pw) {
		if(pw.length() < 8) return false;
		else return true;
	}
	public static boolean classValidation(int uclass) {
		if(uclass < 0 || uclass > 2) return false;
		else return true;
	}
	public static boolean phoneFormValidation(String contact) {
		if(contact.matches("(010)-[1-9][0-9]{3,4}-[0-9]{4,5}") || contact.matches("(011)-[1-9][0-9]{2,3}-[0-9]{4,5}")) return true; 
		else return false;
	}
}
