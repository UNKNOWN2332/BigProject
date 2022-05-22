package maktab.imthonproject.Repository;

import maktab.imthonproject.DAO.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ClassRepository extends JpaRepository<Class,Integer> {


}
