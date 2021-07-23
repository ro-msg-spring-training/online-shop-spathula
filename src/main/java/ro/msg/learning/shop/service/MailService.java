package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.CustomerOrder;
import ro.msg.learning.shop.exception.OrderException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class MailService {
    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    private final JavaMailSender mailSender;

    @Value("${template}")
    private String templateFile;

    @Value("${subject}")
    private String subject;

    public void orderSuccessEmail(CustomerOrder order) {
        sendMessage(order.getCustomer().getEmailAddress(), subject, generateBody(order));
    }

    private void sendMessage(String to, String subject, String text) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            helper.setFrom("noreply@msg.ro");
            helper.setTo(to);
            helper.setSubject(subject);

            if (templateFile.contains(".html"))
                helper.setText(text, true);
            else
                helper.setText(text);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new OrderException(e.toString());
        }
    }

    private String generateBody(Object templateContext) {
        try {
            String template = new String(Files.readAllBytes(Path.of(new ClassPathResource(templateFile).getFile().getPath())));
            Expression expression = new SpelExpressionParser().parseExpression(template, new TemplateParserContext());
            return (String) expression.getValue(new StandardEvaluationContext(templateContext));
        } catch (IOException e) {
            throw new OrderException(e.toString());
        }
    }
}
