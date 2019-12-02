import java.sql.Connection;
import java.sql.SQLException;

public class DaoManager {

    protected ConnectionFactory connectionFactory = null;
    protected Connection conn = null;

    public DaoManager(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Connection getConnection() throws SQLException {
        if (this.conn == null) {
            this.conn = this.connectionFactory.getConnection();
        }
        return this.conn;
    }

    public Connection getTransConnection() throws SQLException {
        if (this.conn == null) {
            this.getConnection().setAutoCommit(false);
        }
        return this.conn;
    }

    public void close() throws SQLException {
        try {
            if (this.conn != null && !this.conn.isClosed())
                this.conn.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public Object transaction(DaoCommand command) {
        try {
            this.conn.setAutoCommit(false);
            Object returnValue = command.execute(this);
            this.conn.commit();
            return returnValue;
        }
        catch (Exception e) {
            try {
                this.conn.rollback();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        finally {
            try {
                this.conn.setAutoCommit(true);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Object executeAndClose(DaoCommand command) {
        try {
            return command.execute(this);
        }
        finally {
            try {
                this.getConnection().close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Object transactionAndClose(final DaoCommand command) {
        return executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.transaction(command);
            }
        });
    }

    public Dao<Book> getBookDao() throws SQLException {
        return new BookDaoImpl(this.getConnection());
    }

    public Dao<Student> getStudentDao() throws SQLException {
        return new StudentDaoImpl(this.getConnection());
    }

    public Dao<Reservation> getReservationDao() throws SQLException {
        return new ReservationDaoImpl(this.getConnection());
    }

    public Dao<Checkout> getCheckoutDao() throws SQLException {
        return new CheckoutDaoImpl(this.getConnection());
    }
}
