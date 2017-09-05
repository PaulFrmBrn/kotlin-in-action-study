import java.io.File;
import java.util.List;

/**
 * @author PaulFrmBrn
 */
interface FileContentProcessor {
    void processContents(File path, byte[] binaryContents, List<String> textContents);
}
