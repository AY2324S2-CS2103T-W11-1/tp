package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.internship.Internship;

/**
 * Represents the in-memory model of the Internship data.
 */
public class InternshipModelManager implements InternshipModel {
    private static final Logger logger = LogsCenter.getLogger(InternshipModelManager.class);

    private final InternshipData internshipData;
    private final InternshipUserPrefs userPrefs;
    private FilteredList<Internship> filteredInternships;
    private SortedList<Internship> sortedInternships;

    // The internship that is currently selected. Even though it is a list, it should only ever show one internship.
    // It is set as a FilteredList, so it can work with the existing UI components.
    private FilteredList<Internship> selectedInternship;

    /**
     * Initializes a InternshipModelManager with the given internshipData and userPrefs.
     */
    public InternshipModelManager(ReadOnlyInternshipData internshipData, ReadOnlyInternshipUserPrefs userPrefs) {
        requireAllNonNull(internshipData, userPrefs);

        logger.fine("Initializing with internship data: " + internshipData + " and user prefs " + userPrefs);

        this.internshipData = new InternshipData(internshipData);
        this.userPrefs = new InternshipUserPrefs(userPrefs);
        filteredInternships = new FilteredList<>(this.internshipData.getInternshipList());
        sortedInternships = new SortedList<>(filteredInternships);
        selectedInternship = new FilteredList<>(filteredInternships);
    }

    public InternshipModelManager() {
        this(new InternshipData(), new InternshipUserPrefs());
    }

    @Override
    public void setUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyInternshipUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getInternshipDataFilePath() {
        return userPrefs.getInternshipDataFilePath();
    }

    @Override
    public void setInternshipDataFilePath(Path internshipDataFilePath) {
        requireNonNull(internshipDataFilePath);
        userPrefs.setInternshipDataFilePath(internshipDataFilePath);
    }

    @Override
    public void setInternshipData(ReadOnlyInternshipData internshipData) {
        this.internshipData.resetData(internshipData);
    }

    @Override
    public ReadOnlyInternshipData getInternshipData() {
        return internshipData;
    }

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internshipData.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        internshipData.removeInternship(target);
    }

    @Override
    public void addInternship(Internship internship) {
        internshipData.addInternship(internship);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);
        internshipData.setInternship(target, editedInternship);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Internship} backed by the internal list of
     * {@code versionedInternshipData}
     */
    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return sortedInternships;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Internship} backed by the internal list of
     * {@code InternshipData}
     */
    public void sortFilteredInternshipList(Comparator<Internship> comparator) {
        requireNonNull(comparator);
        sortedInternships.setComparator(comparator);
    }
    @Override
    public void setSelectedInternship(Internship internship) {
        selectedInternship.setPredicate(internship::equals);
    }

    @Override
    public ObservableList<Internship> getSelectedInternship() {
        return selectedInternship;
    }

    @Override
    public void updateFilteredInternshipList(Predicate<Internship> predicate) {
        requireNonNull(predicate);
        filteredInternships.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipModelManager)) {
            return false;
        }

        InternshipModelManager otherModelManager = (InternshipModelManager) other;
        return internshipData.equals(otherModelManager.internshipData)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredInternships.equals(otherModelManager.filteredInternships);
    }

}
