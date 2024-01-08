package ma.fstt.entity;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class AidType {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private String name;

	@JsonBackReference
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "assistance_offer_aid_type", joinColumns = @JoinColumn(name = "aid_type_id"), inverseJoinColumns = @JoinColumn(name = "assistance_offer_id"))
	private Set<AssistanceOffer> assistanceOffers;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AssistanceOffer> getAssistanceOffers() {
		return assistanceOffers;
	}

	public void setAssistanceOffers(Set<AssistanceOffer> assistanceOffers) {
		this.assistanceOffers = assistanceOffers;
	}

}
