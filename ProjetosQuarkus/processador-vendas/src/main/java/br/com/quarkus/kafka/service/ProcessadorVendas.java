package br.com.quarkus.kafka.service;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import br.com.quarkus.kafka.model.Venda;
import io.smallrye.common.annotation.Blocking;

@ApplicationScoped
public class ProcessadorVendas {
		
	@Blocking
	@Incoming("vendas")
	public void processarVenda(Venda venda) throws InterruptedException {
		
		if(new Random().nextBoolean()) {
			venda.setStatus("APROVADA");
		}
		else {
			venda.setStatus("REPROVADA");
		}
		
		Thread.sleep(500);
		System.out.println(venda);
		System.out.println("teste");
		
	}


}
