import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {
  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/library_catalog_test", null, null);
  }
  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteMediaQuery = "DELETE FROM media *;";
      String deletePatronsQuery = "DELETE FROM patrons *;";
      con.createQuery(deleteMediaQuery).executeUpdate();
      con.createQuery(deletePatronsQuery).executeUpdate();
    }
  }


}
