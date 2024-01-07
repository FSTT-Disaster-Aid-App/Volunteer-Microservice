
package ma.fstt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.fstt.entity.AidType;
import ma.fstt.repository.AidTypeRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/volunteer/aidtypes")
public class AidTypeController {

	@Autowired
	private AidTypeRepository aidTypeRepository;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllAidTypes() {
		try {
			List<AidType> allAidTypes = aidTypeRepository.findAll();
			return ResponseEntity.ok(Map.of("data", allAidTypes));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getAidTypeById(@PathVariable UUID id) {
		AidType aidType = aidTypeRepository.findById(id).orElse(null);
		if (aidType != null) {
			return ResponseEntity.ok(Map.of("data", aidType));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "AidType not found"));
		}
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createAidType(@RequestBody AidType aidType) {
		try {
			AidType savedAidType = aidTypeRepository.save(aidType);
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("data", savedAidType));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateAidType(@PathVariable UUID id,
			@RequestBody AidType updatedAidType) {
		AidType existingAidType = aidTypeRepository.findById(id).orElse(null);
		if (existingAidType != null) {
			updatedAidType.setId(id);
			AidType savedAidType = aidTypeRepository.save(updatedAidType);
			return ResponseEntity.ok(Map.of("data", savedAidType));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "AidType not found"));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteAidType(@PathVariable UUID id) {
		if (aidTypeRepository.existsById(id)) {
			aidTypeRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "AidType not found"));
		}
	}
}
