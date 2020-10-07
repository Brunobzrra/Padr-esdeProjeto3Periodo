package model.autenticacao;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {

	public void enviarEmail(String senha, String remetente, String destinatario, String mensagem, String assunto) throws AddressException, MessagingException {
		Properties props = new Properties();
		// conexões com o gmail
		props.put("mail.smtp.user", remetente);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "467");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(remetente, senha);
			}
		});
		session.setDebug(true);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(remetente));
		Address[] toUser = InternetAddress.parse(destinatario);
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(assunto);
		message.setText(mensagem);
		Transport.send(message);
	}

	public void enviarEmailDeCompra(String senha, String remetente, String destinatario1, String destinatario2,
			String mensagem, String assunto) throws AddressException, MessagingException {
		Properties props = new Properties();
		// conexões com o gmail
		props.put("mail.smtp.user", remetente);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "467");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(remetente, senha);
			}
		});
		session.setDebug(true);
		for (int i = 0; i < 2; i++) {
			Address[] toUser = null;
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remetente));
			if (i == 0) {
				toUser = InternetAddress.parse(destinatario1);
			} else {
				toUser = InternetAddress.parse(destinatario2);
			}
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(assunto);
			message.setText(mensagem);
			Transport.send(message);
		}

	}
}
