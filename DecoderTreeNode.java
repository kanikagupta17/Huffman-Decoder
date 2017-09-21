package ADSHuffman;

/* A class for decoder tree node*/
public class DecoderTreeNode {

	DecoderTreeNode left= null;
	DecoderTreeNode right=null;
    String data=null;

    public DecoderTreeNode(){

    }
    public DecoderTreeNode(String data){
        this.data = data;
    }
    public void setData(String data){
        this.data = data;
    }
    public String getData(){
        return this.data;
    }
   
}
