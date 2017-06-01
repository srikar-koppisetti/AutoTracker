package com.srikar;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan
@EnableWebMvc
public class AppConfig {
	
	
	//set Email Alert properties
	@Bean
	public JavaMailSender getMailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("from email");
        mailSender.setPassword("from email password");
        
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
 
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
        
	}
	
	
			/*spring.mail.host = smtp.gmail.com
			spring.mail.username = from email
			spring.mail.password = ******

			spring.mail.properties.mail.smtp.auth = true
			spring.mail.properties.mail.smtp.socketFactory.port = 465
			spring.mail.properties.mail.smtp.socketFactory.class = javax.net.ssl.SSLSocketFactory
			spring.mail.properties.mail.smtp.socketFactory.fallback = false*/
	
}
