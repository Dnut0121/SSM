package com.example.shoppingmall.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "shoes")
public class shoes {
	@Id
	public String sSeq;
	
	public String name;
	public int price;
	public String brand;
	public String category;
	public String releaseDate;
	
	@OneToMany(mappedBy = "shoes", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<shoes_detail> details;
}
