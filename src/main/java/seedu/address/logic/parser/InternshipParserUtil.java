package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.InternshipSortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Remark;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.Task;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class InternshipParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index has to be a positive number! (1,2,3...).";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String companyName} into a {@code CompanyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedName = companyName.trim();
        if (!CompanyName.isValidCompanyName(trimmedName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedName);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);

    }

    /**
     * Parses a {@code String contactName} into a {@code ContactName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code contactName} is invalid.
     */
    public static ContactName parseContactName(String contactName) throws ParseException {
        requireNonNull(contactName);
        String trimmedContactName = contactName.trim();
        if (!ContactName.isValidContactName(trimmedContactName)) {
            throw new ParseException(ContactName.MESSAGE_CONSTRAINTS);
        }
        return new ContactName(trimmedContactName);
    }

    /**
     * Parses a {@code String contactNumber} into a {@code ContactNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code contactNumber} is invalid.
     */
    public static ContactNumber parseContactNumber(String contactNumber) throws ParseException {
        requireNonNull(contactNumber);
        String trimmedContactNumber = contactNumber.trim();
        if (!ContactNumber.isValidContactNumber(trimmedContactNumber)) {
            throw new ParseException(ContactNumber.MESSAGE_CONSTRAINTS);
        }
        return new ContactNumber(trimmedContactNumber);
    }

    /**
     * Parses a {@code String contactEmail} into a {@code ContactEmail}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code contactEmail} is invalid.
     */
    public static ContactEmail parseContactEmail(String contactEmail) throws ParseException {
        requireNonNull(contactEmail);
        String trimmedContactEmail = contactEmail.trim();
        if (!ContactEmail.isValidContactEmail(trimmedContactEmail)) {
            throw new ParseException(ContactEmail.MESSAGE_CONSTRAINTS);
        }
        return new ContactEmail(trimmedContactEmail);
    }

    /**
     * Parses a {@code String status} into a {@code ApplicationStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static ApplicationStatus parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!ApplicationStatus.isValidApplicationStatus(trimmedStatus)) {
            throw new ParseException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        }
        return new ApplicationStatus(trimmedStatus);
    }

    /**
     * Parses a {@code String role} into a {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static Role parseOptionalRole(Optional<String> role) throws ParseException {
        if (role.isEmpty()) {
            return new Role("Unknown Role");
        } else {
            requireNonNull(role);
            String trimmedRole = role.get().trim();
            if (!Role.isValidRole(trimmedRole)) {
                throw new ParseException(Role.MESSAGE_CONSTRAINTS);
            }
            return new Role(trimmedRole);
        }
    }

    /**
     * Parses a {@code String remark} into a {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code remark} is invalid.
     */
    public static Remark parseRemark(String remark) {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String order} into a {@code InternshipSortCommandParser.OrderEnum}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code order} is invalid.
     */
    public static InternshipSortCommandParser.OrderEnum parseOrder(String order) throws ParseException {
        requireNonNull(order);
        String trimmedOrder = order.trim();
        if (!InternshipSortCommandParser.OrderEnum.isValidOrder(trimmedOrder)) {
            throw new ParseException(InternshipSortCommand.MESSAGE_INVALID_ORDER);
        }
        InternshipSortCommandParser.OrderEnum orderEnum = InternshipSortCommandParser
                .OrderEnum.getOrderEnum(trimmedOrder);
        return orderEnum;
    }

    /**
     * Parses a {@code String task} into a {@code task}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code task} is invalid.
     */
    public static Task parseTask(String task) throws ParseException {
        requireNonNull(task);
        String trimmedTask = task.trim();
        if (!Task.isValidTask(trimmedTask)) {
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }
        return new Task(trimmedTask);
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses a {@code String location} into a {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseOptionalLocation(Optional<String> location) throws ParseException {
        if (location.isEmpty()) {
            return new Location("UNKNOWN");
        } else {
            requireNonNull(location);
            String trimmedLocation = location.get().trim();
            if (!Location.isValidLocation(trimmedLocation)) {
                throw new ParseException(Location.MESSAGE_CONSTRAINTS);
            }
            return new Location(trimmedLocation);
        }
    }

    /**
     * Returns true if any of the prefixes contains {@code Optional} values, which are possibly empty, in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean anyPrefixesPresent(ArgumentMultimap argMulMap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argMulMap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if all prefixes present in the {@code ArgumentMultimap} are non-empty.
     * Vacuously true if no prefixes are present.
     */
    public static boolean prefixesPresentAreNotEmpty(ArgumentMultimap argMulMap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix ->
                argMulMap.getValue(prefix).isEmpty()
                        || (argMulMap.getValue(prefix).isPresent() && !argMulMap.getValue(prefix).get().isEmpty()));
    }

    /**
     * Returns true if all the prefixes contains {@code Optional} values, which could be empty, in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argMulMap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argMulMap.getValue(prefix).isPresent());
    }

}
