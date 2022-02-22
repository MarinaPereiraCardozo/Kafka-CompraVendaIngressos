package br.com.quarkus.kafka.config;

import br.com.quarkus.kafka.model.Venda;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class VendaDeserializer extends ObjectMapperDeserializer<Venda>{
	
	public VendaDeserializer() {
		super(Venda.class);
	}
}