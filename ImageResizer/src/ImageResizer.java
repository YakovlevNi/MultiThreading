import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable {


    private File[] files;
    private int newWidth;
    private String dstFolder;
    private long start;

    public ImageResizer(File[] files, int newWidth, String dstFolder, long start) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }
    @Override
    public void run() {
        try {
            for (File file : files) {
                System.out.println(files.length);
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                BufferedImage resizedImage = Scalr.resize(image,newWidth);
                File resizedFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(resizedImage, "jpg", resizedFile);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Program finished in : " + (System.currentTimeMillis() - start) + " ms");
    }
}
