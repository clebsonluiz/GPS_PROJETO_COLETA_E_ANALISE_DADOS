/**
 * 
 */
package util;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;

/**
 * @author ayrtons
 *
 */
public class ConvertLocalDate implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate arg0) {
		// TODO Auto-generated method stub
		return Date.valueOf(arg0);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date arg0) {
		// TODO Auto-generated method stub
		return arg0.toLocalDate();
	}

}
