
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.*;

public final class StringCompressor {

    private StringCompressor() {
    }

    public static String compressGZIP(String value) {
        if (value == null || value.length() == 0) {
            throw new IllegalArgumentException("Cannot compress null or empty string...");
        }
        try {
            byte[] bytes = value.getBytes("UTF-8");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(value.length());
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            gzipOutputStream.write(bytes);
            gzipOutputStream.close();
            byte[] compressed = outputStream.toByteArray();
            outputStream.close();
            return new String(compressed, "ISO-8859-1");
        } catch (IOException e) {
            return null;
        }
    }

    public static String decompressGZIP(String value) {
        if (value == null || value.length() == 0) {
            throw new IllegalArgumentException("Cannot decompress null value");
        }
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(value.getBytes("ISO-8859-1"));
            GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gzipInputStream, "UTF-8"));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static String compressDEFLATE(String value){
        return compressDEFLATE(value, Deflater.BEST_COMPRESSION);
    }

    public static String compressDEFLATE(String value, Integer deflaterArg) {
        if (value == null || value.length() == 0) {
            throw new IllegalArgumentException("Cannot compress null or empty string...");
        }
        try {
            byte[] data = value.getBytes("ISO-8859-1");
            ByteArrayOutputStream out = new ByteArrayOutputStream(value.length());
            Deflater d = new Deflater(deflaterArg);
            DeflaterOutputStream dout = new DeflaterOutputStream(out, d);
            dout.write(data);
            dout.close();
            byte[] arr = out.toByteArray();
            out.close();
            return new String(arr, "ISO-8859-1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decompressDEFLATE(String value) {
        if (value == null || value.length() == 0) {
            throw new IllegalArgumentException("Cannot decompress null or empty string...");
        }
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(value.getBytes("ISO-8859-1"));
            InflaterInputStream in = new InflaterInputStream(inputStream);
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            int b;
            while ((b = in.read()) != -1) {
                bout.write(b);
            }
            in.close();
            bout.close();
            return new String(bout.toByteArray(), "ISO-8859-1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
