package jp.gtf.taotao001.repository;



import jp.gtf.taotao001.entity.UserEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository <UserEntity,String>{

	@Query(value = "SELECT NEXTVAL('hr_id_seq');", nativeQuery = true)
    Long getNextSeriesId();

	UserEntity findByEmail(Object email);


}
