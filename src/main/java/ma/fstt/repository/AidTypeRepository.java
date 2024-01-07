package ma.fstt.repository;

import ma.fstt.entity.AidType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AidTypeRepository extends JpaRepository<AidType, UUID> {
}
