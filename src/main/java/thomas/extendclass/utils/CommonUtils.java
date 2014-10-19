package thomas.extendclass.utils;

public class CommonUtils {
	public static boolean isEmptyString(String s) {
		return s == null || "".equals(s.trim()) || "null".equals(s);
	}

}
