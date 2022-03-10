package it.finemodulo.myebay.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.finemodulo.myebay.model.Categoria;

public class UtilityCategoria {

	public static Map<Categoria, String> createMapForAttribute(List<Categoria> listAll) {
		Map<Categoria, String> map = new HashMap<>();
		for (Categoria categoriaItem : listAll) {
			map.put(categoriaItem, "");
		}
		return map;
	}

}
