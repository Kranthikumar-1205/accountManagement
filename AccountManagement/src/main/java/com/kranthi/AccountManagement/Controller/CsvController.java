package com.kranthi.AccountManagement.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kranthi.AccountManagement.ServiceImpl.CsvService;

@RestController
@RequestMapping("/api")
public class CsvController {
	
	private CsvService csvService;
	
	public CsvController(CsvService csvService) {
		this.csvService = csvService;
	}
	
	@PostMapping("users/import-csv")
	public ResponseEntity<String> importCsv(@RequestParam("file") MultipartFile file){
//		System.out.println("Received file: " + file.getOriginalFilename());
		String result = csvService.importCsv(file);
		return ResponseEntity.ok(result);
	}

}
