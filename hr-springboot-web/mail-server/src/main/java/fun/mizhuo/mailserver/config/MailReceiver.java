package fun.mizhuo.mailserver.config;

import fun.mizhuo.hrserver.exception.HrException;
import fun.mizhuo.hrserver.model.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author: Mizhuo
 * @time: 2020/10/19 10:02 下午
 * @description: 消息接收者
 */
public class MailReceiver {

   @Autowired
   JavaMailSender mailSender;

   @RabbitListener(queues = "mizhuo.mail")
   public void handler(Employee employee) throws HrException {
      try {
         //收到消息，发送邮件
         MimeMessage message = mailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(message);
         helper.setTo(employee.getEmail());
      }catch (MessagingException e) {
         throw new HrException("发送邮件失败!",e);
      }
   }
}
