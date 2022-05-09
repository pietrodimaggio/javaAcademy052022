package com.techedgegroup.accademy.course.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

	Logger logger=LoggerFactory.getLogger(AmqpConfig.class);
	
	@Bean
	public Queue createCourseQueue() {
		logger.info("Creata coda corsi");
		
		return new Queue("corsi");
	}
	
	@Bean
	MessageConverter messageConverter(){
	    return new Jackson2JsonMessageConverter();
	}
}
