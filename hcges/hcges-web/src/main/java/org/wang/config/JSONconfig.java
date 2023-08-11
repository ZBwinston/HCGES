package org.wang.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JSONconfig {

        @Bean
        public HttpMessageConverters fastJsonHttpMessageConverter() {
            // 创建一个消息转换器
            FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();

            // 创建一个fastjson的配置类
            FastJsonConfig fastJsonConfig = new FastJsonConfig();
            // 设置序列化方式
            fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

            // 为转换器设置配置信息
            messageConverter.setFastJsonConfig(fastJsonConfig);
            // 创建http消息转换器
            return new HttpMessageConverters(messageConverter);
        }


}
