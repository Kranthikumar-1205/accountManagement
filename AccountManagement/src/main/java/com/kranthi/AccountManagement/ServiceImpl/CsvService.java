package com.kranthi.AccountManagement.ServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kranthi.AccountManagement.Model.Users;
import com.kranthi.AccountManagement.Repo.UserRepo;

@Service
public class CsvService {
	
	private UserRepo repo;
	
	public CsvService(UserRepo repo) {
		this.repo = repo;
	}

	public String importCsv(MultipartFile file) {
		int insertedCount = 0;
		int skippedCount = 0;
		
		List<String> skippedDetails = new ArrayList<String>();
		
		
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            br.readLine(); // skip header
            
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                
                if(data.length<7) {
                	continue;
                }
                String email = data[1].trim();
                String accountNumber = data[3].trim();
                String phoneNumber = data[4].trim();
                boolean exists = repo.findByEmail(email).isPresent() ||
                        repo.findByAccountNumber(accountNumber).isPresent() ||
                        repo.findByPhoneNumber(phoneNumber).isPresent();

			       if (exists) {
			           skippedCount++;
			           skippedDetails.add("Email: " + email + ", Account: " + accountNumber + ", Phone: " + phoneNumber);
			           continue;
			       }

                Users user = new Users();
                user.setName(data[0].trim());
                user.setEmail(email);
                user.setPassword(data[2].trim());
                user.setAccountNumber(accountNumber);
                user.setPhoneNumber(phoneNumber);
                user.setGender(data[5].trim());
                user.setAddress(data[6].trim());
                repo.save(user);
                insertedCount++;
            }
            return "CSV import completed. Inserted " + insertedCount +
                    " records. Skipped " + skippedCount + " duplicates: " + skippedDetails;
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV: " + e.getMessage());
        }
     }

}
