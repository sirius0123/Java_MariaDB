package MariaDB_Library;

import java.sql.SQLException;

public class MariaDB_LibraryMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
				
		MariaDB_LibraryService mariaDB_LibraryService = new MariaDB_LibraryService();
		mariaDB_LibraryService.menu();
		System.out.println("프로그램을 종료합니다.");		
	}

}
