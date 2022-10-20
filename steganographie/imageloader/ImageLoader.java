package steganographie.imageloader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class ImageLoader{

    BufferedImage image;

    public ImageLoader (String filePath) {
        try{
            System.out.println(System.getProperty("user.dir"));
            this.image = ImageIO.read(new File(filePath));
        }
        catch(IOException fail)
        {
            System.err.println("failed loading the file");
        }
    }

    public BufferedImage getImage()
    {
        return this.image;
    }

    public byte[]getPixels()
    {
        return ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    }

    public int[][] getPixelMap()
    {
        final byte[] pixels = this.getPixels();
        final int width = image.getWidth();
        final int height = image.getHeight();

        int[][] result = new int[height][width];

        final int pixelLength = 4;
        for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength) {
            int argb = 0;
            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
            argb += ((int) pixels[pixel + 1] & 0xff); // blue
            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
            result[row][col] = argb;
            col++;
            if (col == width) {
                col = 0;
                row++;
            }
        }
        return result;
    }

}
