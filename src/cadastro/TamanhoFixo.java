package cadastro;

import javax.swing.text.*;
    
/* 
 *  Classe usada para sobrescrever o PlainDocument padrao
 *  dos JTextFields para um PlainDocument de tamanho personalizado * 
 */

@SuppressWarnings("serial")
public class TamanhoFixo extends PlainDocument {
    
	private int tamanhoMaximo;

    public TamanhoFixo(int maxlen) {
        super();
        tamanhoMaximo = maxlen;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;
        
        if (tamanhoMaximo <= 0) {
            super.insertString(offset, str, attr);
            return;
        }

        int ilen = (getLength() + str.length());
        
        if (ilen <= tamanhoMaximo) super.insertString(offset, str, attr);
   }
}
