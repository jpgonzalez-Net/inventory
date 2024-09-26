package com.jp.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.jp.inventory.repository.ItemRepo;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan("com.jp.inventory.respository")
@EntityScan("com.jp.inventory.model.Item")
@EnableJpaRepositories({ "com.jp.model.Item", "org.jp.resource.ItemResource" })
@Configuration
public class InventoryApplication {

	@Bean
	@Primary
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.jp.model.*");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return em;
	}

	// @Bean
	// ItemRepo itemRepo() {
	// return new ItemRepo() {

	// };
	// }

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

}
