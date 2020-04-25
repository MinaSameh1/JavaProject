package sample;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLHelper{

	// our Connection url, that will be used to connect to the DB
	private String connectionUrl = 
		"jdbc:sqlserver://localhost:1433" +
		";databaseName=" + dbSchema.DB_NAME + 
		";user=" + dbSchema.USER + 
		";password=" + dbSchema.PASSWORD + ";";

	private Connection con = null;
	private Statement statement = null;
	private PreparedStatement prepStat = null;
	private ResultSet resultSet = null;

	// add constructor so its okay to call it and do nothing in case it got called
	// by mistake 
	public SQLHelper(){ }

	// WAKE UP DATABASE STOP SLEEPING
	public int Init() throws Exception{
	try {
		// Make sure we have the driver
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		// Connect to the server
		con = DriverManager.getConnection( connectionUrl );
	
		statement = con.createStatement();
	} catch ( Exception e ) {
		// even my programs throw stuff at me, sad
		throw e;
	}
	return 0;
	}

	// Close the connection and everything
	public void die(){
		// Ofc make sure the have something in them, if they are null and you attempt to close it will cause error
		try {
		if ( resultSet != null ){
			resultSet.close();
		}

		if ( statement != null ){
			statement.close();;
		}

		if ( con != null ){
			con.close();
		}
		} catch( SQLException e ) {
			System.err.println("ERROR! " + e);
		}
	}

	public void readDB() throws Exception { }


	// Note to self: Use execute statement for data manipulation 
	// like insert, update and delete and executeQuery for data retrieval like select
	public ResultSet findByUserName(String name) throws SQLException{
		try{
			dbSchema t = new dbSchema();
			return statement.executeQuery(
					"SELECT * FROM " + dbSchema.TABLE1_NAME +
					" WHERE " + t.Tab1.get(1)+ "= '" + name + "'"
					);
		} catch(SQLException e){
			throw e;
		}
	}

	public ResultSet findByUserName(int ID) throws SQLException{
		try{
			dbSchema t = new dbSchema();
			return statement.executeQuery(
					"SELECT * FROM " + dbSchema.TABLE1_NAME +
							" WHERE " + t.Tab1.get(0)+ "= " + ID
			);
		} catch(SQLException e){
			throw e;
		}
	}

	// This is only for testing purposes
	public void testSql() throws SQLException {
		ResultSet rs = statement.executeQuery(
				"SELECT * FROM " + dbSchema.TABLE1_NAME
		);
		while(rs.next()){
			System.out.println("USERNAME:" + rs.getString("USERNAME") +
					" FIRST NAME:" + rs.getString("FIRST_NAME")
					);
		}
	}

	// this is also for testing techincally speaking
	public boolean HandleLogin(String name,String pass){
		dbSchema db = new dbSchema();
		try {
			ResultSet rs = statement.executeQuery(
					"SELECT * FROM " + dbSchema.TABLE1_NAME +
							" WHERE USERNAME= '" + name + "'"
			);
			rs.next();
			if(name.equals(rs.getString("USERNAME")) && pass.equals(rs.getString("PASSWORD") ))
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("FAILED");
		}
		return false;
	}
	// Mostafa Did all of the inserts on his own :D 
	// Insert into clinc Users
	public int InsertIntoUsers(
			int ID, String USERNAME,
			String FIRST_NAME, String LAST_NAME, String PASSWORD,
		        String EMAIL, String DOB, int AGE, String TELEPHONE, 
		        String ALTPHONE, String ADDRESS, String BLOOD_TYPE,
		        int USERTYPE, char GENDER ) {
		String SQL = 
			"INSERT INTO " + dbSchema.TABLE1_NAME + " VALUES('" + ID + 
			"', '" + USERNAME + "', '" + FIRST_NAME + "', '" + LAST_NAME + 
			"','"  + PASSWORD + "'  ,'" + EMAIL+  
			"' ,'" + DOB + "','" + AGE + "','" + TELEPHONE + 
			"' ,'" + ALTPHONE +"','" + ADDRESS + "','" + 
			BLOOD_TYPE + "','" + USERTYPE + "','" + GENDER + "' )";
		try{ 
		statement.execute(SQL);
		} catch( SQLException e ){
			System.err.println("ERROR! " + e);
			return -1;
		}
		return 0;
	}

	// Insert into workers table
	public int InsertIntoWORKERS(
			int WORKERID,
			double SALARY, String WORK_TIME, String NOTES){
		String SQL = 
			"INSERT INTO " + dbSchema.TABLE2_NAME + " VALUES('" + WORKERID + 
			"', '" + SALARY + "', '" + WORK_TIME + "', '" + NOTES + "')";
		try{ 
		statement.execute(SQL);
		} catch( SQLException e ){
			System.err.println("ERROR! " + e);
			return -1;
		}
		return 0;

	}
	// Insert into patients
	public int InsertIntoPATIENTS(	int PATIENTSID,
			String NOTES , String KONOWN_DISEASES, String PRESCRIPTION) {
		String SQL = "INSERT INTO " + dbSchema.TABLE3_NAME + 
			" VALUES('" + PATIENTSID + "', '" + NOTES 
			+ "', '" + KONOWN_DISEASES + "', '" + PRESCRIPTION + "')";
		try{ 
		statement.execute(SQL);
		} catch( SQLException e ){
			System.err.println("ERROR! " + e);
			return -1;
		}
		return 0;
	}

	public int InsertIntoVISTS( String VISITTYPE , 
			String EXTRA , String VISIT_TIME,double COST) {
			String SQL = 
			"INSERT INTO " + dbSchema.TABLE4_NAME + 
			" VALUES('" + VISITTYPE + 
			"', '" + EXTRA + "', '" + VISIT_TIME + 
			"', '" + COST + "')";
		try{ 
		statement.execute(SQL);
		} catch( SQLException e ){
			System.err.println("ERROR! " + e);
			return -1;
		}
		return 0;
	}

	protected void resetDatabase() throws Exception{
		try{ 
			SQLHelper.createTable(statement);
		} catch( SQLException e){
			throw e;
		}
	}

	// Recreate the entire tables and everything
	private static void createTable(Statement stmt) throws SQLException {
		dbSchema DB = new dbSchema();

		// Note: You cannot drop refrenced Tables, drop first the refrences  
		// then drop the tables, learned that the hard way! ~Mina
		// Drop FOREGIN Keys first
		stmt.execute(
				"IF EXISTS (SELECT * FROM sys.foreign_keys WHERE " +
				"name='FK_WorkerID'" + 
				") ALTER TABLE " + dbSchema.TABLE2_NAME + 
				" DROP CONSTRAINT FK_WorkerID"
				);
		stmt.execute(
				"IF EXISTS (SELECT * FROM sys.foreign_keys WHERE " +
				"name='FK_vPatId'" + 
				") ALTER TABLE " + dbSchema.TABLE3_NAME + 
				" DROP CONSTRAINT FK_PatientID"
				);

		stmt.execute(
				"IF EXISTS (SELECT * FROM sys.foreign_keys WHERE " +
				"name='FK_vPatId'" + 
				") ALTER TABLE " + dbSchema.TABLE4_NAME + 
				" DROP CONSTRAINT FK_vPatId"
				);

		// Delete Tables if they exist
		stmt.execute( "DROP TABLE IF EXISTS " + dbSchema.TABLE1_NAME );
		stmt.execute( "DROP TABLE IF EXISTS " + dbSchema.TABLE2_NAME );
		stmt.execute( "DROP TABLE IF EXISTS " + dbSchema.TABLE3_NAME );
		stmt.execute( "DROP TABLE IF EXISTS " + dbSchema.TABLE4_NAME );

		// ReCreate Them
		stmt.execute(
			"CREATE TABLE " + dbSchema.TABLE1_NAME + "("       + 
			DB.Tab1.get(0)	+ 	" INT		NOT NULL," +
			DB.Tab1.get(1)  + 	" VARCHAR(25)	NOT NULL," +
			DB.Tab1.get(2)  + 	" VARCHAR(25)	NOT NULL," +
			DB.Tab1.get(3)  + 	" VARCHAR(25)	NOT NULL," +
			DB.Tab1.get(4)  +       " VARCHAR(25)	NOT NULL," +
			DB.Tab1.get(5)  +   	" VARCHAR(20) 	NOT NULL," +
			DB.Tab1.get(6)  +	" DATE		NOT NULL," +
			DB.Tab1.get(7)  + 	" INT		CHECK (AGE >= 0 AND AGE <=105) NOT NULL," +
			DB.Tab1.get(8)	+	" CHAR(14)	NULL,    " +
			DB.Tab1.get(9)	+ 	" VARCHAR(12)	NULL,    " +
			DB.Tab1.get(10)	+ 	" VARCHAR(25)	NULL,	 " +
			DB.Tab1.get(11)	+ 	" VARCHAR(2)	NULL,    " +
			DB.Tab1.get(12)	+ 	" INT		NOT NULL," +
			DB.Tab1.get(13)	+ 	" CHAR(1)	NOT NULL," +
			"PRIMARY KEY("  + DB.Tab1.get(0)       + "), " 	   +    
			"CONSTRAINT UC_userName UNIQUE(" + DB.Tab1.get(1)  + ") );"
			);

		stmt.execute(
			"CREATE TABLE " + dbSchema.TABLE2_NAME + "(" + 
			DB.Tab2.get(0) + " INT         NOT NULL,"    +
			DB.Tab2.get(1) + " NUMERIC     NOT NULL,"    +
			DB.Tab2.get(2) + " VARCHAR(20) NOT NULL,"    +
			DB.Tab2.get(3) + " VARCHAR(20) NULL, "   +
			"CONSTRAINT WorkerId PRIMARY KEY("     + DB.Tab2.get(0) + ")," +
			"CONSTRAINT FK_WorkerID FOREIGN KEY (" + DB.Tab2.get(0) +  ") " +
			" REFERENCES " + dbSchema.TABLE1_NAME + 
			"(" + DB.Tab1.get(0) +") );"
			);

		stmt.execute(
			"CREATE TABLE " + dbSchema.TABLE3_NAME +" ( " + 
			DB.Tab3.get(0) + " INT 	NOT NULL," + 
			DB.Tab3.get(1) + " VARCHAR(20),"   + 
			DB.Tab3.get(2) + " VARCHAR(20),"   + 
			"CONSTRAINT PatientId PRIMARY KEY(" + DB.Tab3.get(0) + ")," +
			"CONSTRAINT FK_PatientID FOREIGN KEY (" + DB.Tab3.get(0) + 
			") REFERENCES " + dbSchema.TABLE1_NAME  +
		       	"( " + DB.Tab1.get(0) + " ) );"
			);

		stmt.execute(
			"CREATE TABLE " + dbSchema.TABLE4_NAME + " ( " +
			DB.Tab4.get(0) + " INT NOT NULL,"     + 
			DB.Tab4.get(1) + " INT NOT NULL,"     +
			DB.Tab4.get(2) + " VARCHAR(50) NOT NULL," +
			DB.Tab4.get(3) + " VARCHAR(25) NOT NULL," +
			DB.Tab4.get(4) + " DATETIME NOT NULL," +
			DB.Tab4.get(5) + " VARCHAR(20) NULL," +
			DB.Tab4.get(6) + " NUMERIC NOT NULL " + 
			"CONSTRAINT VisitID PRIMARY KEY (" + DB.Tab4.get(0) + " )," +
			"CONSTRAINT FK_vPatId FOREIGN KEY (" + DB.Tab4.get(1) + ")" +
		        "REFERENCES " + dbSchema.TABLE1_NAME + "(" + DB.Tab1.get(0) +
			") );" 
		);

		// Add admin user
		stmt.execute(
				"INSERT INTO " + dbSchema.TABLE1_NAME + " VALUES(" +
						"1,'admin','Admin','Owner','admin','ADMIN@EMAIL.COM',"+
						"'1999-05-18' ,20,'01252515125',NULL,"+
						"'3in Shams',NULL,'1','M');"
		);
		// Also add admin into workers 
		stmt.execute(
				"INSERT INTO " + dbSchema.TABLE2_NAME + " VALUES(" +
				"1,0,'11:00 am - 8:00 pm', NULL);" 
			    );
	}


	// this will help us automate somethings and make everything more modifiable
	public static class dbSchema{
	// NOTE : I made the username and password into vars so my team can 
	// use their SQL. It is not very secure but will work for now.
		private static final String USER="SA";
		private static final String PASSWORD="r00t.ROOT";
		public static final String DB_NAME = "Project";
		// First Table
		public static final String TABLE1_NAME = "ClinicUSERS";
		// The workers table
		public static final String TABLE2_NAME = "WORKERS";
		// The Patients table
		public static final String TABLE3_NAME = "PATIENTS";
		// The Visits table
		public static final String TABLE4_NAME = "Vists";

		// General Users Table
		public ArrayList<String> Tab1 = new ArrayList<String>();
		// Workers Table aka doctors and staff
		public ArrayList<String> Tab2 = new ArrayList<String>();
		// Patients Table
		public ArrayList<String> Tab3 = new ArrayList<String>();
		// Visits Table
		public ArrayList<String> Tab4 = new ArrayList<String>();

		public dbSchema(){
			Tab1.add("ID");
			Tab1.add("USERNAME");
			Tab1.add("FIRST_NAME");
			Tab1.add("LAST_NAME");
			Tab1.add("PASSWORD");
			Tab1.add("EMAIL");
			Tab1.add("DOB");
			Tab1.add("AGE");
			Tab1.add("TELEPHONE");
			Tab1.add("ALTPHONE");
			Tab1.add("ADDRESS");
			Tab1.add("BLOOD_TYPE");
			Tab1.add("USERTYPE");
			Tab1.add("GENDER");

			Tab2.add("WORKERID");
			Tab2.add("SALARY");
			Tab2.add("WORK_TIME");
			Tab2.add("NOTES");

			Tab3.add("PATIENTID");
			Tab3.add("NOTES");
			Tab3.add("KNOWN_DISEASES");
			Tab3.add("PRESCRIPTION");

			Tab4.add("VISITID");
			Tab4.add("PatientID");
			Tab4.add("PURPOSE");
			Tab4.add("VISITTYPE");
			Tab4.add("VISIT_TIME");
			Tab4.add("EXTRA");
			Tab4.add("COST");
		}
	}

}
