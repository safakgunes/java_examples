
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connect {

    private String user_name = "root";
    private String password = "root";
    private String db_name = "mydb";
    private String host =  "localhost";
    private int port = 3306;


    private Connection con = null;

    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public void preparedGetEmployee(int id) {

        String query = "Select * From employees where id > ? and name like ? ";


        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2,"S%");


            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name  = rs.getString("name");
                String surname = rs.getString("surname");
                String email =  rs.getString("email");

                System.out.println("Name : " + name + " Surname : " + surname + " Email : " + email);



            }


        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    public void addEmployee() {


        try {
            statement = con.createStatement();
            String name = "Safak";
            String surname = "Gunes";
            String email =  "safakgunes@gmail.com";
            // Insert Into employees (name,surname,email) VALUES('Safak','Gunes','safak@gmail.com')
            String query = "Insert Into employees (name,surname,email) VALUES(" + "'" + name + "'," + "'" + surname + "'," + "'" + email + "')";

            statement.executeUpdate(query);



        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void deleteEmployee() {

        try {
            statement = con.createStatement();

            String query = "Delete from employees where id > 3";

            int deger = statement.executeUpdate(query);
            System.out.println(deger + " kadar veri etkilendi...");


        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    public void updateEmployee() {


        try {
            statement = con.createStatement();

            String query = "Update employees Set email = 'example@gmail.com' where id > 3";

            statement.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    public void getEmployee() {

        String query = "Select * From employees";

        try {
            statement = con.createStatement();

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");

                System.out.println("Id : " + id + " Name: " + name + " Surname : " + surname + " Email : " + email);


            }


        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
    public Connect() {

        // "jbdc:mysql://localhost:3306/mydb"
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_name+ "?useUnicode=true&characterEncoding=utf8";


        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Not Found");
        }


        try {
            con = DriverManager.getConnection(url, user_name, password);
            System.out.println("Connection Success");


        } catch (SQLException ex) {
            System.out.println("Connection Failed");
            //ex.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Connect connect = new Connect();


        //connect.preparedGetEmployee(1);
        connect.getEmployee();
        //connect.updateEmployee();
        //connect.deleteEmployee();
        //connect.addEmployee();


    }


}
