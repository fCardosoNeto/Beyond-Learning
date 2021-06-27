//package net.codejava.email.envio;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Mailer { //propriedade para enviar a mensagem
//	
//	@Autowired
//	private JavaMailSender javaMailSender;
//	
//	public void enviar(Mensagem mensagem) { 
//		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//		
//		simpleMailMessage.setFrom(mensagem.getRemetente()); //vai pegar a mensagem do remetente
//		simpleMailMessage.setTo(mensagem.getDestinatarios() //configurar a lista de destinatários, funcionando com array, para funcionar com uma lista
//				.toArray(new String[mensagem.getDestinatarios().size()])); //método do array
//		simpleMailMessage.setSubject(mensagem.getAssunto()); //vai pegar o assunto da mensagem
//		simpleMailMessage.setText(mensagem.getCorpo()); //vai pegar o corpo da mensagem
//		
//		javaMailSender.send(simpleMailMessage); //envio do email
//	}
//
//}
