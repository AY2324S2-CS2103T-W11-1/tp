package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipUserPrefs;

/**
 * A class to access UserPrefs stored in the hard disk as a json file
 */
public class JsonInternshipUserPrefsStorage implements InternshipUserPrefsStorage {
    private Path filePath;
    public JsonInternshipUserPrefsStorage(Path filePath) {
        this.filePath = filePath;
    }
    /**
     * Returns the file path of the UserPrefs data file.
     */
    @Override
    public Path getUserPrefsFilePath() {
        return filePath;
    }
    /**
     * Returns UserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if the loading of data from preference file failed.
     */
    @Override
    public Optional<InternshipUserPrefs> readUserPrefs() throws DataLoadingException {
        return readUserPrefs(filePath);
    }
    /**
     * Similar to {@link #readUserPrefs()}
     * @param prefsFilePath location of the data. Cannot be null.
     * @throws DataLoadingException if the file format is not as expected.
     */
    public Optional<InternshipUserPrefs> readUserPrefs(Path prefsFilePath) throws DataLoadingException {
        return JsonUtil.readJsonFile(prefsFilePath, InternshipUserPrefs.class);
    }
    /**
     * Saves the given {@link ReadOnlyInternshipUserPrefs} to the storage.
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) throws IOException {
        JsonUtil.saveJsonFile(userPrefs, filePath);
    }

}
