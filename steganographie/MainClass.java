package steganographie;

import steganographie.imageloader.ImageLoader;

public class MainClass {

    private void loadImage(String path)
    {
        ImageLoader imageLoader = new ImageLoader();
    }

    public static void main(String[]args)
    {
        MainClass main = new MainClass();
        main.loadImage("test");
    }
}
