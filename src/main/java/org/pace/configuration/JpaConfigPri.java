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
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(basePackages = "org.pace.repositories.primary",
        entityManagerFactoryRef = "entityManagerPri",
        transactionManagerRef = "transactionalManagerPri")
@EnableTransactionManagement
public class JpaConfigPri {
 
    @Autowired
    private Environment environment;
 
    @Value("${db1.datasource.maxPoolSize}")
    private int maxPoolSize;
    
  
 
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "db1.datasource")
    public DataSourceProperties dataSourcePropertiesPri(){
        return new DataSourceProperties();
    }
 
    @Bean
    public DataSource dataSourcePri() {
        DataSourceProperties dataSourcePropertiesPri = dataSourcePropertiesPri();
            HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
                    .create(dataSourcePropertiesPri.getClassLoader())
                    .driverClassName(dataSourcePropertiesPri.getDriverClassName())
                    .url(dataSourcePropertiesPri.getUrl())
                    .username(dataSourcePropertiesPri.getUsername())
                    .password(dataSourcePropertiesPri.getPassword())
                    .type(HikariDataSource.class)
                    .build();
            dataSource.setMaximumPoolSize(maxPoolSize);
            return dataSource;
    }
    
    @Bean(name="entityManagerPri")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPri() throws NamingException {
    	
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSourcePri());
        factoryBean.setPackagesToScan(new String[] { "org.pace.model" });
        factoryBean.setJpaVendorAdapter(jpaVendorAdapterPri());
        factoryBean.setJpaProperties(jpaPropertiesPri());
        return factoryBean;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapterPri() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        return hibernateJpaVendorAdapter;
    }
 

    private Properties jpaPropertiesPri() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("db1.datasource.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("db1.datasource.hibernate.hbm2ddl.method"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("db1.datasource.hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("db1.datasource.hibernate.format_sql"));
        if(StringUtils.isNotEmpty(environment.getRequiredProperty("db1.datasource.defaultSchema"))){
            properties.put("hibernate.default_schema", environment.getRequiredProperty("db1.datasource.defaultSchema"));
        }
        return properties;
    }
 
    @Bean(name="transactionalManagerPri")
    @Autowired
    public PlatformTransactionManager transactionManagerPri( @Qualifier("entityManagerPri") EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }
 
}