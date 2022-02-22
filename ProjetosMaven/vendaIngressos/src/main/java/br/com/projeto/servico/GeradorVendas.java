package br.com.projeto.servico;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import br.com.projeto.model.Venda;
import br.com.projeto.serializer.VendaSerializer;

public class GeradorVendas {

	private static Random random = new Random();
	private static long operacao = 0;
	private static BigDecimal valorIngresso = BigDecimal.valueOf(500);

	public static void main(String[] args) throws InterruptedException {

		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VendaSerializer.class.getName());

		try (KafkaProducer<String, Venda> producer = new KafkaProducer<String, Venda>(properties)) {

			while (true) {
				Venda venda = gerarVenda();
				ProducerRecord<String, Venda> record = new ProducerRecord<String, Venda>("venda-ingressos", venda);
				producer.send(record);
				Thread.sleep(200);
			}
		}

	}

	private static Venda gerarVenda() {
		long cliente = random.nextLong();
		int qtdIngressos = random.nextInt(10);

		return new Venda(operacao++, cliente, qtdIngressos, valorIngresso.multiply(BigDecimal.valueOf(qtdIngressos)));
	}

}
