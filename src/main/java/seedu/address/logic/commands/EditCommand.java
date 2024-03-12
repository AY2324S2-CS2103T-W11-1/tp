package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Role;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditInternshipDescriptor editInternshipDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editInternshipDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditInternshipDescriptor editInternshipDescriptor) {
        requireNonNull(index);
        requireNonNull(editInternshipDescriptor);

        this.index = index;
        this.editInternshipDescriptor = new EditInternshipDescriptor(editInternshipDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Internship internshipToEdit = lastShownList.get(index.getZeroBased());
        Internship editedInternship = createEditedInternship(internshipToEdit, editInternshipDescriptor);

        if (!internshipToEdit.isSameInternship(editedInternship) && model.hasInternship(editedInternship)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setInternship(internshipToEdit, editedInternship);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedInternship)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Internship createEditedInternship(Internship internshipToEdit, EditInternshipDescriptor editPersonDescriptor) {
        assert internshipToEdit != null;

        CompanyName updatedCompanyName = editPersonDescriptor.getCompanyName().orElse(internshipToEdit.getCompanyName());
        ContactName updatedContactName = editPersonDescriptor.getContactName().orElse(internshipToEdit.getContactName());
        ContactEmail updatedContactEmail = editPersonDescriptor.getContactEmail().orElse(internshipToEdit.getContactEmail());
        ContactNumber updatedContactNumber = editPersonDescriptor.getContactNumber().orElse(internshipToEdit.getContactNumber());
        Location updatedLocation = editPersonDescriptor.getLocation().orElse(internshipToEdit.getLocation());
        ApplicationStatus updatedApplicationStatus = editPersonDescriptor.getApplicationStatus().orElse(internshipToEdit.getApplicationStatus());
        Description updatedDescription = editPersonDescriptor.getDescription().orElse(internshipToEdit.getDescription());
        Role updatedRole = editPersonDescriptor.getRole().orElse(internshipToEdit.getRole());

        return new Internship(updatedCompanyName, updatedContactName, updatedContactEmail, updatedContactNumber, updatedLocation, updatedApplicationStatus, updatedDescription, updatedRole);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editInternshipDescriptor.equals(otherEditCommand.editInternshipDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editInternshipDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditInternshipDescriptor {
        private CompanyName companyName;
        private Location location;
        private Description description;
        private Role role;

        // Data fields
        private ContactName contactName;
        private ContactEmail contactEmail;
        private ContactNumber contactNumber;
        private ApplicationStatus applicationStatus;

        public EditInternshipDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
            setCompanyName(toCopy.companyName);
            setContactName(toCopy.contactName);
            setContactEmail(toCopy.contactEmail);
            setContactNumber(toCopy.contactNumber);
            setLocation(toCopy.location);
            setApplicationStatus(toCopy.applicationStatus);
            setDescription(toCopy.description);
            setRole(toCopy.role);
        }
        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(companyName, contactName, contactEmail, contactNumber, location, applicationStatus, description, role);
        }

        public void setCompanyName(CompanyName companyName) {
            this.companyName = companyName;
        }
        public Optional<CompanyName> getCompanyName() {
            return Optional.ofNullable(companyName);
        }
        public void setContactName(ContactName contactName) {
            this.contactName = contactName;
        }
        public Optional<ContactName> getContactName() {
            return Optional.ofNullable(contactName);
        }
        public void setContactEmail(ContactEmail contactEmail) {
            this.contactEmail = contactEmail;
        }
        public Optional<ContactEmail> getContactEmail() {
            return Optional.ofNullable(contactEmail);
        }
        public void setContactNumber(ContactNumber contactNumber) {
            this.contactNumber = contactNumber;
        }
        public Optional<ContactNumber> getContactNumber() {
            return Optional.ofNullable(contactNumber);
        }
        public void setLocation(Location location) {
            this.location = location;
        }
        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }
        public void setApplicationStatus(ApplicationStatus applicationStatus) {
            this.applicationStatus = applicationStatus;
        }
        public Optional<ApplicationStatus> getApplicationStatus() {
            return Optional.ofNullable(applicationStatus);
        }
        public void setDescription(Description description) {
            this.description = description;
        }
        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }
        public void setRole(Role role) {
            this.role = role;
        }
        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }
        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditInternshipDescriptor)) {
                return false;
            }

            EditInternshipDescriptor otherEditInternshipDescriptor = (EditInternshipDescriptor) other;
            return Objects.equals(companyName, otherEditInternshipDescriptor.companyName)
                    && Objects.equals(contactName, otherEditInternshipDescriptor.contactName)
                    && Objects.equals(contactEmail, otherEditInternshipDescriptor.contactEmail)
                    && Objects.equals(contactNumber, otherEditInternshipDescriptor.contactNumber)
                    && Objects.equals(location, otherEditInternshipDescriptor.location)
                    && Objects.equals(applicationStatus, otherEditInternshipDescriptor.applicationStatus)
                    && Objects.equals(description, otherEditInternshipDescriptor.description)
                    && Objects.equals(role, otherEditInternshipDescriptor.role);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("companyName", companyName)
                    .add("contactName", contactName)
                    .add("contactEmail", contactEmail)
                    .add("contactNumber", contactNumber)
                    .add("location", location)
                    .add("applicationStatus", applicationStatus)
                    .add("description", description)
                    .add("role", role)
                    .toString();
        }
    }
}
