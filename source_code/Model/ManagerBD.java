/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 05/05/13
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model;

import java.awt.*;
import java.sql.*;

/**
 * Class to manage database SQL
 */
public class ManagerBD {
    /**
     * Url database
     */
    private String url = new String();
    /**
     * Password database
     */
    private String password = new String();
    /**
     * Login database
     */
    private String login = new String();

    /**
     * Connection
     */
    private Connection conn;
    /**
     * Statement
     */
    private Statement stmt;
    /**
     * ResultSet
     */
    private ResultSet rset;
    /**
     * Result set meta data
     */
    private ResultSetMetaData rsetMeta;
    /**
     * List to store data of the database
     */
    private List listData;

    /**
     * Query insert
     */
    private final static String R_INSERT = "INSERT INTO GameData (IdPlayer,NamePlayer,Cash,GreenRank,PowerNeed, NBTurn) VALUES(?,?,?,?,?,?)";

    /**
     * This method allows to create a database
     * @param url Url database
     * @param login Login database
     * @param password password database
     * @throws SQLException throw an exception that provides information on a database access error or other errors
     * @throws ClassNotFoundException thrown when an application tries to load a class which not exist
     */
    public void createBD(String url, String login, String password) throws SQLException, ClassNotFoundException {
        this.url = url;
        this.login = login;
        this.password = password;

        Class.forName("com.mysql.jdbc.Driver"); // chargement driver
        conn = DriverManager.getConnection(this.url, this.login, this.password); //création d'une connexion JDBC à la base
        stmt = conn.createStatement(); // création d'un ordre SQL (statement)
    }

    /**
     * This method allows to execute a query
     * @throws SQLException throw an exception that provides information on a database access error or other errors
     */
    public void executeRequest() throws SQLException {
        rset = stmt.executeQuery("select * from GameData order by NBTurn desc, Cash desc limit 10");
        rsetMeta = rset.getMetaData();

        int nbColumn = rsetMeta.getColumnCount();
        int nbRow = rset.getRow();

        listData = new List(nbRow, false);

        for (int i = 1; i <= nbColumn; ++i) {
            listData.add(rsetMeta.getColumnLabel(i));
        }
    }

    /**
     * This method allows to insert data to database
     * @param idPlayer represents id player
     * @param namePlayer represents name player
     * @param cash represents cash player
     * @param gRK represents green rank player
     * @param powerNeed represents power need of player
     * @param nbTurn represents the number of turn achieved by this player
     * @throws SQLException throw an exception that provides information on a database access error or other errors
     */
    public void insertData(int idPlayer, String namePlayer, float cash, int gRK, int powerNeed, int nbTurn) throws SQLException {
        PreparedStatement stmtPrepared = conn.prepareStatement(R_INSERT);

        stmtPrepared.setInt(1,idPlayer);
        stmtPrepared.setString(2,namePlayer);
        stmtPrepared.setFloat(3,cash);
        stmtPrepared.setInt(4,gRK);
        stmtPrepared.setInt(5,powerNeed);
        stmtPrepared.setInt(6,nbTurn);

        stmtPrepared.executeUpdate();
    }

    /**
     * Get the result of the query
     * @return the current result set
     */
    public ResultSet getRset() {
        return rset;
    }

}
