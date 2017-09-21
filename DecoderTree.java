package ADSHuffman;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DecoderTree{
	static DecoderTreeNode root=new DecoderTreeNode();
	static String decodedFile="decoded.txt";
	
	/*
	 * Creates Decode Tree by adding a node to left if 0 is encountered and node
	 * to right if 1 is encountered and at last bit adds data to the node
	 */
	public static void add_code_to_decode_tree(String data, String code) {

		DecoderTreeNode temp = root;
		int i = 0;
		for (i = 0; i <= code.length() - 2; i++) {
			if (code.charAt(i) == '0') {
				if (temp.left == null) {
					temp.left = new DecoderTreeNode();
					temp = temp.left;
				} else {
					temp = temp.left;
				}
			} else {
				if (code.charAt(i) == '1') {
					if (temp.right == null) {
						temp.right = new DecoderTreeNode();
						temp = temp.right;
					} else {
						temp = temp.right;
					}
				}
			}
		}
		if (code.charAt(i) == '0') {
			temp.left = new DecoderTreeNode(data);
		} else {
			temp.right = new DecoderTreeNode(data);
		}
	}

	/* Parse decoder tree to write value of each huffman code in the file */
	public static void decode_code(String code) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(decodedFile));
		DecoderTreeNode temp = root;
		for (int i = 0; i < code.length(); i++) {

			if (code.charAt(i) == '0') {
				temp = temp.left;
				if (temp.left == null && temp.right == null) {
					bw.write(temp.getData() + "\n");
					temp = root;
				}
			} else {
				temp = temp.right;
				if (temp.left == null && temp.right == null) {
					bw.write(temp.getData() + "\n");
					temp = root;
				}
			}
		}
		bw.close();
	}
}
