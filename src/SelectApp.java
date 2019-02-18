import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList; // All this imports  I saw at this site:  http://www.sqlitetutorial.net/sqlite-java/ 
//  I learned from there



public class SelectApp {
	ArrayList<Object> allData=new ArrayList<Object>();
	public String name1;
	public String gender1;
	public int age1;
	public int id1;

	public SelectApp(String _name1, String _gender1, int _age1,int _iD1) {
		this.name1=_name1;
		this.gender1=_gender1;
		this.age1=_age1;
		this.id1=_iD1;

	}

	public SelectApp() {

	}

	private Connection connect() {  //connect to data base

		String url = "jdbc:sqlite:.\\sqliteDBTest.db"; //where is the data base in my computer
		Connection conn = null;
		try {   //try to connect
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.\n\n");
		} catch (SQLException e) {
			System.out.println(e.getMessage());  // if faild, print error
		}
		return conn;
	}



	public void ex1(){   // Solution for exercise1
		String sql = "select ui.id,ui.name,ui.email_Address,ui.gender from tbl_userinfo ui where ui.age between 25 and 33 order by ui.age";  
		// The SQL contains the logic and and that's why the java code is clean - no manipulation on data with java code



		try (Connection conn = this.connect();
				Statement stmt  = conn.createStatement();
				ResultSet rs    = stmt.executeQuery(sql)){

			// loop through the result set
			while (rs.next()) {  // print all data for each record (all records)
				System.out.println(rs.getInt("id") +  "\t" + 
						rs.getString("name") + "\t" +
						rs.getString("email_Address") + "\t" +
						rs.getString("gender"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


	public void ex2() {
		String sql = "select ui.name,ui.Company,ui.salary,c.state,c.company_name from tbl_userinfo ui,tbl_company c where c.state=\"California\" and ui.salary<=15  order by ui.Company;";
		String sql2= "select ui.name,ui.Company,ui.salary,c.state,c.company_name from tbl_userinfo ui,tbl_company c where c.state not in (\"California\") and ui.salary>=15  order by ui.Company;";


		try (Connection conn = this.connect();
				Statement stmt  = conn.createStatement();
				ResultSet rs    = stmt.executeQuery(sql)){

			// loop through the result set
			while (rs.next()) {
				System.out.println(rs.getString("name") +  "\t" + 
						rs.getString("Company") + "\t" +
						rs.getInt("salary") + "\t" +	
						rs.getString("state") + "\t" +
						rs.getInt("company_name"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n\n\n");

		try (Connection conn = this.connect();
				Statement stmt  = conn.createStatement();
				ResultSet rs    = stmt.executeQuery(sql2)){

			// loop through the result set
			while (rs.next()) {
				System.out.println(rs.getString("name") +  "\t" + 
						rs.getString("Company") + "\t" +
						rs.getInt("salary") + "\t" +	
						rs.getString("state") + "\t" +
						rs.getInt("company_name"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}





	public static void main(String[] args) {
		SelectApp app = new SelectApp();
		System.out.println("Exercise 1:");
		app.ex1();
		System.out.println("\n\n\n");
		System.out.println("Exercise 2:");
		app.ex2();


	}

}
