package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.msg.learning.shop.domain.BaseEntity;

import javax.transaction.Transactional;

@NoRepositoryBean
@Transactional
public interface Repository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {
}
