package maktab.imthonproject.Repository;

import maktab.imthonproject.DAO.Timetable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableRepository extends JpaRepository<Timetable,Integer>
{




}
