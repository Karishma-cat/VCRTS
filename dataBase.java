import java.sql.*;

public class dataBase {
    static Connection connection = null;

   //  static String url = // put MYSQL link here
   // static String user = put username created here
    //static String pass = put password created here

    public static void main(String[] args) {
        try{
           connection = DriverManager.getConnection(url, user, pass);
           //String sql= 
           Statement statement = connection.createStatement();

           int row = statement.executeUpdate(sql);

           if(row > 0){
            System.out.println("Data is inserted");
           }
            connection.close(); 

         } catch (SQLException e) {
			e.getMessage();
           }

        }
    }


