package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.logic.commands.InternshipAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Remark;
import seedu.address.model.internship.Role;

/**
 * Parses input arguments and creates a new InternshipAddCommand object
 */
public class InternshipAddCommandParser implements InternshipParser<InternshipAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InternshipAddCommand
     * and returns an InternshipAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public InternshipAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY, PREFIX_DESCRIPTION, PREFIX_STATUS, PREFIX_CONTACT_NAME,
                        PREFIX_CONTACT_EMAIL, PREFIX_CONTACT_NUMBER, PREFIX_LOCATION, PREFIX_ROLE);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY, PREFIX_DESCRIPTION, PREFIX_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipAddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY, PREFIX_DESCRIPTION, PREFIX_STATUS, PREFIX_CONTACT_NAME,
                PREFIX_CONTACT_EMAIL, PREFIX_CONTACT_NUMBER, PREFIX_LOCATION, PREFIX_ROLE);

        // Mandatory Fields
        CompanyName com = InternshipParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_COMPANY).get());
        ApplicationStatus status = InternshipParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        Description desc = InternshipParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        // Optional Fields
        ContactName poc = InternshipParserUtil.parseOptionalContactName(argMultimap.getValue(PREFIX_CONTACT_NAME));
        ContactEmail email = InternshipParserUtil.parseOptionalContactEmail(argMultimap.getValue(PREFIX_CONTACT_EMAIL));
        ContactNumber phon = InternshipParserUtil.parseOptionalContactNumber(argMultimap.getValue(PREFIX_CONTACT_NUMBER));
        Location loc = InternshipParserUtil.parseOptionalLocation(argMultimap.getValue(PREFIX_LOCATION));
        Role role = InternshipParserUtil.parseOptionalRole(argMultimap.getValue(PREFIX_ROLE));
        Remark remark = new Remark(""); // Add Command does not allow adding remarks immediately

        Internship internship = new Internship(com, poc, email, phon, loc, status, desc, role, remark);

        return new InternshipAddCommand(internship);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
