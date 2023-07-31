import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidatorUsingTimeFormatter implements DateValidator {

    private DateTimeFormatter dateTimeFormatter;

    public DateValidatorUsingTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public boolean isValid(String str) {
        try {
            this.dateTimeFormatter.parse(str);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
