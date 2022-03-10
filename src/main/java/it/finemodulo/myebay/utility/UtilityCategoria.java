package it.finemodulo.myebay.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.finemodulo.myebay.model.Categoria;

public class UtilityCategoria {

	public static Map<Categoria, Boolean> createMapForAttribute(List<Categoria> listAll) {
		Map<Categoria, Boolean> map = new HashMap<>();
		for (Categoria categoriaItem : listAll) {
			map.put(categoriaItem, false);
		}
		return map;
	}
	public static Map<Categoria, Boolean> buildCheckedCategorieForPages(List<Categoria> listaTotaleCategoria,
			String[] categoriaFromParams) {
		Map<Categoria, Boolean> result = new HashMap<>();

		// converto array di string in List di Long
		List<Long> categoriaIdConvertiti = new ArrayList<>();
		for (String stringItem : categoriaFromParams != null ? categoriaFromParams : new String[] {}) {
			categoriaIdConvertiti.add(Long.valueOf(stringItem));
		}

		for (Categoria categoriaItem : listaTotaleCategoria) {
			result.put(categoriaItem, categoriaIdConvertiti.contains(categoriaItem.getId()));
		}

		return result;
	}
}
