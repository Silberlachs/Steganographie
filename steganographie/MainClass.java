package steganographie;

import steganographie.imageloader.ImageLoader;

public class MainClass {

    private void loadImage(String path) {
        ImageLoader imageLoader = new ImageLoader(path);
        int [][] imageArray = imageLoader.getPixelMap();

        int roterPixel = imageArray[imageArray.length/6][imageArray[0].length/2];
        int roterPixel1 = imageArray[imageArray.length/6][imageArray[0].length/2 +1];

        System.out.println(Integer.toHexString(roterPixel));

        // großes L = 0x4c bzw. 76 0100 1100
        // A 0000 0000 | R 0000 0000 | G 0000 0000 | B 0000 0000
        /*
            Pixel ARGB % 2
            Alle values addieren zu string -> binär
            2. pixel
            checken ob der buchstabe entspricht
            dann schauen wo das bit geändert werden muss
            checken ob max oder min erreicht ist dann + oder - zu wert an gewünschter stelle

         */
        char characterTest = 'A'; // 01000001

        Color col1 = new Color(roterPixel1);
        Color col = new Color(roterPixel);

        StringBuffer pixelBuffer = new StringBuffer();
        pixelBuffer.append(col.getRed() % 2);
        pixelBuffer.append(col.getGreen() % 2);
        pixelBuffer.append(col.getBlue() % 2);
        pixelBuffer.append(col.getAlpha() % 2);

        pixelBuffer.append(col1.getRed() % 2);
        pixelBuffer.append(col1.getGreen() % 2);
        pixelBuffer.append(col1.getBlue() % 2);
        pixelBuffer.append(col1.getAlpha() % 2);

        //0x41 A 0100 0001
        //0x5a Z 0101 1010
        //0x61 a 0110 0001
        //0x7a z 0111 1010

        System.out.println(pixelBuffer.toString());

        //checken if max Value bereits erreicht (z.B. bei alphakanal)
        Color newCol = new Color (col.getRed(), col.getGreen()+1, col.getBlue() +1, col.getAlpha()-1);
        Color newCol1 = col1;

        //die neuen pixel values in pixelMap reinschreiben

    }

    public static void main(String[]args) {
        MainClass main = new MainClass();
        main.loadImage("./ds2.png");
    }
}
