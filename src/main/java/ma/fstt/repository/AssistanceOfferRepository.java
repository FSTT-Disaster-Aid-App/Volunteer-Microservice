package ma.fstt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fstt.entity.AssistanceOffer;

import java.util.List;
import java.util.UUID;

public interface AssistanceOfferRepository extends JpaRepository<AssistanceOffer, UUID> {
	List<AssistanceOffer> findByuserId(UUID userId);

	List<AssistanceOffer> findByassistanceRequestId(UUID assistanceRequestId);

}
