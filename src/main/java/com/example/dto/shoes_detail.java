package com.example.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class shoes_detail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int sdSeq;
	
	@ManyToOne
	@JoinColumn(name = "sSeq", nullable = false)
	private shoes shoes;
	
	public int size;
	public String color;
	public int stock;
}
