import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {

    public static void processImagesInFolder(String folderPath, int threshold) {
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            System.out.println("Error: The provided path is not a directory.");
            return;
        }

        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No images found in the folder.");
            return;
        }

        for (File file : files) {
            if (file.isFile() && (file.getName().toLowerCase().endsWith(".png"))) {
                processImage(file.getAbsolutePath(), file.getAbsolutePath(), threshold);
            }
        }
    }

    public static void processImage(String inputPath, String outputPath, int threshold) {
        try {
            // Read the input image
            File inputFile = new File(inputPath);
            BufferedImage image = ImageIO.read(inputFile);

            // Process the image to keep only the parts you want
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int pixel = image.getRGB(x, y);
                    int grayValue = (pixel >> 16) & 0xff; // Extract the red component as the grayscale value
                    int newPixel = (grayValue > threshold) ? pixel : 0; // Set black for unwanted parts
                    image.setRGB(x, y, newPixel);
                }
            }

            // Save the processed image as PNG (replacing the original image)
            File outputFile = new File(outputPath);
            ImageIO.write(image, "png", outputFile);
            System.out.println("Processed image saved to " + outputPath);
        } catch (IOException e) {
            System.out.println("An error occurred while processing the image: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String folderPath = "weapon/1080";//replace with the path to your folder containing images
        int thresholdValue = 200; // You can adjust this value to control which parts to keep

        processImagesInFolder(folderPath, thresholdValue);
    }
}
