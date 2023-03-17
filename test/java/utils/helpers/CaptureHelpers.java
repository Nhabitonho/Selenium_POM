package utils.helpers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import utils.PropertiesReader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static pages.BaseSetup.driver;

public class CaptureHelpers {

    //Lấy đường dẫn đến project hiện tại
    static String projectPath = System.getProperty("user.dir") + "/";
    //Tạo format ngày giờ để xíu gắn dô cái name của screenshot hoặc record video
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    public static void captureScreenshot(String screenName) throws IOException {
        PropertiesReader envProFile = new PropertiesReader("dev");
            Reporter.log("Driver for Screenshot: " + driver);
            // Tạo tham chiếu đối tượng của TakesScreenshot với dirver hiện tại
            TakesScreenshot ts = (TakesScreenshot) driver;
            // Gọi hàm getScreenshotAs để chuyển hóa hình ảnh về dạng FILE
            File source = ts.getScreenshotAs(OutputType.FILE);
            //Kiểm tra folder nếu không tồn tại thì tạo folder
            File theDir = new File(projectPath + PropertiesReader.getPropValue("exportCapturePath"));
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            // Chổ này đặt tên thì truyền biến "screenName" gán cho tên File chụp màn hình
            FileHandler.copy(source, new File(projectPath + PropertiesReader.getPropValue("exportCapturePath") + "/" + screenName + "_" + dateFormat.format(new Date()) + ".png"));
            System.out.println("Screenshot taken: " + screenName);
            Reporter.log("Screenshot taken current URL: " + driver.getCurrentUrl(), true);
        }
    }

    // ------Record with Monte Media library---------
//    public static ScreenRecorder screenRecorder;
//    public String name;
//
//    //Hàm xây dựng
//    public CaptureHelpers(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
//        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
//        this.name = name;
//
//    }
//
//    //Hàm này bắt buộc để ghi đè custom lại hàm trong thư viên viết sẵn
//    @Override
//    protected File createMovieFile(Format fileFormat) throws IOException {
//
//        if (!movieFolder.exists()) {
//            movieFolder.mkdirs();
//        } else if (!movieFolder.isDirectory()) {
//            throw new IOException("\"" + movieFolder + "\" is not a directory.");
//        }
//
//        return new File(movieFolder, name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
//    }
//
//    // Hàm Start record video
//    public static void startRecord(String methodName) {
//        PropertiesFile.setPropertiesFile();
//        //Tạo thư mục để lưu file video vào
//        File file = new File(projectPath + PropertiesFile.getPropValue("exportVideoPath") + "/" + methodName + "/");
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int width = screenSize.width;
//        int height = screenSize.height;
//
//        Rectangle captureSize = new Rectangle(0, 0, width, height);
//
//        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
//        try {
//            screenRecorder = new CaptureHelpers(gc, captureSize, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null, file, methodName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (AWTException e) {
//            e.printStackTrace();
//        }
//        try {
//            screenRecorder.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Stop record video
//    public static void stopRecord() {
//        try {
//            screenRecorder.stop();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


