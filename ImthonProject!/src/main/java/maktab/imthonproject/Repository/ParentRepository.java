package maktab.imthonproject.Repository;

import maktab.imthonproject.DAO.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent,Integer> {

}
