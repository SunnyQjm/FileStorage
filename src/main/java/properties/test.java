package properties;


import utils.CheckSumUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;

/**
 * Created by Sunny on 2017/7/7 0007.
 */
public class test {
    public static void main(String[] args) {
//        CRC32 c1 = new CRC32();
//        CRC32 c2 = new CRC32();
//        String s1 = "wefasfgsadgasgasga";
//        String s2 = "gasgadagasdgadgadfkjasbnldfuhaufhaasfbiasfnelanefjklwfnlwakenfcwamkwalvnejwnvlwanwejvwlanvnwvwklenjwvwawevwrvasdfvgbsadrfbesrrbersrberberrbserbesrberrbesrbserrbserb";
//
//        System.out.println(new String(s1.getBytes()));
//        System.out.println(new String(Arrays.copyOf(s1.getBytes(), s1.getBytes().length + 8)));

        File file = new File("./src/test.txt");
        System.out.println(file.length());
        System.out.println(file.delete());
        System.out.println(file.length());
    }
}
