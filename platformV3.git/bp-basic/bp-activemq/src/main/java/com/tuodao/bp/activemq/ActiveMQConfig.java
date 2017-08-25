package com.tuodao.bp.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

/**
 * amq配置
 * 
 * @author hechuan
 *
 * @created 2017年8月8日
 *
 * @version 1.0.0
 */
@Configuration
@EnableJms
public class ActiveMQConfig {

	// topic模式的ListenerContainer
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		bean.setPubSubDomain(true);
		initPolicy(activeMQConnectionFactory);
		bean.setConnectionFactory(activeMQConnectionFactory);
		return bean;
	}

	// queue模式的ListenerContainer
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		initPolicy(activeMQConnectionFactory);
		bean.setConnectionFactory(activeMQConnectionFactory);
		return bean;
	}

	/**
	 * 初始重试策略
	 * @param activeMQConnectionFactory
	 */
	private void initPolicy(ActiveMQConnectionFactory activeMQConnectionFactory) {
		
		RedeliveryPolicy policy = activeMQConnectionFactory.getRedeliveryPolicy();
		// 初次执行，0延迟
		policy.setInitialRedeliveryDelay(0);
		// 每次重试延迟为1秒执行
		policy.setRedeliveryDelay(1000);
		// 最大重发次数
		policy.setMaximumRedeliveries(5);
		// 每次重试间隔时间递增倍数,重试5次，每次的时间间隔依次为1s 2s 4s 8s 16s
		policy.setBackOffMultiplier(2);
		// 默认false，true表示启用指数倍数递增的方式增加延迟时间
		policy.setUseExponentialBackOff(true);
		
		activeMQConnectionFactory.setRedeliveryPolicy(policy);
	}

}
