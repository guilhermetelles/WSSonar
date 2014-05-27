package br.com.wssonar.business;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.wssonar.model.History;
import br.com.wssonar.model.WebService;
import br.com.wssonar.service.HistoryService;
import br.com.wssonar.service.WebServiceService;

@Component
public class ChartGenerator {

	@Autowired
	private HistoryService historyService;
	@Autowired
	private WebServiceService webServiceService;
	
	/**
	 *  Generates the chart information for the last 10 days
	 *  
	 * @param webService
	 * @return map with chart information
	 */
	public Map<String, String> generateChartInfo(WebService webService) {
	
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_MONTH, -10);
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		
		Map<String, String> chartInfo = new TreeMap<String, String>();
		
		for (int i = 0; i <= 9; i++) {
			
			if(i != 0) {
				date.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			Date day = date.getTime();
			
			generateChartInfo(webService, chartInfo, day);
		} 
		
		return chartInfo;
	}
	
	/**
	 *  Generates the chart information by a specified period
	 *  
	 * @param webService
	 * @return map with chart information
	 */
	public Map<String, String> generateChartInfoByPeriod(WebService webService, Date date1, Date date2) {
		
		DateTime startDate = new DateTime(date1.getTime());
		DateTime endDate = new DateTime(date2.getTime());
		
		Days days = Days.daysBetween(startDate, endDate);
		int daysOfDifference = days.getDays();
		
		Calendar date = Calendar.getInstance();
		date.setTime(date1);
		
		Map<String, String> chartInfo = new TreeMap<String, String>();
		
		for (int i = 0; i <= daysOfDifference; i++) {
			
			if(i != 0) {
				date.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			Date day = date.getTime();
			
			if(!DateUtils.isSameDay(new Date(), day)) {
				generateChartInfo(webService, chartInfo, day);
			}
		}
		
		return chartInfo;
	}

	/**
	 * Process downtime history and generates the chart information.
	 * 
	 * @param webService
	 * @param chartInfo
	 * @param day
	 */
	private void generateChartInfo(WebService webService, Map<String, String> chartInfo, Date day) {
		Long timeOnline = 86400000L;
		
		List<History> histories = historyService.findByWebServiceAndDate(webService, day);
		
		if(histories != null) {

			for(History history : histories) {
				
				Date downDate = history.getHtDownDate();
				Date backOnline = history.getHtBackOnline();
				
				if(backOnline != null && DateUtils.isSameDay(day, downDate) && DateUtils.isSameDay(day, backOnline)) {
					
					DateTime date1 = new DateTime(downDate.getTime());
					DateTime date2 = new DateTime(backOnline.getTime());
					
					Period period = new Period(date1, date2);
					DateTime zero = new DateTime(0); 
					long millis = period.toDurationFrom(zero).getMillis();

					timeOnline = timeOnline - millis;
					
					continue;
				}	
				
				if(DateUtils.isSameDay(day, downDate)) {
					
					DateTime date1 = new DateTime(downDate.getTime());
					DateTime date2 = new DateTime(day.getTime());
					
					Period period = new Period(date2, date1);
					DateTime zero = new DateTime(0); 
					long millis = period.toDurationFrom(zero).getMillis();
					
					timeOnline = timeOnline - (86400000L - millis);
					
					continue;
				}
				
				if(backOnline != null && DateUtils.isSameDay(day, backOnline)) {
					
					DateTime date1 = new DateTime(backOnline.getTime());
					DateTime date2 = new DateTime(day.getTime());
					
					Period period = new Period(date2, date1);
					DateTime zero = new DateTime(0); 
					long millis = period.toDurationFrom(zero).getMillis();
					
					timeOnline = timeOnline - millis;
					
					continue;
				}
				
				timeOnline = 0L;
				
			} // for
			
		} // if
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dayFormatted =  format.format(day);
		
		chartInfo.put(dayFormatted, formatTime(timeOnline));
	}
	
	/**
	 * Format milliseconds to a human readable string 
	 * @param millis
	 * @return
	 */
	private String formatTime(long millis) {
		return String.format("%d:%d", 
				TimeUnit.MILLISECONDS.toHours(millis),
			    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))
			);
	}
	
}
