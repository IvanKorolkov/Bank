package ivan.model;

import ivan.build.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationSQLDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/bank";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static Connection connection;

    public ApplicationSQLDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Application> getApplicationsForModel(int  modelId){
        List<Application> applications = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM application WHERE model_id = ?;");
            preparedStatement.setInt(1, modelId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Application application = new Application();
                application.setId(resultSet.getInt("id"));
                application.setServiceTime(resultSet.getInt("serviceTime"));
                application.setTimeOfReceipt(resultSet.getDate("timeOfReceipt"));
                application.setProfit(resultSet.getInt("profit"));
                applications.add(application);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return applications;
    }

    public void addNewApplication(Application application){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("""
                            INSERT INTO public.application (id,
                                                    timeOfReceipt,
                                                    serviceTime,
                                                    profit,
                                                    model_id)
                            VALUES (nextval('application_id_seq'), ?, ?, ?, ?);""");

            setStatement(application, preparedStatement);

            PreparedStatement seqStat = connection.prepareStatement("SELECT last_value FROM model_id_seq;");
            ResultSet resultSet = seqStat.executeQuery();
            resultSet.next();
            int nextVal = resultSet.getInt("last_value");
            preparedStatement.setInt(4, nextVal);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateApplication(Application application){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    UPDATE public.application SET
                    timeOfReceipt=?,
                    serviceTime=?,
                    profit=? WHERE id=?;""");
            setStatement(application, preparedStatement);
            preparedStatement.setInt(4, application.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setStatement(Application application, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setDate(1, new Date(application.getTimeOfReceipt().getTime()));
        preparedStatement.setInt(2, application.getServiceTime());
        preparedStatement.setInt(3, application.getProfit());
    }
}
