import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CbrParser {

    @Option(name = "--code")
    private String code;

    @Option(name = "--date")
    private String date;

    @Argument
    private List<String> arguments = new ArrayList<>();


    public static void main(String[] args) {
        new CbrParser().run(args);
    }


    private void run(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateValidator validator = new DateValidatorUsingTimeFormatter(dateTimeFormatter);

            parser.parseArgument(args);
            if (code == null || date == null || !arguments.get(0).equals("currency_rates")
                || arguments.size() != 1) {
                System.err.println("Incorrect input args");
                System.err.println("Example: currency_rates --code=USD --date=2022-10-08");
                return;
            } else if (!validator.isValid(date)) {
                System.err.println("Incorrect date");
                return;
            } else {
                String[] dateParts = date.split("-");
                StringBuilder sb = new StringBuilder();
                sb.append(dateParts[2]);
                sb.append("/");
                sb.append(dateParts[1]);
                sb.append("/");
                sb.append(dateParts[0]);
                date = sb.toString();



                System.out.println(code);
                System.out.println(date);
            }

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        new Cbr().getInfo(code, date);
    }



}



