import javax.swing.*;
import java.sql.*;

public class mysqlConnect {

    //apex database variables
    String databaseName;
    String serverAddress;
    String username;
    String password;
    String tableName;
    String cdkeys;
    int loggedin;
    int leftMinute;

    //users database variables
    String user;
    String pass;
    String freePass;
    String dayPass;
    String weekPass;
    String monthPass;
    String yearPass;
    String lifetimePass;



    mysqlConnect(String serverAddress, String username, String password, String databaseName, String tableName) {
        this.serverAddress = serverAddress;
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    boolean checkConnection() {
        System.out.println("Checking connection to database...");
        try (Connection conn = DriverManager.getConnection(serverAddress, username, password); Statement stmt = conn.createStatement()) {
            String query = "use " + databaseName + ";";
            stmt.execute(query);
            System.out.println("Checking connection to database... Database connected!");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Checking connection to database... Database connection failed!");
            return false;
        }
    }

    boolean initialize (String key) {
        try (Connection conn = DriverManager.getConnection(serverAddress, username, password); Statement stmt = conn.createStatement()) {
            String query = "use " + databaseName + ";";
            stmt.execute(query);

            System.out.println("Database connected!");
            String query2 = "select * from "+ tableName +" where cdkeys = '"  + key + "';";
            System.out.println(query2);
            ResultSet rs = stmt.executeQuery(query2);
            System.out.println(rs);
            while (rs.next()) {
                this.cdkeys = rs.getString("cdkeys");
                this.loggedin = rs.getInt("loggedin");
                System.out.println(cdkeys);
                System.out.println(loggedin);
            }
            if (cdkeys == null) {
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Database connection failed!");
            return false;
        }
    }


    boolean adminInitialize (String user, String pass) {
        try (Connection conn = DriverManager.getConnection(serverAddress, username, password); Statement stmt = conn.createStatement()) {
            String query = "use " + databaseName + ";";
            stmt.execute(query);

            System.out.println("Database connected!,admin initialize");
            String query2 = "select * from "+ tableName +" where userName = '"  + user + "' and hashedPass = '" + pass + "';";
            System.out.println(query2);
            ResultSet rs = stmt.executeQuery(query2);
            System.out.println(rs);
            while (rs.next()) {
                this.user = rs.getString("userName");
                this.pass = rs.getString("hashedPass");
                this.freePass = rs.getString("freePass");
                this.dayPass = rs.getString("dayPass");
                this.weekPass = rs.getString("weekPass");
                this.monthPass = rs.getString("monthPass");
                this.yearPass = rs.getString("yearPass");
                this.lifetimePass = rs.getString("lifetimePass");
            }
            System.out.println(this.user);
            System.out.println(this.pass);
            System.out.println(freePass);
            System.out.println(dayPass);
            System.out.println(weekPass);
            System.out.println(monthPass);
            System.out.println(yearPass);
            System.out.println(lifetimePass);

            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Database connection failed!");
            return false;
        }
    }


    boolean update(String Table,String updateColumn, String newUpdateValue,String asscoiatedColumn, String associatedValue){
        try (Connection conn = DriverManager.getConnection(this.serverAddress, this.username, this.password); Statement stmt = conn.createStatement()) {
            String query = "use " + databaseName + ";";
            stmt.execute(query);
            String query2 = "update " + Table +
                            " set " + updateColumn + " = " + "'" + newUpdateValue + "'" +
                            " where " + asscoiatedColumn + " = " + "'" + associatedValue + "'" + ";";
            System.out.println(query2);
            stmt.execute(query2);
            System.out.println("Key set!");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    void remainingTime(String table, String column) {
        try (Connection conn = DriverManager.getConnection(this.serverAddress, this.username, this.password); Statement stmt = conn.createStatement()) {
            String query = "use " + databaseName + ";";
            stmt.execute(query);
            String query2 = "select * from " + table + " where " + column + " = '" + cdkeys + "' and expiry_date > now()";
            stmt.execute(query2);
            //calculate remaining time
            String query3 = "SELECT TIMESTAMPDIFF(minute, now(), expiry_date) AS leftMinute " +
                    "FROM " + table + " where " + column + " = '" + cdkeys + "';";
            ResultSet rs2 = stmt.executeQuery(query3);
            while (rs2.next()) {
                leftMinute = Integer.parseInt(rs2.getString("leftMinute"));
                System.out.println(leftMinute);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void insertKeys(String creater,String keys,int expiry_date, int loggedin, boolean free) {
        String column = "";

        switch (expiry_date){
            case 1:
                if (free) {
                    column = "freePass";
                } else {
                    column = "dayPass";
                }
                break;
            case 7:
                column = "weekPass";
                break;
            case 30:
                column = "monthPass";
                break;
            case 365:
                column = "yearPass";
                break;
            case 9999:
                column = "lifetimePass";
                break;
            default:
                column = "";
                break;
        }

        try (Connection conn = DriverManager.getConnection(this.serverAddress, this.username, this.password); Statement stmt = conn.createStatement()) {
            String query = "use " + databaseName + ";";
            stmt.execute(query);
            String query2 = "insert into apex (creater,cdkeys,expiry_date,loggedin,free) values ('" + creater + "','" + keys + "',NOW() + INTERVAL '" + expiry_date + "' day," + loggedin + "," + free + ");";
            System.out.println(query2);
            stmt.execute(query2);
            //update user table for each key generated
            String query3 = "update users " +
                            "set "+ column +" = " + column + " + 1 " +
                            "where userName = '" + creater + "';";
            System.out.println(query3);
            stmt.execute(query3);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "生成失败");
            throw new RuntimeException(e);
        }
    }


    Boolean fixLogin(String cdkeys){
        try (Connection conn = DriverManager.getConnection(this.serverAddress, this.username, this.password)){
            Statement stmt = conn.createStatement();
            String query = "use " + databaseName + ";";
            stmt.execute(query);
            String query2 = "update apex " +
                            "set loggedin = 0 where cdkeys = '" + cdkeys + "';";
            System.out.println(query2);
            stmt.execute(query2);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    void summary(){
        try (Connection conn = DriverManager.getConnection(this.serverAddress, this.username, this.password); Statement stmt = conn.createStatement()){
            String query = "use " + databaseName + ";";
            stmt.execute(query);
            String query2 = "select * from users where userName = '" + user + "';";
            ResultSet rs = stmt.executeQuery(query2);
            while (rs.next()){
                freePass = rs.getString("freePass");
                dayPass = rs.getString("dayPass");
                weekPass = rs.getString("weekPass");
                monthPass = rs.getString("monthPass");
                yearPass = rs.getString("yearPass");
                lifetimePass = rs.getString("lifetimePass");
            }
            JOptionPane.showMessageDialog(null, "体验卡: " + freePass + "\n" +
                    "天卡: " + dayPass+ "\n" +
                    "周卡: " + weekPass+ "\n" +
                    "月卡: " + monthPass+ "\n" +
                    "年卡: " + yearPass+ "\n" +
                    "终身卡: " + lifetimePass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "查询失败");
            throw new RuntimeException(e);
        }

    }


    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getCdkeys() {
        return cdkeys;
    }

    public void setCdkeys(String cdkeys) {
        this.cdkeys = cdkeys;
    }

    public int getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(int loggedin) {
        this.loggedin = loggedin;
    }

    public int getLeftMinute() {
        return leftMinute;
    }

    public void setLeftMinute(int leftMinute) {
        this.leftMinute = leftMinute;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
