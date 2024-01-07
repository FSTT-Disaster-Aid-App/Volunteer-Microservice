package ma.fstt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.fstt.entity.AssistanceOffer;
import ma.fstt.repository.AssistanceOfferRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/volunteer/assistanceoffers")
public class AssistanceOfferController {

	@Autowired
	private AssistanceOfferRepository assistanceOfferRepository;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllAssistanceOffers() {
		try {
			List<AssistanceOffer> allAssistanceOffers = assistanceOfferRepository.findAll();
			return ResponseEntity.ok(Map.of("data", allAssistanceOffers));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getAssistanceOfferById(@PathVariable UUID id) {
		AssistanceOffer assistanceOffer = assistanceOfferRepository.findById(id).orElse(null);
		if (assistanceOffer != null) {
			return ResponseEntity.ok(Map.of("data", assistanceOffer));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "AssistanceOffer not found"));
		}
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createAssistanceOffer(@RequestBody AssistanceOffer assistanceOffer) {
		try {
			AssistanceOffer savedAssistanceOffer = assistanceOfferRepository.save(assistanceOffer);
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("data", savedAssistanceOffer));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateAssistanceOffer(@PathVariable UUID id,
			@RequestBody AssistanceOffer updatedAssistanceOffer) {
		AssistanceOffer existingAssistanceOffer = assistanceOfferRepository.findById(id).orElse(null);
		if (existingAssistanceOffer != null) {
			updatedAssistanceOffer.setId(id);
			AssistanceOffer savedAssistanceOffer = assistanceOfferRepository.save(updatedAssistanceOffer);
			return ResponseEntity.ok(Map.of("data", savedAssistanceOffer));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "AssistanceOffer not found"));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteAssistanceOffer(@PathVariable UUID id) {
		if (assistanceOfferRepository.existsById(id)) {
			assistanceOfferRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "AssistanceOffer not found"));
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Map<String, Object>> getAssistanceOffersByUserId(@PathVariable UUID userId) {
		try {
			List<AssistanceOffer> userAssistanceOffers = assistanceOfferRepository.findByuserId(userId);
			return ResponseEntity.ok(Map.of("data", userAssistanceOffers));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}

	@GetMapping("/request/{assistanceRequestId}")
	public ResponseEntity<Map<String, Object>> getAssistanceOffersByRequest(@PathVariable UUID assistanceRequestId) {
		try {
			List<AssistanceOffer> assistanceOffersByRequest = assistanceOfferRepository
					.findByassistanceRequestId(assistanceRequestId);
			return ResponseEntity.ok(Map.of("data", assistanceOffersByRequest));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}
}
