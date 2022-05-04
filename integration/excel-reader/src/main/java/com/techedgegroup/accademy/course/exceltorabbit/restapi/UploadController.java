package com.techedgegroup.accademy.course.exceltorabbit.restapi;

import java.io.InputStream;
import java.util.List;

import com.techedgegroup.accademy.course.exceltorabbit.service.ExcelDataParser;
import com.techedgegroup.accademy.course.rabbitimporter.model.TeacherData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UploadController {

	Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Value("${upload.queue.name}")
	String uploadQueuename;

	@Autowired
	ExcelDataParser excelDataParser;

	@Autowired
	private RabbitTemplate messageBroker;

	@Operation(summary = "Upload file")
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String upload(@RequestPart("file") MultipartFile file) {
		try {
			InputStream is = file.getInputStream();
			String filename = file.getOriginalFilename();

			List<TeacherData> data = excelDataParser.parse(filename, is);

			data.stream().forEach(item -> {
				messageBroker.convertAndSend(uploadQueuename, item);
			});

			return "ok";
		} catch (Exception e) {
			logger.error("Error on upload", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
