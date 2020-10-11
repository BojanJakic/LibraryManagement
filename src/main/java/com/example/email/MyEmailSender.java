package com.example.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class MyEmailSender {

	@Autowired
	private JavaMailSender sender;

	public MyEmailSender() {
	}

	public String prepareRegistrationMessage(String token, String firstName, String lastName) {

		String message = "<h1>MOJA BIBLIOTEKA</h1>" + "<h3>Dobrodosli " + firstName + " " + lastName + "</h3>"
				+ "<h5>Uspijesno ste se registovali i postali clan biblioteke"
				+ ", da biste koristili svoj korisnicki racun morate ga aktivirati "
				+ "klikom na ponudjeni link.Aktivacioni Link je validan naredna 24 sata</h5><br/><br/>"
				+ "<a href=http://localhost:8080/account_activation?param=" + token + ">Aktivacioni link</a>";

		return message;

	}

	public String resendLinkMessage(String token) {

		String message = "<h1>MOJA BIBLIOTEKA</h1>" + "<h3>Vas novi aktivacioni link :</h3>"
				+ "<a href=http://localhost:8080/account_activation?param=" + token + ">Aktivacioni link</a>";

		return message;
	}

	public void sendMessage(String recipient, String text, String subject) throws MessagingException {

		//SimpleMailMessage mailMessage = new SimpleMailMessage();

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(recipient);
		helper.setFrom("bojanjakic1@gmail.com");
		helper.setSubject(subject);
		helper.setText(text, true);

		sender.send(message);

	}

}
