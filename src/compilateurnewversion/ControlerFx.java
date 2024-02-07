package compilateurnewversion;

import java.io.File;			
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.OutputStream;
import java.io.PrintStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class ControlerFx {
	private Stage stage;
	public static File file;
    @FXML
    private TextArea codeTxt;

    @FXML
    private Button importBtn;

    @FXML
    private Button runBtn;
    @FXML
    private TextArea output;
    @FXML
    void importAction(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a File");
        file = fileChooser.showOpenDialog(this.stage);
        if (file != null) {
        	String content = null;
			try {
				content = new String(Files.readAllBytes(Paths.get(file.toURI())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            codeTxt.setText(content);
        }
    };
    @FXML
    void compileBtn(ActionEvent event) {
    	PrintStream printStream = new PrintStream(new OutputStream() {
    	    @Override
    	    public void write(int b) throws IOException {
    	        output.appendText(String.valueOf((char) b));
    	    }
    	});
        System.setOut(printStream);
    	Main.main(null);
    }
    public void setStage(Stage stage) {
		this.stage = stage;
		
	}
	}

