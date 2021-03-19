package com.cos.mybatisex01.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class DataAccessConfig {

   //sqlSession을 만들어서 db에 접근을 함
   //1.DataSource 등록, yml에 있는 datasource를 가져옴 yml은 ioc에 등록되기 때문
   //2.xml 파일 연결
   @Bean
   public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception { // 공장에서 sqlSession을 때려줌, ioc에 등록 되면 데이터 소스를 넣어줌
      
      SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean(); //공장을 하나 만든다.
      sessionFactoryBean.setDataSource(dataSource); 
      sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mapper/*.xml"));
      
      return sessionFactoryBean.getObject();
      
   }
   
   // SqlSession 만들기
   // 1. SqlSession은 인터페이스여서 직접 만들면 번거롭기 때문에 SqlSessionTemplate 사용(편함)
   @Bean
   public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
      //SqlSessionTemplate s = new SqlSessionTemplate(sqlSessionFactory);
      return new SqlSessionTemplate(sqlSessionFactory);
      
   }
}