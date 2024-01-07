package ma.fstt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.fstt.entity.Donation;
import ma.fstt.repository.DonationRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/volunteer/donations")
public class DonationController {

	@Autowired
	private DonationRepository donationRepository;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllDonations() {
		try {
			List<Donation> allDonations = donationRepository.findAll();
			return ResponseEntity.ok(Map.of("data", allDonations));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getDonationById(@PathVariable UUID id) {
		Donation donation = donationRepository.findById(id).orElse(null);
		if (donation != null) {
			return ResponseEntity.ok(Map.of("data", donation));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Donation not found"));
		}
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createDonation(@RequestBody Donation donation) {
		try {
			Donation savedDonation = donationRepository.save(donation);
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("data", savedDonation));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateDonation(@PathVariable UUID id,
			@RequestBody Donation updatedDonation) {
		Donation existingDonation = donationRepository.findById(id).orElse(null);
		if (existingDonation != null) {
			updatedDonation.setId(id);
			Donation savedDonation = donationRepository.save(updatedDonation);
			return ResponseEntity.ok(Map.of("data", savedDonation));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Donation not found"));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteDonation(@PathVariable UUID id) {
		if (donationRepository.existsById(id)) {
			donationRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Donation not found"));
		}
	}

}
