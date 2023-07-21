import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JPGtoPNGConverter {

    public static void convertJPGtoPNGInFolder(String folderPath) {
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
            if (file.isFile() && (file.getName().toLowerCase().endsWith(".jpg") || file.getName().toLowerCase().endsWith(".jpeg"))) {
                String inputPath = file.getAbsolutePath();
                String outputPath = inputPath.substring(0, inputPath.lastIndexOf('.')) + ".png";
                convertJPGtoPNG(inputPath, outputPath);

                // Remove the original JPG file
                if (file.delete()) {
                    System.out.println("Original JPG file deleted: " + file.getName());
                } else {
                    System.out.println("Could not delete the original JPG file: " + file.getName());
                }
            }
        }
    }

    public static void convertJPGtoPNG(String inputPath, String outputPath) {
        try {
            // Read the input image (JPG)
            File inputFile = new File(inputPath);
            BufferedImage image = ImageIO.read(inputFile);

            // Save the image as PNG
            File outputFile = new File(outputPath);
            ImageIO.write(image, "png", outputFile);
            System.out.println("Converted image saved to " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred while converting the image: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String folderPath = "weapon/1600"; // Replace with the path to your folder containing JPG images

        convertJPGtoPNGInFolder(folderPath);
    }
}
