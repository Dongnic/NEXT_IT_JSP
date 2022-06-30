import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardVO;

public class Main {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 1. mybatis-config.xml파일 읽음
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 2. 읽는 순간에 db접속하고 mapper파일들을 SqlSession에 저장
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			IFreeBoardDao freeBoardDao=session.getMapper(IFreeBoardDao.class);
			// 3. SqlSession으로부터 mapper파일 얻기
			FreeBoardVO freeBoard= freeBoardDao.getBoard(30);
			// 4. FreeBoardDao.getBoard하는 순간 freeBoard.xml의 쿼리가 실행(DaoOracle 실행x)
			
			//BlogMapper mapper = session.getMapper(BlogMapper.class);
			//Blog blog = mapper.selectBlog(101);
			// getBoard할 때 FreeBoard.xml의 getBoard 쿼리가 실행이된다
			System.out.println(freeBoard);
			System.out.println(freeBoard.getBoWriter());
		}
		
		
	}


}