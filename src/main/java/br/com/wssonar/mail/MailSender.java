package br.com.wssonar.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.time.DurationFormatUtils;

import br.com.wssonar.model.WebService;

public class MailSender {

	private static Date lastSentEmailDate;
	
	/**
	 * Sends an e-mail in case the web service goes back online
	 * @param webService
	 * @param timeOffline
	 */
	public static void backOnlineEmail(WebService webService, String timeOffline) {
		String subject = webService.getWsName() + " voltou ao ar";
		String text = subject + ", depois de " + timeOffline + " indisponível. Mais informações no Admin.";

		sendMail(subject, text);
	}

	/**
	 * Sends an e-mail in case the web service goes offline
	 * @param webService
	 */
	public static void errorEmail(WebService webService) {
		String subject = webService.getWsName() + " fora do ar.";
		String text = subject + " Mais informações no Admin.";

		sendMail(subject, text);
	}
	
	/**
	 * Method responsible to send the e-mails
	 * @param subject
	 * @param text
	 */
	private static void sendMail(String subject, String text) {

		if(isAbleToSendEmail()) {

			final String username = "";
			final String password = "";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("FROM"));
				message.setRecipients(Message.RecipientType.TO, 
							new InternetAddress[]{
								new InternetAddress("example@fakeemail.com.br"),
								new InternetAddress("example2@fakeemail.com.br")
							});
				message.setSubject(subject);
				message.setText(text);

				Transport.send(message);

				lastSentEmailDate = new Date();

				System.out.println("Email Sent!");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("Email not sent, too soon.");
		}
	}
	
	/**
	 * Checks if the last e-mail was sent in the last 8 hours
	 */
	private static boolean isAbleToSendEmail() {
		
		if(lastSentEmailDate == null) {
			lastSentEmailDate = new Date();
			return false;
		}

		Date now = new Date();

		Integer hoursSinceLastEmail = Integer.parseInt(DurationFormatUtils.formatPeriod(lastSentEmailDate.getTime(), now.getTime(), "mm"));

		System.out.println("TIME SINCE LAST EMAIL: " + hoursSinceLastEmail + "m");

		// 8 hours
		if(hoursSinceLastEmail >= 480) { 
			return true;
		}

		return false;
	}

}
