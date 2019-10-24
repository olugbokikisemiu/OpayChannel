package dao;

import dao.dataFactory.SessionFactory;
import dao.model.ChannelJournal;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class DataLayer {
    public void PersistJournal(String db, String url, String user, String password, ChannelJournal channel){
        SqlSessionFactory sessionFactory = new SessionFactory().Session(db, url, user, password);
        try (SqlSession session = sessionFactory.openSession()) {
            session.insert("insertJournal", channel);
            session.commit();
            session.close();
        }
    }
}
