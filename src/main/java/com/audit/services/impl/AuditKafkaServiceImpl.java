package com.audit.services.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.audit.repo.AuditRepo;
import com.google.gson.Gson;
import com.model.entity.Audit;
import com.utilities.log.Log;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuditKafkaServiceImpl {

	private AuditRepo auditRepo;

	private Gson g;

	@KafkaListener(topics = "log-topic", groupId = "my-consumer-group")
	public void consumeLogs(ConsumerRecord<String, String> record, Acknowledgment ack) {
		try {
			Log.info(this.getClass().getSimpleName(), "startConsuming",
					"Received message: key=%s, value=%s, partition=%s, offset=%s", record.key(), record.value(),
					record.partition(), record.offset());

			processMessage(record.value());

			ack.acknowledge();
		} catch (Exception e) {
			Log.error(this.getClass().getSimpleName(), "startConsuming", "Error processing message: %s,%n%s",
					record.value(), ExceptionUtils.getStackTrace(e));
		}
	}

	@KafkaListener(topics = "audit-topic", groupId = "my-consumer-group")
	public void consumeAudit(ConsumerRecord<String, String> record, Acknowledgment ack) {
		try {
			Log.info(this.getClass().getSimpleName(), "startConsuming",
					"Received message: key=%s, value=%s, partition=%s, offset=%s", record.key(), record.value(),
					record.partition(), record.offset());

			auditRepo.save(g.fromJson(record.value(), Audit.class));

			ack.acknowledge();
		} catch (Exception e) {
			Log.error(this.getClass().getSimpleName(), "startConsuming", "Error processing message: %s,%n%s",
					record.value(), ExceptionUtils.getStackTrace(e));
		}
	}

	private void processMessage(String message) {
		
		Log.info(this.getClass().getSimpleName(), "processMessage", "Processing message: %s", message);

	}
}
