package cs544.exercise13_1.Aspect;

import cs544.exercise13_1.IEmailSender;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Aspect
@Component
public class LoggingAdvice {
    @After(value = "execution(* cs544.exercise13_1.IEmailSender.sendEmail(..)) && args(email,message)", argNames = "joinPoint,email,message")
    public void afterSendingEmail(JoinPoint joinPoint, String email, String message) {
        System.out.println(LocalDateTime.now(ZoneOffset.UTC) + " method=" + joinPoint.getSignature().getName() + " " +
                "address=" + email + " message=" + message + "\noutgoing email server=" +
                ((IEmailSender) joinPoint.getTarget()).getOutgoingMailServer());
    }

}
