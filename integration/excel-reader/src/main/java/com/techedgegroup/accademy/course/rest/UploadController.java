package com.techedgegroup.accademy.course.rest;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techedgegroup.accademy.course.model.TeacherData;
import com.techedgegroup.accademy.course.service.ExcelDataParser;

@RestController
public class UploadController {

	Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private ExcelDataParser excelDataParser;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String uploadExcel(@RequestPart("file") MultipartFile file) throws Exception {

		InputStream is = file.getInputStream();
		String filename = file.getOriginalFilename();

		List<TeacherData> data = excelDataParser.parse(filename, is);

		data.stream().forEach(item -> {
			rabbitTemplate.convertAndSend("corsi", item);
		});

		logger.info("Ricevuto File:" + filename + ", dati:" + data);

		return "ok";

	}
}
