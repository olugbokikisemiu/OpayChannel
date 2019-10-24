package dao.dataFactory;

import dao.Mapper.ChannelMapper;
import dao.model.ChannelJournal;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

public class SessionFactory {

    public SqlSessionFactory Session(String db, String url, String user, String password){
        Properties prop = new Properties();
        prop.setProperty("driver","com.mysql.cj.jdbc.Driver");
        prop.setProperty("url", String.format("%s%s/%s", "jdbc:mysql://",url, db));
        prop.setProperty("user", user);
        prop.setProperty("password", password);

        OpaySourceFactory mdsf = new OpaySourceFactory();
        mdsf.setProperties(prop);
        DataSource ds = mdsf.getDataSource();

        TransactionFactory trFact = new JdbcTransactionFactory();
        Environment environment = new Environment("development", trFact, ds);
        Configuration config = new Configuration(environment);
        config.addMapper(ChannelMapper.class);

        return new SqlSessionFactoryBuilder().build(config);
    }
}
