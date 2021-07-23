package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class StrategyConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public Strategy chooseStrategy(@Value("${strategy}") StrategyName strategy) {
        if (strategy == StrategyName.SINGLELOCATIONSTRATEGY) {
            return new SingleLocationStrategy();
        } else if (strategy == StrategyName.MOSTABUNDANTSTRATEGY) {
            return new MostAbundantStrategy();
        } else if (strategy == StrategyName.GREEDYSTRATEGY) {
            return new GreedyStrategy();
        }
        return null;
    }
}
