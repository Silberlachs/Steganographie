package steganographie.encoder;

import java.awt.*;
import java.util.ArrayList;

public class Encoder {

    private int [][] pixelMap;
    private int imageLength;
    private Point coordinate;
    private String secretText;


    public Encoder (int[][] pixelMap){
        this.pixelMap = pixelMap;
        this.imageLength = pixelMap[0].length;
        this.coordinate = new Point(0,0);
    }

    private void encodeTextToImage()
    {
        for(int strL=0; strL<secretText.length(); strL++)
        {
            char actualChar = secretText.charAt(strL);
            String binary = this.getBinaryString(this.getNextPixels());
        }
    }

    private void compareBinaryStringAndUpdatePixel(char character, String binary){
        String charToBinaryString = Integer.toBinaryString(character);

        for (int charpos=0; charpos < binary.length(); charpos++){
            if(binary.charAt(charpos) == charToBinaryString.charAt(charpos)) {
                continue;
            }

            // TODO: map difference between two strings on color model
        }
    }

    private String getBinaryString(ArrayList<Color> pixel)
    {
        StringBuffer pixelBuffer = new StringBuffer();
        pixelBuffer.append(pixel.get(0).getRed() % 2);
        pixelBuffer.append(pixel.get(0).getGreen() % 2);
        pixelBuffer.append(pixel.get(0).getBlue() % 2);
        pixelBuffer.append(pixel.get(0).getAlpha() % 2);

        pixelBuffer.append(pixel.get(1).getRed() % 2);
        pixelBuffer.append(pixel.get(1).getGreen() % 2);
        pixelBuffer.append(pixel.get(1).getBlue() % 2);
        pixelBuffer.append(pixel.get(1).getAlpha() % 2);

        return pixelBuffer.toString();
    }

    private ArrayList<Color> getNextPixels()
    {
        ArrayList<Color> nextPixels = new ArrayList<Color>();
        nextPixels.add(new Color(pixelMap[this.coordinate.x][this.coordinate.y]));
        this.addToPixelMapCoord();

        nextPixels.add(new Color(pixelMap[this.coordinate.x][this.coordinate.y]));
        this.addToPixelMapCoord();

        return nextPixels;
    }

    private void addToPixelMapCoord(){
        if(this.coordinate.x < this.imageLength)
        {
            this.coordinate.x++;
        }
        else
        {
            this.coordinate.x = 0;
            this.coordinate.y++;
        }
    }

    public Image getManipulatedImage(String secretText)
    {
        this.secretText = secretText;
        this.encodeTextToImage();
        return null;
    }
}