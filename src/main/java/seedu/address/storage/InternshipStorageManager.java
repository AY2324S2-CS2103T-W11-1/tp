package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipData;
import seedu.address.model.ReadOnlyInternshipUserPrefs;

/**
 * Manages storage of InternshipData in local storage.
 */
public class InternshipStorageManager implements InternshipStorage {

    private static final Logger logger = LogsCenter.getLogger(InternshipStorageManager.class);
    private InternshipDataStorage internshipDataStorage;
    private InternshipUserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code InternshipStorageManager} with the given
     * {@code InternshipDataStorage} and {@code UserPrefStorage}.
     */
    public InternshipStorageManager(InternshipDataStorage internshipDataStorage,
                                    InternshipUserPrefsStorage userPrefsStorage) {
        this.internshipDataStorage = internshipDataStorage;
        this.userPrefsStorage = userPrefsStorage;
    }
    /**
     * Returns the Path of the UserPrefs file.
     */
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }
    /**
     * Returns InternshipUserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if the loading of data from preference file failed.
     */
    @Override
    public Optional<InternshipUserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }
    /**
     * Saves the given {@link ReadOnlyInternshipUserPrefs} to the storage.
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }
    /**
     * Returns the file path of the data file.
     */
    @Override
    public Path getInternshipDataFilePath() {
        return internshipDataStorage.getInternshipDataFilePath();
    }
    /**
     * Returns InternshipData data as a {@link ReadOnlyInternshipData}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    @Override
    public Optional<ReadOnlyInternshipData> readInternshipData() throws DataLoadingException {
        return readInternshipData(internshipDataStorage.getInternshipDataFilePath());
    }
    /**
     * Attempts to read internship data from the file at {@code filePath}.
     */
    @Override
    public Optional<ReadOnlyInternshipData> readInternshipData(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internshipDataStorage.readInternshipData(filePath);
    }
    /**
     * Saves the given {@link ReadOnlyInternshipData} to the storage.
     * @param internshipData cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveInternshipData(ReadOnlyInternshipData internshipData) throws IOException {
        saveInternshipData(internshipData, internshipDataStorage.getInternshipDataFilePath());
    }
    /**
     * Attempts to save internship data to the file at {@code filePath}.
     */
    @Override
    public void saveInternshipData(ReadOnlyInternshipData internshipData, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internshipDataStorage.saveInternshipData(internshipData, filePath);
    }

}
