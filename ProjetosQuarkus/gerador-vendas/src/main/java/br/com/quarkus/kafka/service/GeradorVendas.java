package br.com.quarkus.kafka.service;

import java.math.BigDecimal;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import br.com.quarkus.kafka.model.Venda;

@ApplicationScoped
public class GeradorVendas {
	
	private static Random random = new Random();
	private static long operacao = 0;
	private static BigDecimal valorIngresso = BigDecimal.valueOf(500);
	
	@Outgoing("vendas")
	public Venda gerarVenda() {
		long cliente = random.nextLong();
		int qtdIngressos = random.nextInt(10);

		return new Venda(operacao++, cliente, qtdIngressos, valorIngresso.multiply(BigDecimal.valueOf(qtdIngressos)));
	}

}
