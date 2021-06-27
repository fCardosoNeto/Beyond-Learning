//package net.codejava.email;
//
//import java.util.Arrays;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import net.codejava.email.envio.Mailer;
//import net.codejava.email.envio.Mensagem;
//
//public class SpringEmailMain {
//
//	public static void main(String[] args) {
//	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
//			SpringEmailMain.class.getPackage().getName());
//	
//		Mailer mailer = applicationContext.getBean(Mailer.class); //
//		mailer.enviar(new Mensagem("beyond learning <vyuri804@gmail.com> ", //nas aspas vai o nome fantasia e no <> o nome do email
//			Arrays.asList("<cardoso19042000@gmail.com>") //manda para o email que voce quer 
//				, "dasdasda", "sadasdasdas \n\n adsasdasdasd")); // primeiro texto o titulo, segundo texto o corpo do email
//		
//		applicationContext.close();
//		
//		System.out.println("Fim!");
//	}
//}