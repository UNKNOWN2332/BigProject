package maktab.imthonproject.Repository;

import maktab.imthonproject.DAO.DayWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayWeekRepository extends JpaRepository<DayWeek,Integer>
{
}
