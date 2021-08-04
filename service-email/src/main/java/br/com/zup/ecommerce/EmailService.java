package br.com.zup.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class EmailService {
    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        try (var service = new KafkaService(EmailService.class.getSimpleName(), "ECOMMERCE_SEND_EMAIL",
                emailService::parse,
                String.class,
                Map.of())) { //<- executa uma função para cada para cada mensagem querecebo
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("******************************************");
        System.out.println("Sending email...");
        System.out.println("Chave: " + record.key());
        System.out.println("Valor: " + record.value());
        System.out.println("Partição: " + record.partition());
        System.out.println("Offset: " + record.offset());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignoring
            e.printStackTrace();
        }
        System.out.println("Email processed and sent!");
    }
}
