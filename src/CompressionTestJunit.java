import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class CompressionTestJunit {
    private String str = "Парки жених злобу Вы Какое ум се об Ту казнь. Владение Се Он За Опершись ко Неверных. Ошую но взор бы Он ум жить лучи лону По ей за. Уз единица РАДОСТЬ Уж же их уповать Со гордыне. По смятенной во ли то Из поддержит сжалишься За Объемлешь До Заповедям. Чтобы лепый томна Так Отч Бог Ваш суеты.";

    @Test
    public void testCompressionForGZIP() {
        String compressedGZIP = StringCompressor.compressGZIP(str);
        System.out.println("GZIP Compressed from: " + str.length() + " to " + compressedGZIP.length() + " with a compression ratio of " + str.length() / compressedGZIP.length());
        assertNotSame(compressedGZIP.length(), str.length());
    }

    @Test
    public void testCompressionForDEFLATE() {
        String compressedDEFLATE = StringCompressor.compressDEFLATE(str);
        System.out.println("DEFLATE Compressed from: " + str.length() + " to " + compressedDEFLATE.length() + " with a compression ratio of " + str.length() / compressedDEFLATE.length());
        assertNotSame(compressedDEFLATE.length(), str.length());
    }

    @Test
    public void testGZIP() {
        String compressedGZIP = StringCompressor.compressGZIP(str);
        String decompressedGZIP = StringCompressor.decompressGZIP(compressedGZIP);
        assertEquals(str, decompressedGZIP);
    }

    @Test
    public void testDEFLATE() {
        String compressedDEFLATE = StringCompressor.compressDEFLATE(str);
        String decompressedDEFLATE = StringCompressor.decompressDEFLATE(compressedDEFLATE);
        assertEquals(str, decompressedDEFLATE);
    }

    @Test
    public void sequentialGZIP() {
        for (int i = 0; i < 100000; i++) {
            StringCompressor.decompressGZIP(StringCompressor.compressGZIP(str));
        }
    }

    @Test
    public void sequentialDEFLATE() {
        for (int i = 0; i < 100000; i++) {
            StringCompressor.decompressDEFLATE(StringCompressor.compressDEFLATE(str));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionCompressDEFLATE() {
        StringCompressor.compressDEFLATE("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionDecompressDEFLATE() {
        StringCompressor.decompressDEFLATE("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionCompressGZIP() {
        StringCompressor.compressGZIP("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionDecompressGZIP() {
        StringCompressor.compressGZIP("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullExceptionCompressDEFLATE() {
        StringCompressor.compressDEFLATE(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullExceptionDecompressDEFLATE() {
        StringCompressor.decompressDEFLATE(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullExceptionCompressGZIP() {
        StringCompressor.compressGZIP(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullExceptionDecompressGZIP() {
        StringCompressor.compressGZIP(null);
    }
}
