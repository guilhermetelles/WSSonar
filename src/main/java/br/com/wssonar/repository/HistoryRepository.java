package br.com.wssonar.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.wssonar.model.History;
import br.com.wssonar.model.WebService;

/**
 * History class Repository
 * 
 * All the methods all self-explanatory 
 */
public interface HistoryRepository extends JpaRepository<History, Integer> {
	
	@Query("SELECT h "
			+ "FROM History h "
			+ "WHERE "
			+ "h.htId = (SELECT max(h.htId) FROM History h WHERE h.webService = ? AND h.htBackOnline IS NULL)")
	History findLastDownHistoryByWebService(WebService webService);
	
	@Query("SELECT h "
			+ "FROM History h "
			+ "WHERE "
			+ "h.htId = (SELECT max(h.htId) FROM History h WHERE h.webService = ?)")
	History findLastHistoryByWebService(WebService webService);
	
	@Query("SELECT h "
			+ "FROM History h "
			+ "WHERE h.webService = ?1 "
			+ "ORDER BY h.htId DESC "
			+ "LIMIT '10' ")
	List<History> findByWebServiceLimit(WebService webService);
	
	@Query("SELECT h "
			+ "FROM History h "
			+ "WHERE h.webService = ?1 AND "
			+ "("
			+ "pg_catalog.date(h.htDownDate) BETWEEN pg_catalog.date(?2) AND pg_catalog.date(?3) "
			+ "OR "
			+ "pg_catalog.date(h.htBackOnline) BETWEEN pg_catalog.date(?2) AND pg_catalog.date(?3)"
			+ ")"
			+ "ORDER BY h.htId ")
	List<History> findByWebServiceAndPeriod(WebService webService, Date date1, Date date2);
	
	@Query("SELECT h "
			+ "FROM History h "
			+ "WHERE "
			+ "h.webService = ?1 AND "
			+ "("
			+ "pg_catalog.date(?2) BETWEEN pg_catalog.date(h.htDownDate) AND pg_catalog.date(h.htBackOnline) "
			+ "OR "
			+ "(h.htBackOnline IS NULL AND pg_catalog.date(?2) BETWEEN pg_catalog.date(h.htDownDate) AND pg_catalog.date(CURRENT_DATE))"
			+ ")"
			+ "ORDER BY h.htId " )
	List<History> findByWebServiceAndDate(WebService webService, Date date);
}
