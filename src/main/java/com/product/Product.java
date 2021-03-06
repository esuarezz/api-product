package com.product;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;


@Entity
@Table(name="products")
public class Product {

	@Id @GeneratedValue
	private Long id;

	@NotNull
	@Column(unique = true)
	private String name;

	@NotNull
	@Column
	private String description;

	@Column(nullable = true)
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> tags;

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
    public Product(String name, String description, Set tags, Map<String, BigDecimal> prices) {
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

	public Set<String> getTags() {
		return tags;
	}

	public Map<String, BigDecimal> getPrices() {
		return prices;
	}

	public void setPrices(Map<String, BigDecimal> prices) {
		this.prices = prices;
	}
}
