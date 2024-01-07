
package ma.fstt.repository;

import ma.fstt.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
	List<Skill> findByuserId(UUID userId);
}
