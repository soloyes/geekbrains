package mybatis.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractService {
    private static final String RESOURCE = "2.xml";

    final SqlSessionFactory sqlSessionFactory;

    final SqlSession sqlSession;

    public AbstractService() throws IOException {
        final InputStream inputStream = Resources.getResourceAsStream(RESOURCE);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }
}
