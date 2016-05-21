package in.gagan.common.util;

public class CommonUtil {
	public static boolean checkIfNotNull(String fields) {
		String[] field = fields.split(",");
		for(int i=0;i<field.length;i++){
			if(field[i].isEmpty() && field[i]==null){
				return false;
			}				
		}
		return true;
	}
}
