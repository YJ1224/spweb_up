package commons;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class Db_test {

	public static void main(String[] args) {
		ArrayList<HashMap<String, String>> rs = Db.selectListMap("SELECT * FROM spr_test ORDER BY S_NUM DESC");
		System.out.println(rs);
	}
}
