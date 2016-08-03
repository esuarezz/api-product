package com.product;

import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.core.Relation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Entity
@Table(name="products")
public class Product {

	@Id @GeneratedValue
	private Long id;

	@Column @NotNull
	private String name;

	@NotNull
	@Column
	private String description;

	@Column(nullable = true)
	@ElementCollection(fetch = FetchType.EAGER)

	private List<String> tags;

	@NotNull
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "prices")
	@MapKeyColumn(name="currency")
	private Map<String,BigDecimal> prices;

	private Product() {}


    /**
	 * *prices will be a map because we could have different currencies and we can access directly when we want to update
	 * improving the perfomance with direct access, and keeping the "only one price per currency)
	 * @param name
	 * @param description
	 * @param tags
	 * @param prices
     */
    public Product(String name, String description, List tags, Map<String, BigDecimal> prices) {
		this.name = name;
		this.description = description;
		this.tags = tags;
		this.prices = prices;
	}


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getTags() {
		return tags;
	}

	public Map<String, BigDecimal> getPrices() {
		return prices;
	}
}
