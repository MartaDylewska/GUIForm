

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextAreaLimit extends PlainDocument {

    private int limit;

    JTextAreaLimit(int limit){
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr){
        if(str == null)
            return;

        if((getLength()+str.length()) <= limit){
            try {
                super.insertString(offset,str,attr);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }
}
