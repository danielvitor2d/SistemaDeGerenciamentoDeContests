package gemp.github.util;

public class ContestManagerUtil {
    /**
     * Field to represent API version on the requests/responses header
     */
    public static final String HEADER_CONTESTMANAGER_API_VERSION = "1.0";

    /**
     * Field to represent API key on the requests/responses header
     */
    public static final String HEADER_API_KEY = "123123";

    /**
     * Field to represent API Rate Limit Remaining on the requests/responses header
     */
    public static final String HEADER_LIMIT_REMAINING = "X-Rate-Limit-Remaining";

    /**
     * Field to represent API Rate Limit Retry After Seconds on the requests/responses header
     */
    public static final String HEADER_RETRY_AFTER = "X-Rate-Limit-Retry-After-Seconds";

    private ContestManagerUtil() {}
}
