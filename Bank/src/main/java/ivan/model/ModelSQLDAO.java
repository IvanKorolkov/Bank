package ivan.model;

import ivan.build.Autowired;
import ivan.build.Component;

import java.sql.*;

@Component
public class ModelSQLDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/bank";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static Connection connection;
    @Autowired
    private ApplicationSQLDAO applicationSQLDAO;

    public ModelSQLDAO() {
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

    public Model getModel(int id){
        Model model = new Model();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM model WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            model.setId(resultSet.getInt("id"));
            model.setClerkCount(resultSet.getInt("clerkCount"));
            model.setMaxClientCount(resultSet.getInt("maxClientCount"));
            model.setCountOfServedClients(resultSet.getInt("countOfServedClients"));
            model.setCountOfLostClients(resultSet.getInt("countOfLostClients"));
            model.setMaxQueue(resultSet.getInt("maxQueue"));
            model.setMinQueue(resultSet.getInt("minQueue"));
            model.setAverageQueue(resultSet.getInt("averageQueue"));
            model.setAverageWaitTime(resultSet.getInt("averageWaitTime"));
            model.setProfit(resultSet.getInt("profit"));

            model.setApplications(applicationSQLDAO.getApplicationsForModel(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return model;
    }

    public void addNewModel(Model model){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("""
                            INSERT INTO public.model(id,
                                                    clerkCount,
                                                    maxClientCount,
                                                    countOfServedClients,
                                                    countOfLostClients,
                                                    maxQueue,
                                                    minQueue,
                                                    averageQueue,
                                                    averageWaitTime,
                                                    profit)
                            VALUES (nextval('model_id_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?);""");

            setStatement(model, preparedStatement);
            for (Application a: model.getApplications()) {
                applicationSQLDAO.addNewApplication(a);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateModel(Model model){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    UPDATE public.model SET
                    clerkCount=?,
                    maxClientCount=?,
                    countOfServedClients=?,
                    countOfLostClients=?,
                    maxQueue=?,
                    minQueue=?,
                    averageQueue=?,
                    averageWaitTime=?,
                    profit=? WHERE id=?;""");
            setStatement(model, preparedStatement);
            preparedStatement.setInt(10, model.getId());
            preparedStatement.executeUpdate();
            for (Application a: model.getApplications()) {
                applicationSQLDAO.updateApplication(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setStatement(Model model, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, model.getClerkCount());
        preparedStatement.setInt(2, model.getMaxClientCount());
        preparedStatement.setInt(3, model.getCountOfServedClients());
        preparedStatement.setInt(4, model.getCountOfLostClients());
        preparedStatement.setInt(5, model.getMaxQueue());
        preparedStatement.setInt(6, model.getMinQueue());
        preparedStatement.setInt(7, model.getAverageQueue());
        preparedStatement.setInt(8, model.getAverageWaitTime());
        preparedStatement.setInt(9, model.getProfit());
    }
}
