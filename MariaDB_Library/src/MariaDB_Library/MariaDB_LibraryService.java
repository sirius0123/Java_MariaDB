package MariaDB_Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MariaDB_LibraryService {
	
	Connection connection = null;
    Statement statement= null;
	
	public MariaDB_LibraryService() {}
	
	public void menu() {
		
		Scanner 	menu_scan = new	Scanner(System.in);
		
		int num;
		int cnt = 0;
		
		while(true) {
			System.out.println();
			System.out.println("*********************");
			System.out.println("    1. DB Connect   ");
			System.out.println("    2. Create  	    "); 
			System.out.println("    3. Read         "); 
			System.out.println("    4. Update       "); 
			System.out.println("    5. Delete       "); 
			System.out.println("    6. DB Exit      ");
			System.out.println("*********************");
			System.out.print(" 번호 입력 :  ");
			
			num = menu_scan.nextInt();
									
			if		(num == 6)	{
				menu_scan.close();     
				break;  		     			
			}
			if		(num == 1) {	 
				connectMariaDB();   
				cnt =1;
			}			
			else if (num == 2)	{	 
				if (cnt ==1) create();	     
				else {
					System.out.println("DB is not Connected!! ");
				
				}
			}
			else if (num == 3)	{
				if (cnt ==1) read();	 
				else {
					System.out.println("DB is not Connected!! ");	
				}
			
			}
			else if (num == 4)	{
				try {
					
					if (cnt ==1) update();	 
					else {
						System.out.println("DB is not Connected!! ");	
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 //Update
			
			}	
			else if (num == 5)	{
				if (cnt ==1) delete();	 
				else {
					System.out.println("DB is not Connected!! ");	
				}			
			}	
			else System.out.println(" 1~ 6까지 숫자를 입력하시오");
		
			
		}//while
		menu_scan.close();
	}//menu()

	public void connectMariaDB() {
		
		System.out.println("    1. DB연결    ");

		try {		
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/csv_db", "root", "1234") ;
			System.out.println(" Connection OK ");
			connection.setAutoCommit(true);
			
		}catch(ClassNotFoundException e) {
			System.out.println("드라이브 로딩실패");
		}
		catch(SQLException e) {
			System.out.println("DB연결실패");
		}
	}
	public void create(){
		int station_no;
		String station_name;
		//int ck_input = 0;
		System.out.println("");
		System.out.println("   Insert   ");
		
		Scanner 	sc = new	Scanner(System.in);
		
		System.out.println("  데이터를 입력합니다.");
		System.out.println("");
		System.out.println("일련번호를 입력하세요:");
	    int in_num = sc.nextInt();

		try{
					    System.out.println("도서관 일련번호를 입력하세요:");
					    station_no = sc.nextInt();
					   // ck_input = 1;
					    System.out.println("도서관 명을 입력하세요:");
					    station_name = sc.next();
					    statement = connection.createStatement();			
					    statement.execute("INSERT INTO libary(도서관 일련번호, 도서관명)" + 
					    				  " VALUES("+도서관 일련번호+","+도서관명+"');"
					    				  );
			
		    }catch (InputMismatchException ex) {
		    					System.out.println("이전메뉴로돌아갑니다. 처음부터 다시 입력하세요.");
		    }catch (SQLException e) {
			      
			}finally {
			    		System.out.println("");
			    		System.out.println("입력 완료");
		    }	
		
		//sc.close();

} //create
	public void read() {
		System.out.println("");
		System.out.println("   Select  ");
		System.out.println("   데이터를 조회합니다.");
		System.out.println("");
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
			"SELECT * FROM library;");
		
   
	         System.out.println("도서관 일련번호 / 도서관명   /구 코드 / 구명  / 주소   / 전화번호 / 홈페이지 URL / 운영시간 / 정기 휴관일 / 도서관 구분 / 도서관 구분명 /위도 / 경도 " );
	         System.out.println("----------------------------------------------------------------------------------------");
			
		      //int count = 0;
		      while (resultSet.next()) {		            
		            System.out.print  (resultSet.getString("도서관 일련번호") +	"\t" );
		            System.out.print  (resultSet.getString("도서관명") +"\t");
		            System.out.print  (resultSet.getString("구 코드") +	"\t");
		            System.out.print  (resultSet.getString("구명") +"\t");
		            System.out.print  (resultSet.getString("주소") +"\t");
		            System.out.print  (resultSet.getString("전화번호") +		"\t" );
		            System.out.print  (resultSet.getString("홈페이지 URL") +		"\t");
		            System.out.print  (resultSet.getString("운영시간") +		"\t");
		            System.out.print  (resultSet.getString("정기 휴관일") +		"\t");
		            System.out.print  (resultSet.getString("도서관 구분") +		"\t");
		            System.out.print  (resultSet.getString("도서관 구분명") +		"\t");
		            System.out.println(resultSet.getString("위도") +		"\t");
		            System.out.println(resultSet.getString("경도") +		"\t");
		         }
		}catch(SQLException e) {
					e.printStackTrace();
		        	 System.out.println("DB SQL Fail");
		}finally {
			System.out.println("");
			System.out.println("조회 완료");
		}

	}
	
	public void update() throws SQLException {
				
		Scanner 	sc = new	Scanner(System.in);
		System.out.println("");
		System.out.println("   UPDATE   ");
		
		System.out.println("  데이터를 수정합니다.");
		System.out.println("");
		System.out.println("수정할 도서관 일련번호 입력:");
		
		int id = sc.nextInt();
		System.out.println("수정할 line 입력:");
		int line = sc.nextInt();
		try {
				  
				  System.out.println("수정할 도서관명 입력:");
				  String station_name = sc.next();			      
	
				  statement = connection.createStatement();
				  statement.execute("UPDATE library SET" + 
				    	  			" 구 코드 ="+구 코드+", 도서관명 = '"+도서관명+"'"+"WHERE 도서관 일련번호="+도서관 일련번호+";"
						  			);
		}catch (InputMismatchException ex) {
	    		 System.out.println("이전메뉴로 돌아갑니다. 처음부터 다시 입력하세요.");
		}catch (SQLException e) {
		     
		}finally {
				System.out.println("");
				System.out.println("UPDATE 완료");
		}
	//	sc.close();
	}//update
	
	public void delete() {
		Scanner 	sc = new	Scanner(System.in);
		System.out.println("");
		System.out.println("   DETETE   ");			
		System.out.println("  데이터를 삭제합니다.");
		System.out.println("");
		System.out.print("삭제할 도서관 일련번호를 입력하세요 : ");
		      		      
		int delnum = sc.nextInt();
		try {
		   	  statement = connection.createStatement();
		   	  statement.execute("DELETE FROM library WHERE in_num="+delnum+";");
		   }catch (SQLException e) {
		   	  System.out.println("DB SQL Fail");
		   }finally {
		      System.out.println("");
		      System.out.println("삭제완료");
		   }
		//sc.close();
	} //delete
	
}   // MariaDB_LibraryService


