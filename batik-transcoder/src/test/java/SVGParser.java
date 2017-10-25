import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.io.*;

/**
 * Created by hunter on 2017/10/23.
 */
public class SVGParser {
    public static String readFileByLines(String fileName) {
        FileInputStream file = null;
        BufferedReader reader = null;
        InputStreamReader inputFileReader = null;
        StringBuffer buf = new StringBuffer();
        String tempString = null;
        try {
            file = new FileInputStream(fileName);
            inputFileReader = new InputStreamReader(file, "utf-8");
            reader = new BufferedReader(inputFileReader);
            // 四次读入四行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                buf.append(tempString.trim());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return buf.toString();
    }

    public static void convertToPng(String svgCode, OutputStream outputStream)
            throws TranscoderException, IOException {
        try {
            byte[] bytes = svgCode.getBytes("utf-8");
            PNGTranscoder t = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
            TranscoderOutput output = new TranscoderOutput(outputStream);
            t.transcode(input, output);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void convertToPng(String svgCode, String pngFilePath) throws IOException,
            TranscoderException {

        File file = new File(pngFilePath);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            convertToPng(svgCode, outputStream);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String data = readFileByLines("/Users/hunter/Desktop/1.svg");
        convertToPng(data, "/Users/hunter/Desktop/test.png");
    }
}
