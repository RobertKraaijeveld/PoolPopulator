
package FileChoosing;
import GUI.FileChooserGUIframe;
import java.io.File;
import javax.swing.JFileChooser;

/*
 * @author robert
 */

public class FileUnpacker
{
    private File selectedFile;
    
    public FileUnpacker(File fileSelectedByUser)
    {
        selectedFile = fileSelectedByUser;
    }
}
