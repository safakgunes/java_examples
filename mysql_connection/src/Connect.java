
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
    //    private String password = "root";
    private String password = "";



    //    private String db_name = "mydb";
    private String db_name = "demo";

    private String host =  "localhost";

    //    private int port = 3306;
    private int port = 3307;


    private Connection con = null;

    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public void preparedCalisanlariGetir(int id) {

        String sorgu = "Select * From calisanlar where id > ? and ad like ? ";


        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2,"M%");


            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ad  = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String email =  rs.getString("email");

                System.out.println("Ad : " + ad + " Soyad : " + soyad + " Email : " + email);



            }


        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }






    }
    public void calisanEkle() {



        try {
            statement = con.createStatement();
            String ad = "Semih";
            String soyad = "Aktaş";
            String email =  "semihaktas@gmail.com";
            // Insert Into calisanlar (ad,soyad,email) VALUES('Yusuf','Çetinkaya','mucahit@gmail.com')
            String sorgu = "Insert Into calisanlar (ad,soyad,email) VALUES(" + "'" + ad + "'," + "'" + soyad + "'," + "'" + email + "')";

            statement.executeUpdate(sorgu);



        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void calisanSil() {

        try {
            statement = con.createStatement();

            String sorgu = "Delete from calisanlar where id > 3";

            int deger = statement.executeUpdate(sorgu);
            System.out.println(deger + " kadar veri etkilendi...");


        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    public void calisanGuncelle() {


        try {
            statement = con.createStatement();

            String sorgu = "Update calisanlar Set email = 'example@gmail.com' where id > 3";

            statement.executeUpdate(sorgu);

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    public void calisanlariGetir() {

        String sorgu = "Select * From calisanlar";

        try {
            statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sorgu);

            while (rs.next()) {

                int id = rs.getInt("id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String email = rs.getString("email");

                System.out.println("Id : " + id + " Ad: " + ad + " Soyad : " + soyad + " Email : " + email);


            }


        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
    public Connect() {

        // "jbdc:mysql://localhost:3306/demo"
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
        //connect.preparedCalisanlariGetir(1);

        /*System.out.println("Silinmeden Önce........");
        Connect.calisanlariGetir();
        System.out.println("********************************************");
        System.out.println("Silindikten Sonra........");
        //Connect.calisanGuncelle();
        Connect.calisanSil();*/

        connect.calisanlariGetir();

        //Connect.calisanEkle();
        //Connect.calisanlariGetir();



    }


}
