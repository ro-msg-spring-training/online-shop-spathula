package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StrategyConfig {
    @Bean
    public Strategy chooseStrategy(@Value("${strategy}") StrategyName strategy) {
        if (strategy == StrategyName.SingleLocationStrategy) {
            return new SingleLocationStrategy();
        } else if (strategy == StrategyName.MostAbundantStrategy) {
            return new MostAbundantStrategy();
        }
        return null;
    }
}
