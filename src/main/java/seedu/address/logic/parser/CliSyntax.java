package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_COMPANY_NAME = new Prefix("c/");
    public static final Prefix PREFIX_CONTACT_NAME = new Prefix("cn/");
    public static final Prefix PREFIX_CONTACT_NUMBER = new Prefix("cp/");
    public static final Prefix PREFIX_CONTACT_EMAIL = new Prefix("ce/");
    public static final Prefix PREFIX_APPLICATION_STATUS = new Prefix("as/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_ROLE = new Prefix("r/");

}
