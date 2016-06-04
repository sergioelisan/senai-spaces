package cadastro.excecoes;

public class TurmaNaoCadastradaException extends Exception 
{
	private static final long serialVersionUID = 1L;
	public TurmaNaoCadastradaException(String s3)
	{
		super(s3);
	}

}
