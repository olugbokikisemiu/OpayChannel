package dao.dataFactory;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class OpaySourceFactory implements DataSourceFactory {
    private Properties props;

    @Override
    public void setProperties(Properties properties) {
        props = properties;
    }

    @Override
    public DataSource getDataSource() {
        PooledDataSource pool = new PooledDataSource();
        pool.setDriver(props.getProperty("driver"));
        pool.setUrl(props.getProperty("url"));
        pool.setUsername(props.getProperty("user"));
        pool.setPassword(props.getProperty("password"));
        return pool;
    }
}
