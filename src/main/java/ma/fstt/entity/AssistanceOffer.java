package ma.fstt.entity;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

enum Status {
	PENDING,
	APPROVED,
	REJECTED
}

@Entity
public class AssistanceOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private UUID assistanceRequestId;

	@Column(nullable = false)
	private UUID userId;

	@Column(nullable = false)
	private Status status = Status.PENDING;

	@Column(nullable = false)
	private String dateOfferMade;

	private String dateOfferAcceptedOrRejected;

	@ManyToMany(mappedBy = "assistanceOffers", cascade = CascadeType.ALL)
	private Set<AidType> aidTypes;

	@OneToMany(mappedBy = "assistanceOffer", cascade = CascadeType.ALL)
	private Set<Donation> donations;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Set<AidType> getAidTypes() {
		return aidTypes;
	}

	public void setAidTypes(Set<AidType> aidTypes) {
		this.aidTypes = aidTypes;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDateOfferMade() {
		return dateOfferMade;
	}

	public void setDateOfferMade(String dateOfferMade) {
		this.dateOfferMade = dateOfferMade;
	}

	public String getDateOfferAcceptedOrRejected() {
		return dateOfferAcceptedOrRejected;
	}

	public void setDateOfferAcceptedOrRejected(String dateOfferAcceptedOrRejected) {
		this.dateOfferAcceptedOrRejected = dateOfferAcceptedOrRejected;
	}

	public Set<Donation> getDonations() {
		return donations;
	}

	public void setDonations(Set<Donation> donations) {
		this.donations = donations;
	}

	public UUID getAssistanceRequestId() {
		return assistanceRequestId;
	}

	public void setAssistanceRequestId(UUID assistanceRequestId) {
		this.assistanceRequestId = assistanceRequestId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

}
