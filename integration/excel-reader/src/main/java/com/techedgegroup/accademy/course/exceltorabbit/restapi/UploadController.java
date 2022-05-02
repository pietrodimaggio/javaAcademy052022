package com.techedgegroup.accademy.course.exceltorabbit.restapi;

import java.io.InputStream;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UploadController {
	
	Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Value("${default.parser.configuration}")
	String defaultConfigurationFile;
	
	@Value("${default.parser.path}")
	String defaultConfigurationPath;
	
	@Value("${upload.queue.name}")
	String uploadQueuename;
	
//	@Autowired
//	DataParserService dataParserService;
	
	@Autowired
	private RabbitTemplate messageBroker;
			
	@PostMapping("/upload")
	public String upload(@RequestPart("file") MultipartFile file) {
		try {
			InputStream is=file.getInputStream();
			String filename = file.getOriginalFilename();
//			Data data =dataParserService.parseData(is, filename);
//			data.setRequestId(requestIdService.getId(user));
//			data.setUser(user);
//			response.setRequestId(data.getRequestId());
//			response.setStatus(UploadResponseStatus.PUBLISHED);
			
//			ObjectMapper mapper = new ObjectMapper();
//			String json=mapper.writeValueAsString(data);
//			
//			logger.debug("output:"+ json);
//			
//			messageBroker.convertAndSend(uploadQueuename,json);
			
			return "ok";
		}
		catch(Exception e) {
			logger.error("Error on upload",e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		}		
	}
		
}
