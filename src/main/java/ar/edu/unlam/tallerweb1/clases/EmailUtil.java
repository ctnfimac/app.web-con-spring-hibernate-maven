package ar.edu.unlam.tallerweb1.clases;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	public static void sendEmail(Session session, String emailDeDestino, String asunto, String mensaje ){
		MimeMessage msg = new MimeMessage(session);
		
		try {
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress("christianipc.1987@gmail.com","Christian Peralta"));
			msg.setReplyTo(InternetAddress.parse("info@christianperalta.com", false));
			msg.setSubject(asunto,"UTF-8");
			msg.setText(mensaje,"UTF-8");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDeDestino,false));
			Transport.send(msg);
			System.out.println("Email enviado");
		} catch (/*MessagingException*/ Exception e) {
			e.printStackTrace();
		}  
	}
}
