package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipData;
import seedu.address.model.ReadOnlyInternshipUserPrefs;

/**
 * API of the InternshipStorage component
 */
public interface InternshipStorage extends InternshipUserPrefsStorage, InternshipDataStorage {
    /**
     * Attempts to read the user prefs from InternshipUserPrefsStorage.
     */
    @Override
    Optional<InternshipUserPrefs> readUserPrefs() throws DataLoadingException;
    /**
     * Attempts to save the user prefs to InternshipUserPrefsStorage.
     */
    @Override
    void saveUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) throws IOException;
    /**
     * Returns a Path object of the internship data file.
     */
    @Override
    Path getInternshipDataFilePath();
    /**
     * Attempts to read the internship data from the InternshipDataStorage.
     */
    @Override
    Optional<ReadOnlyInternshipData> readInternshipData() throws DataLoadingException;
    /**
     * Attempts to save the given internship data to the internship data file path.
     */
    @Override
    void saveInternshipData(ReadOnlyInternshipData internshipData) throws IOException;

}
