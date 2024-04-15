package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_COMPANY_NAME_BOB;
import static seedu.address.model.util.InternshipSampleDataUtil.EMPTY_REMARK;
import static seedu.address.model.util.InternshipSampleDataUtil.EMPTY_TASKLIST;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ALICE_MICROSOFT;
import static seedu.address.testutil.TypicalInternships.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class InternshipTest {
    @Test
    public void constructor_allNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Internship(null, null, null,
                null, null, null, null, null, null, null));
    }

    @Test
    public void constructor_nullCompanyName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Internship(null, new ContactName("John Doe"),
                new ContactEmail("johnDoe@gmail.com"), new ContactNumber("91234567"), new Location("local"),
                new ApplicationStatus("to_apply"), new Description("Software Engineer Intern"),
                new Role("Software Engineer"), EMPTY_REMARK, EMPTY_TASKLIST));
    }

    @Test
    public void isSameInternship() {
        // same object -> returns true
        assertTrue(ALICE_MICROSOFT.isSameInternship(ALICE_MICROSOFT));

        // null -> returns false
        assertFalse(ALICE_MICROSOFT.isSameInternship(null));

        // same mandatory fields but all other attributes different -> returns true
        Internship editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withLocation("local")
                .withRole("Backend Engineer").build();
        assertTrue(ALICE_MICROSOFT.isSameInternship(editedAlice));

        // different company name, all other attributes same -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withCompanyName(VALID_COMPANY_NAME_BOB).build();
        assertFalse(ALICE_MICROSOFT.isSameInternship(editedAlice));

        // different contact name, all other attributes same -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withContactName("Alicia Paulina").build();
        assertFalse(ALICE_MICROSOFT.isSameInternship(editedAlice));

        // different contact number, all other attributes same -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withContactNumber("85462364").build();
        assertFalse(ALICE_MICROSOFT.isSameInternship(editedAlice));

        // different contact email, all other attributes same -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withContactEmail("alicer@exampler.com").build();
        assertFalse(ALICE_MICROSOFT.isSameInternship(editedAlice));

        // different application status, all other attributes same -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withApplicationStatus("rejected").build();
        assertFalse(ALICE_MICROSOFT.isSameInternship(editedAlice));

        // different description, all other attributes same -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withDescription("Data Analyst Intern").build();
        assertFalse(ALICE_MICROSOFT.isSameInternship(editedAlice));

        // company name differs in case, all other attributes same -> returns false
        Internship editedBob = new InternshipBuilder(BOB).withCompanyName(VALID_COMPANY_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameInternship(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Internship aliceCopy = new InternshipBuilder(ALICE_MICROSOFT).build();
        assertTrue(ALICE_MICROSOFT.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE_MICROSOFT.equals(ALICE_MICROSOFT));

        // null -> returns false
        assertFalse(ALICE_MICROSOFT.equals(null));

        // different type -> returns false
        assertFalse(ALICE_MICROSOFT.equals(5));

        // different internship -> returns false
        assertFalse(ALICE_MICROSOFT.equals(BOB));

        // different company name -> returns false
        Internship editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withCompanyName(VALID_COMPANY_NAME_BOB).build();
        assertFalse(ALICE_MICROSOFT.equals(editedAlice));

        // different contact name -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withContactName("John Doe").build();
        assertFalse(ALICE_MICROSOFT.equals(editedAlice));

        // different contact number -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withContactNumber("85462364").build();
        assertFalse(ALICE_MICROSOFT.isSameInternship(editedAlice));

        // different contact email -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withContactEmail("johnDoe@gmail.com").build();
        assertFalse(ALICE_MICROSOFT.equals(editedAlice));

        // different contact number -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withContactNumber("91234567").build();
        assertFalse(ALICE_MICROSOFT.equals(editedAlice));

        // different location -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withLocation("local").build();
        assertFalse(ALICE_MICROSOFT.equals(editedAlice));

        // different application status -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withApplicationStatus("to_apply").build();
        assertFalse(ALICE_MICROSOFT.equals(editedAlice));

        // different description -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withDescription("Software Engineer Intern").build();
        assertFalse(ALICE_MICROSOFT.equals(editedAlice));

        // different role -> returns false
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withRole("Software Engineer").build();
        assertFalse(ALICE_MICROSOFT.equals(editedAlice));

        // different remark -> returns true
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withRemark("Good company").build();
        assertTrue(ALICE_MICROSOFT.equals(editedAlice));

        // different task list -> returns true
        editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withTaskList("Task 1;Task 2").build();
        assertTrue(ALICE_MICROSOFT.equals(editedAlice));
    }
}
