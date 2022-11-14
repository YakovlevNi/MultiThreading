import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main1 {
    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "C:\\Users\\Yakov\\Desktop\\Thread\\src";
        String dstFolder = "C:\\Users\\Yakov\\Desktop\\Thread\\dsc";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        int middle = files.length / 2;
        int firstCore = middle / 2;
        int secondCore = middle - firstCore;
        int thirdCore = middle / 2;
        int fourthCore = middle - thirdCore;

        File[] files1 = new File[firstCore];
        System.arraycopy(files, 0, files1, 0, firstCore);
        ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder, start);
        new Thread(resizer1).start();

        File[] files2 = new File[secondCore];
        System.arraycopy(files, 0, files2, 0, secondCore);
        ImageResizer resizer2 = new ImageResizer(files2, newWidth, dstFolder, start);
        new Thread(resizer2).start();


        File[] files3 = new File[thirdCore];
        System.arraycopy(files, 0, files3, 0, thirdCore);
        ImageResizer resizer3 = new ImageResizer(files3, newWidth, dstFolder, start);
        new Thread(resizer3).start();

        File[] files4 = new File[fourthCore];
        System.arraycopy(files, 0, files4, 0, fourthCore);
        ImageResizer resizer4 = new ImageResizer(files4, newWidth, dstFolder, start);
        new Thread(resizer4).start();

        System.out.println(firstCore);
        System.out.println(secondCore);
        System.out.println(thirdCore);
        System.out.println(fourthCore);


        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
