package com.kranthi.AccountManagement.Model;



import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Audit {
	
	@Column(name = "account_status", nullable = false)
    private String accountStatus = "ACTIVE"; 
	
	@CreatedDate
	@Column(name = "account_created_date", updatable = false)
	private  LocalDateTime accountCreatedDate;
	
	@LastModifiedDate      
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
	
	

}
