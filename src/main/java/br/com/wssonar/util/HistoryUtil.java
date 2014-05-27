package br.com.wssonar.util;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;

import br.com.wssonar.model.History;

public class HistoryUtil {

	/**
	 * Gets the web service downtime based on it's history
	 * 
	 * @param histories
	 * @return
	 */
	public static String defineDowntime(List<History> histories) {

		long millis = 0;

		for (History history : histories) {

			String offlineTotalTime = "";
			Long firstDate = history.getHtDownDate().getTime();
			Long secondDate = 0L; 

			if(history.getHtBackOnline() != null){
				secondDate = history.getHtBackOnline().getTime(); 
			} else {
				secondDate = new Date().getTime();
			}

			DateTime date1 = new DateTime(firstDate);
			DateTime date2 = new DateTime(secondDate);

			Period period = new Period(date1, date2);
			DateTime zero = new DateTime(0); 
			millis = millis + period.toDurationFrom(zero).getMillis();

			offlineTotalTime = DurationFormatUtils.formatPeriod(firstDate, secondDate, "HH 'h' mm 'm' ss 's'");

			history.setHtOfflineTotalTime(offlineTotalTime);
		}

		String duracao = DurationFormatUtils.formatDuration(millis, "HH 'h' mm 'm' ss 's'");

		return duracao;
	}
}
