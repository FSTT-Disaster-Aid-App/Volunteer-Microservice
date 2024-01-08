package ma.fstt.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Donation {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "assistance_offer_id")
	private AssistanceOffer assistanceOffer;

	@Column(nullable = false)
	private String donationDescription;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public AssistanceOffer getAssistanceOffer() {
		return assistanceOffer;
	}

	public void setAssistanceOffer(AssistanceOffer assistanceOffer) {
		this.assistanceOffer = assistanceOffer;
	}

	public String getDonationDescription() {
		return donationDescription;
	}

	public void setDonationDescription(String donationDescription) {
		this.donationDescription = donationDescription;
	}

}
