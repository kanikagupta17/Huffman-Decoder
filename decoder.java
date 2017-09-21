package ADSHuffman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Kanika Gupta
 *
 */

public class decoder {

	public static void main(String args[]) {

		long startTime = System.currentTimeMillis();
		String codeTable_file = args[1];
		String encoded_file = args[0];
		parse_code_table_and_build_decode_tree(codeTable_file);
		parse_encoded_file_and_decode(encoded_file);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Time using decoder: " + totalTime);
	}

	/* Parses Code Table and Builds Decode Tree */
	public static void parse_code_table_and_build_decode_tree(String codeTable_file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(codeTable_file));
			String line = "";

			while ((line = br.readLine()) != null && !line.equals("")) {
				String vars[] = line.split(" ");
				/* Adds each individual value and huffman code to decode tree */
				DecoderTree.add_code_to_decode_tree(vars[0], vars[1]);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception in parseCodeTableBuildDecodeTree : " + e.getMessage());
			e.printStackTrace();
		} catch (IOException ex) {
			System.out.println("IO Exception in parseCodeTableBuildDecodeTree: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	/* Parses Encoded File */
	public static void parse_encoded_file_and_decode(String encoded_file) {

		try {
			Path path = Paths.get(encoded_file);
			byte[] bytearray = Files.readAllBytes(path);
			StringBuilder code = new StringBuilder();
			for (byte nextbyte : bytearray) {
				code.append(String.format("%8s", Integer.toBinaryString(nextbyte & 0xff)).replace(' ', '0')); // Padding of 0s in the front of each binary code
			}
			DecoderTree.decode_code(code.toString());
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception in parse_encoded_file_and_decode : " + e.getMessage());
			e.printStackTrace();
		} catch (IOException ex) {
			System.out.println("IO Exception in parse_encoded_file_and_decode : " + ex.getMessage());
			ex.printStackTrace();
		}

	}
}
