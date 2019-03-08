package org.pace.configuration;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
 
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
import com.zaxxer.hikari.HikariDataSource;
 
@Configuration
@EnableJpaRepositories(basePackages = "org.pace.repositories.secondary",
        entityManagerFactoryRef = "entityManagerSec",
        transactionManagerRef = "transactionalManagerSec")
@EnableTransactionManagement
public class JpaConfigSec {
 
    @Autowired
    private Environment environment;
 
    @Value("${db2.datasource.maxPoolSize}")
    private int maxPoolSize;
    
  
 
    @Bean
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSourceProperties dataSourcePropertiesSec(){
        return new DataSourceProperties();
    }
 
    @Bean
    public DataSource dataSourceSec() {
        DataSourceProperties dataSourcePropertiesSec = dataSourcePropertiesSec();
            HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
                    .create(dataSourcePropertiesSec.getClassLoader())
                    .driverClassName(dataSourcePropertiesSec.getDriverClassName())
                    .url(dataSourcePropertiesSec.getUrl())
                    .username(dataSourcePropertiesSec.getUsername())
                    .password(dataSourcePropertiesSec.getPassword())
                    .type(HikariDataSource.class)
                    .build();
            dataSource.setMaximumPoolSize(maxPoolSize);
            return dataSource;
    }
    
    @Bean(name="entityManagerSec")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySec() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSourceSec());
        factoryBean.setPackagesToScan(new String[] { "org.pace.model" });
        factoryBean.setJpaVendorAdapter(jpaVendorAdapterSec());
        factoryBean.setJpaProperties(jpaPropertiesSec());
        return factoryBean;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapterSec() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        return hibernateJpaVendorAdapter;
    }
 

    private Properties jpaPropertiesSec() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("db2.datasource.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("db2.datasource.hibernate.hbm2ddl.method"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("db2.datasource.hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("db2.datasource.hibernate.format_sql"));
        if(StringUtils.isNotEmpty(environment.getRequiredProperty("db2.datasource.defaultSchema"))){
            properties.put("hibernate.default_schema", environment.getRequiredProperty("db2.datasource.defaultSchema"));
        }
        return properties;
    }
 
    @Bean(name="transactionalManagerSec")
    @Autowired
    public PlatformTransactionManager transactionManagerSec( @Qualifier("entityManagerSec") EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }
 
}