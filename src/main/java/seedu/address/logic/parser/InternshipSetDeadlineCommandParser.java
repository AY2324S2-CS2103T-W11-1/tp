package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELECT_TASK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InternshipSetDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.Deadline;

/**
 * Parses input arguments and creates a new InternshipSetDeadlineCommand object
 */
public class InternshipSetDeadlineCommandParser implements InternshipParser<InternshipSetDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InternshipSetDeadlineCommand
     * and returns an InternshipSetDeadlineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipSetDeadlineCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SELECT_TASK, PREFIX_DEADLINE);

        Index internshipIndex;
        Index taskIndex;
        Deadline deadline;

        if (argMultimap.getPreamble().isEmpty() || argMultimap.getValue(PREFIX_SELECT_TASK).isEmpty()
                || argMultimap.getValue(PREFIX_DEADLINE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipSetDeadlineCommand.MESSAGE_USAGE));
        }

        try {
            internshipIndex = InternshipParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX, pe);
        }

        try {
            taskIndex = InternshipParserUtil.parseIndex(argMultimap.getValue(PREFIX_SELECT_TASK).get());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, pe);
        }

        try {
            deadline = InternshipParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Deadline.MESSAGE_CONSTRAINTS), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SELECT_TASK, PREFIX_DEADLINE);

        return new InternshipSetDeadlineCommand(internshipIndex, taskIndex, deadline);
    }
}
