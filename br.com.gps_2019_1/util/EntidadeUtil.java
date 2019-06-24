/**
 * 
 */
package util;

import java.util.ArrayList;
import java.util.List;

import entidade.Dado;

/**
 * @author Aluno
 *
 */
public class EntidadeUtil {

	public static double parceValorToDouble(String value)
	{
		Double d;
		try
		{
			d = Double.parseDouble(value);
		}
		catch (Exception e)
		{
			if(value.contains(","))
			{
				value = value.replace(",", ".");
				
					try
					{
						d = Double.parseDouble(value);
					}
					catch (Exception e1)
					{
						return 1;
					}
			}
			else if(value.trim().equals(""))
				return 1;
			else return 1;
		}
		
		return d.doubleValue();
	}
	
	public static List<Dado> getOrdenado(List<Dado> dados)
	{
		List<Dado> dadosAdicionais = new ArrayList<>();
		
		for(Dado dado: dados)
		{
			boolean achei = false;
			
			for(Dado dadoTemp: dadosAdicionais)
			{
				String nome1 = dado.getCol_2_nome();
				String nome2 = dadoTemp.getCol_2_nome();
				String familia1 = dado.getCol_1_nome_familia();
				String familia2 = dadoTemp.getCol_1_nome_familia();

				if(nome1.equals(nome2) && familia1.equals(familia2))
				{
					Double d1 = Double.parseDouble(dado.getCol_3_valor());
					Double d2 = Double.parseDouble(dadoTemp.getCol_3_valor());

					Double d3 = d1.doubleValue() + d2.doubleValue();
					dadoTemp.setCol_3_valor("" + d3.doubleValue());
					achei = true;
					break;
				}
			}
			if(!achei)
			{
				Dado d = new Dado();
				d.setId(dado.getId());
				d.setCol_1_nome_familia(dado.getCol_1_nome_familia());
				d.setCol_2_nome(dado.getCol_2_nome());
				d.setCol_3_valor(dado.getCol_3_valor());
				
				dadosAdicionais.add(d);
			}
		}	
		
		return dadosAdicionais;
	}
	
}
