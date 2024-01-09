package ma.fstt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ma.fstt.entity.Skill;
import ma.fstt.repository.SkillRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/volunteer/skills")
public class SkillController {

	@Autowired
	private SkillRepository skillRepository;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllSkills() {
		try {
			List<Skill> allSkills = skillRepository.findAll();
			return ResponseEntity.ok(Map.of("data", allSkills));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getSkillById(@PathVariable int id) {
		Skill skill = skillRepository.findById(id).orElse(null);
		if (skill != null) {
			return ResponseEntity.ok(Map.of("data", skill));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Skill not found"));
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Map<String, Object>> getSkillsByUserId(@PathVariable UUID userId) {
		try {
			List<Skill> userSkills = skillRepository.findByuserId(userId);
			return ResponseEntity.ok(Map.of("data", userSkills));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error while processing the request"));
		}
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createSkill(@RequestBody Skill skill) {
		try {
			Skill savedSkill = skillRepository.save(skill);
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("data", savedSkill));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", e.getMessage()));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateSkill(@PathVariable int id, @RequestBody Skill updatedSkill) {
		Skill existingSkill = skillRepository.findById(id).orElse(null);
		if (existingSkill != null) {
			updatedSkill.setId(id);
			Skill savedSkill = skillRepository.save(updatedSkill);
			return ResponseEntity.ok(Map.of("data", savedSkill));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Skill not found"));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteSkill(@PathVariable int id) {
		if (skillRepository.existsById(id)) {
			skillRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Skill not found"));
		}
	}
}
